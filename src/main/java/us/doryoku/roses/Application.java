package us.doryoku.roses;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.doryoku.roses.TileEdge.Color;
import us.doryoku.roses.TileEdge.Size;

/**
 * Brute force solution to the roses puzzle.
 * 
 * @author Matt Merrill
 *
 */
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		Application app = new Application();
		app.solvePuzzle();
	}

	/**
	 * Create the tiles, create a Solver and ask it to solve the puzzle.
	 */
	private void solvePuzzle() {

		// Define the tiles.
		List<Tile> tiles = createTiles();

		// Create the solver.
		Solver solver = new BruteForceSolver();
		solver.setTiles(tiles);

		// Ask the solver to find all solutions.
		List<Arrangement> solutionList = solver.solve();
		
		log.info("Found [" + solutionList.size() + "] solution(s)");
		for (int i = 0; i < solutionList.size(); i++) {
			log.info("Solution #" + (i + 1) + System.lineSeparator() + solutionList.get(i).display());
		}
		
	}

	/**
	 * The tiles are defined to be identical to the physical tiles from the puzzle.  The id
	 * assigned to each is arbitrary, and is just used to make it easier to tie these definitions
	 * back to the physical tiles (to which I attached a sticky note with the id).
	 * 
	 * @return a List of Tile objects that represent the physical tiles of the puzzle
	 */
	private List<Tile> createTiles() {

		List<Tile> retval = new ArrayList<Tile>();

		Tile t1 = new Tile(1, 
				new TileEdge(Color.PINK, Size.SMALL),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.WHITE, Size.SMALL),
				new TileEdge(Color.RED, Size.SMALL)
				);
		retval.add(t1);

		Tile t2 = new Tile(2, 
				new TileEdge(Color.RED, Size.BIG),
				new TileEdge(Color.WHITE, Size.BIG),
				new TileEdge(Color.PINK, Size.BIG),
				new TileEdge(Color.WHITE, Size.SMALL)
				);
		retval.add(t2);

		Tile t3 = new Tile(3, 
				new TileEdge(Color.RED, Size.BIG),
				new TileEdge(Color.WHITE, Size.BIG),
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.PINK, Size.SMALL)
				);
		retval.add(t3);

		Tile t4 = new Tile(4, 
				new TileEdge(Color.WHITE, Size.SMALL),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.PINK, Size.BIG)
				);
		retval.add(t4);

		Tile t5 = new Tile(5, 
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.PINK, Size.BIG),
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.WHITE, Size.SMALL)
				);
		retval.add(t5);

		Tile t6 = new Tile(6, 
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.WHITE, Size.BIG),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.PINK, Size.SMALL)
				);
		retval.add(t6);

		Tile t7 = new Tile(7, 
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.WHITE, Size.SMALL),
				new TileEdge(Color.RED, Size.BIG),
				new TileEdge(Color.PINK, Size.BIG)
				);
		retval.add(t7);

		Tile t8 = new Tile(8, 
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.PINK, Size.BIG)
				);
		retval.add(t8);

		Tile t9 = new Tile(9, 
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.PINK, Size.BIG),
				new TileEdge(Color.WHITE, Size.SMALL)
				);
		retval.add(t9);

		return retval;

	}

}
