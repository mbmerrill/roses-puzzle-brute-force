package us.doryoku.roses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import us.doryoku.roses.TileEdge.Color;
import us.doryoku.roses.TileEdge.Size;

public class TileTest {

	private static final TileEdge te1 = new TileEdge(Color.PINK, Size.SMALL);
	private static final TileEdge te2 = new TileEdge(Color.RED, Size.BIG);
	private static final TileEdge te3 = new TileEdge(Color.WHITE, Size.SMALL);
	private static final TileEdge te4 = new TileEdge(Color.YELLOW, Size.BIG);
	
	private static final Tile tile = new Tile(1, te1, te2, te3, te4);
	
	@Test
	public void testGetId() {
		assertEquals(1, tile.getId());
	}

	@Test
	public void testGetEdgeTop() {
		assertEquals(te1, tile.getEdgeTop());
	}
	
	@Test
	public void testGetEdgeRight() {
		assertEquals(te2, tile.getEdgeRight());
	}
	
	@Test
	public void testGetEdgeBottom() {
		assertEquals(te3, tile.getEdgeBottom());
	}
	
	@Test
	public void testGetEdgeLeft() {
		assertEquals(te4, tile.getEdgeLeft());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetTopNegative() {
		tile.setEdgeTop(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetTopTooLarge() {
		tile.setEdgeTop(4);
	}
	
	@Test
	public void testSetTop() {
		tile.setEdgeTop(2);
		assertEquals(te3, tile.getEdgeTop());
		assertEquals(te4, tile.getEdgeRight());
		assertEquals(te1, tile.getEdgeBottom());
		assertEquals(te2, tile.getEdgeLeft());
	}
	
	@Test
	public void testCopyConstructor() {
		Tile copy = new Tile(tile);
		assertTrue(copy.equals(tile));
		copy.setEdgeTop(1);
		assertFalse(copy.equals(tile));
	}
	
}
