package us.doryoku.roses;

import java.util.List;

/**
 * 
 * @author Matt Merrill
 *
 */
public interface Solver {

	/**
	 * Define the set of tiles that will be solved.
	 * @param tiles
	 */
	void setTiles(List<Tile> tiles);
	
	/**
	 * Iterate through all permutations, looking for solutions to the puzzle.
	 * 
	 * @return a List of Arrangements that are solutions to the puzzle
	 */
	List<Arrangement> solve();
	
	/**
	 * Look for solutions to the puzzle.
	 * 
	 * @param iterateOverAllPermutationsOfTileOrder if true, shuffle the tiles in every possible permutation to attempt to find a
	 * solution.  If false, the tiles will be left in the original order (but still rotated through all possible orientations).
	 * @return a List of Arrangements that are solutions to the puzzle
	 */
	List<Arrangement> solve(boolean iterateOverAllPermutationsOfTileOrder);

}