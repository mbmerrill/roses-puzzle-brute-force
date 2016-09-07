package us.doryoku.roses;

import java.util.Arrays;

/**
 * Represents a single physical tile.  A tile has an ID, four edges, and an orientation (which edge is currently on top).
 * 
 * @author Matt Merrill
 *
 */
public class Tile {

	public static final int NUM_EDGES = 4;
	
	public static final Integer[] POSSIBLE_TOP_INDICES = { 0, 1, 2, 3 };
	
	/** 
	 * A Tile is assigned an id, just to make it easier to keep track of which physical tile this Tile represents.
	 */
	private int id;
	
	/**
	 * We store the edges in an array.  This makes it easy to keep track of which edge is on top, by a simple index into the array.
	 */
	private TileEdge[] edges = new TileEdge[NUM_EDGES];
	
	/**
	 * We need to maintain an orientation.  To do so, we keep track of which edge is currently on top.
	 */
	private int edgeTop = 0;
	
	public Tile(int id, TileEdge top, TileEdge right, TileEdge bottom, TileEdge left) {
		this.id = id;
		edges[0] = top;
		edges[1] = right;
		edges[2] = bottom;
		edges[3] = left;
	}
	
	public Tile(Tile t) {
		this.id = t.id;
		for (int i = 0; i < NUM_EDGES; i++) {
			this.edges[i] = new TileEdge(t.edges[i]);
		}
		this.edgeTop = t.edgeTop;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * @return For the current orientation, return the edge on top.
	 */
	public TileEdge getEdgeTop() {
		return edges[edgeTop];
	}
	
	/**
	 * @return For the current orientation, return the edge on the right.
	 */
	public TileEdge getEdgeRight() {
		int edge = edgeTop + 1;
		if (edge >= NUM_EDGES) edge = 0;
		return edges[edge];
	}
	
	/**
	 * @return For the current orientation, return the edge on the bottom.
	 */
	public TileEdge getEdgeBottom() {
		int edge = edgeTop + 1;
		if (edge >= NUM_EDGES) edge = 0;
		edge++;
		if (edge >= NUM_EDGES) edge = 0;
		return edges[edge];
	}
	
	/**
	 * @return For the current orientation, return the edge on the left.
	 */
	public TileEdge getEdgeLeft() {
		int edge = edgeTop - 1;
		if (edge < 0) edge = NUM_EDGES - 1;
		return edges[edge];
	}
	
	/**
	 * Set the tile to a new orientation.
	 * @param i
	 */
	public void setEdgeTop(int i) {
		if ((i < 0) || (i >= NUM_EDGES)) {
			throw new IllegalArgumentException("Attempting to set edgeTop to [" + i + "]");
		}
		edgeTop = i;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(id);
		sb.append(":");
		
		for (TileEdge te : edges) {
			sb.append(te);
		}
		
		sb.append(" (");
		sb.append(edgeTop);
		sb.append(")");
		
		return sb.toString();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edgeTop;
		result = prime * result + Arrays.hashCode(edges);
		result = prime * result + id;
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
		Tile other = (Tile) obj;
		if (edgeTop != other.edgeTop)
			return false;
		if (!Arrays.equals(edges, other.edges))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
