package us.doryoku.roses;

import java.util.ArrayList;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the Solver interface that takes a brute-force approach to the problem.
 * 
 * @author Matt Merrill
 *
 */
public class BruteForceSolver implements Solver {

	private static final Logger log = LoggerFactory.getLogger(BruteForceSolver.class);

	private List<Tile> tiles;

	private Arrangement arrangement;

	/**
	 * This will hold a permutations-with-repeats generator. It will generate
	 * unique sets of "top indices", such as "0 0 0 0 0 0 0 0 0" and "1 0 2 3 0
	 * 0 1 0 0". Each set will consist of 9 integers (our ROWS * COLS), with
	 * each integer being 0-3 (as defined by the number of sides on a tile).
	 */
	private Generator<Integer> uniqueTopIndicesGenerator;

	public BruteForceSolver() {

		// Create the arrangement.
		arrangement = new Arrangement();

		// Create a vector of the possible top indices.
		ICombinatoricsVector<Integer> originalVector = Factory.createVector(Tile.POSSIBLE_TOP_INDICES);

		// Create a generator that will iterate over all possible combinations
		// of 9 choices of the top indices.
		uniqueTopIndicesGenerator = Factory.createPermutationWithRepetitionGenerator(originalVector,
				(Arrangement.ROWS * Arrangement.COLS));

		log.info("There are [" + uniqueTopIndicesGenerator.getNumberOfGeneratedObjects() + "] combinations of ["
				+ (Arrangement.ROWS * Arrangement.COLS) + "] top indices");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see us.doryoku.roses.Solver#setTiles()
	 */
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see us.doryoku.roses.Solver#solve()
	 */
	public List<Arrangement> solve() {
		return solve(true);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see us.doryoku.roses.Solver#solve()
	 */
	public List<Arrangement> solve(boolean iterateOverAllPermutationsOfTileOrder) {

		if (tiles == null || tiles.size() != (Arrangement.ROWS * Arrangement.COLS)) {
			throw new IllegalStateException("tiles list must be defined and have length of [" + (Arrangement.ROWS * Arrangement.COLS) + "]");
		}
		
		log.info("Starting brute-force solution of roses puzzle");

		List<Arrangement> retval = new ArrayList<Arrangement>();

		if (iterateOverAllPermutationsOfTileOrder) {

			// Create a vector based on the original list of tiles.
			ICombinatoricsVector<Tile> originalVector = Factory.createVector(tiles);

			// Create a permutation generator based on the originalVector.
			Generator<Tile> generator = Factory.createPermutationGenerator(originalVector);

			// Run through the permutations.
			log.info("There are [" + tiles.size() + "] tiles, which yields [" + generator.getNumberOfGeneratedObjects()
					+ "] permutations of tiles");
			long count = 1L;
			for (ICombinatoricsVector<Tile> permutationTiles : generator) {
				if (count % 1000 == 0)
					log.debug("Working on permutation [" + count + "]");

				// Arrange this permutation of tiles.
				arrangement.layTiles(permutationTiles.getVector());

				// Try to find solutions with the tiles in this order.
				retval.addAll(iterateThroughTileOrientations());

				count++;

			}

		} else {
			
			// Layout the tiles.
			arrangement.layTiles(tiles);

			// Try to find solutions with the tiles in this order.
			retval.addAll(iterateThroughTileOrientations());

		}

		log.info("Ending brute-force solution of roses puzzle");
		return retval;

	}

	/**
	 * Iterate over all possible tile orientations, looking for a solution.
	 */
	private List<Arrangement> iterateThroughTileOrientations() {

		List<Arrangement> retval = new ArrayList<Arrangement>();

		// Iterate over the set of unique "top indices" arrays.
		for (ICombinatoricsVector<Integer> permutationTopIndices : uniqueTopIndicesGenerator) {

			List<Integer> topIndicesList = permutationTopIndices.getVector();

			// Set each position on the arrangement to the desired top index.
			int row = 0;
			int col = 0;

			for (Integer i : topIndicesList) {
				arrangement.rotateTile(row, col, i);
				col++;
				if (col >= Arrangement.COLS) {
					col = 0;
					row++;
				}
			}

			// If this state represents a solved puzzle, say so!
			if (arrangement.isSolved()) {
				log.info("SOLUTION FOUND: " + System.lineSeparator() + arrangement.display());
				// Store a copy of the current arrangement (a copy because we're going to continue rotating tiles).
				retval.add(new Arrangement(arrangement));
			}

		}

		return retval;

	}

}
