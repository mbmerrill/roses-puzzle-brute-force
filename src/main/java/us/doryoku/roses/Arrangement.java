package us.doryoku.roses;

import java.util.Arrays;
import java.util.List;

/**
 * An arrangement of tiles in the pattern that's necessary for a solved puzzle.
 * 
 * @author Matt Merrill
 *
 */
public class Arrangement {

	public static final int ROWS = 3;
	
	public static final int COLS = 3;
	
	/**
	 * The current arrangement of tiles.
	 */
	private Tile[][] layout;

	public Arrangement() {
		clear();
	}
	
	public Arrangement(Arrangement a) {
		clear();
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (a.layout[r][c] != null) {
					this.layout[r][c] = new Tile(a.layout[r][c]);
				}
			}
		}
	}
	
	/**
	 * Wipe out the arrangement of tiles.
	 */
	public void clear() {
		layout = new Tile[ROWS][COLS];
	}
	
	/**
	 * Return the tile at a given position of the layout.
	 * @param row
	 * @param col
	 * @return
	 */
	public Tile getTile(int row, int col) {
		if ((row < 0 || row >= ROWS)) {
			throw new IllegalArgumentException("Row parameter must be between 0 and " + ROWS);
		}
		if ((col < 0 || col >= COLS)) {
			throw new IllegalArgumentException("Col parameter must be between 0 and " + COLS);
		}
		return layout[row][col];
	}
	
	/**
	 * Set the arrangement of tiles to match the input.
	 * 
	 * @param indices Indices into the tiles array that tell which order to place the tiles in the layout.
	 */
	public void layTiles(List<Tile> tiles) {
		
		if (tiles == null || tiles.size() != (ROWS * COLS)) {
			throw new IllegalArgumentException("tiles list must be defined and have a length of [" + (ROWS * COLS) + "]");
		}
		
		// Clear the layout.
		clear();
		
		// Place the files in the layout.
		int row = 0;
		int col = 0;
		
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			layout[row][col] = t;
			
			col++;
			if (col >= COLS) {
				row++;
				col = 0;
			}
		}
		
	}
	
	/**
	 * Rotate the tile at a particular location, setting a new top.
	 * @param row
	 * @param col
	 * @param newTop
	 */
	public void rotateTile(int row, int col, int newTop) {
		layout[row][col].setEdgeTop(newTop);
	}
	
	/**
	 * @return True if the current layout arrangement represents a "solved" puzzle; false otherwise.
	 */
	public boolean isSolved() {
		
		boolean retval = true;
		
		// Iterate over the entire layout.
		for (int r = 0; retval && r < ROWS; r++) {
			for (int c = 0; retval && c < COLS; c++) {
				
				// Grab the current tile.
				Tile t = layout[r][c];
				
				// Grab the tile to the right of the current tile.
				Tile toRight = null;
				if (c < (COLS - 1)) {
					toRight = layout[r][c + 1];
				}
				
				// If there's a tile to the right of the current tile
				if (toRight != null) {
					// See if the adjoining edges match.
					TileEdge mine = t.getEdgeRight();
					TileEdge theirs = toRight.getEdgeLeft();
					retval &= mine.matches(theirs);
				}
				
				// Grab the tile to the bottom of the current tile.
				Tile toBottom = null;
				if (r < (ROWS - 1)) {
					toBottom = layout[r + 1][c];
				}
				
				// If there's a tile to the bottom of the current tile
				if (toBottom != null) {
					// See if the adjoining edges match.
					TileEdge mine = t.getEdgeBottom();
					TileEdge theirs = toBottom.getEdgeTop();
					retval &= mine.matches(theirs);
				}
				
			}
		}
		
		return retval;
		
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				sb.append(layout[r][c]);
				sb.append(" ");
			}
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
		
	}
	
	/**
	 * @return A nicely-formatted String showing the layout and orientation of the tiles.
	 */
	public String display() {
		
		StringBuilder sb = new StringBuilder();

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				Tile t = layout[r][c];
				sb.append(" ");
				sb.append(t.getEdgeTop());
				sb.append(" ");
				if (c < (COLS - 1)) { sb.append("|"); }
			}
			sb.append(System.lineSeparator());
			for (int c = 0; c < COLS; c++) {
				Tile t = layout[r][c];
				sb.append(t.getEdgeLeft());
				sb.append(t.getId());
				sb.append(t.getEdgeRight());
				if (c < (COLS - 1)) { sb.append("|"); }
			}
			sb.append(System.lineSeparator());
			for (int c = 0; c < COLS; c++) {
				Tile t = layout[r][c];
				sb.append(" ");
				sb.append(t.getEdgeBottom());
				sb.append(" ");
				if (c < (COLS - 1)) { sb.append("|"); }
			}
			sb.append(System.lineSeparator());
			if (r < (ROWS - 1)) { 
				sb.append("---+---+---");
				sb.append(System.lineSeparator());
			}

		}

		return sb.toString();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(layout);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arrangement other = (Arrangement) obj;
		if (!Arrays.deepEquals(layout, other.layout))
			return false;
		return true;
	}

}
