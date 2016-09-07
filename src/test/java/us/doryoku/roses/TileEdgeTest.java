package us.doryoku.roses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import us.doryoku.roses.TileEdge.Color;
import us.doryoku.roses.TileEdge.Size;

public class TileEdgeTest {

	private static final TileEdge bigWhite = new TileEdge(Color.WHITE, Size.BIG);
	private static final TileEdge littleWhite = new TileEdge(Color.WHITE, Size.SMALL);
	private static final TileEdge littleYellow = new TileEdge(Color.YELLOW, Size.SMALL);
	private static final TileEdge bigYellow = new TileEdge(Color.YELLOW, Size.BIG);
	
	@Test
	public void testBigWhiteMatchesLittleWhite() {
		assertTrue(bigWhite.matches(littleWhite));
	}

	@Test
	public void testBigWhiteMatchesLittleYellow() {
		assertFalse(bigWhite.matches(littleYellow));
	}

	@Test
	public void testBigWhiteMatchesBigYellow() {
		assertFalse(bigWhite.matches(bigYellow));
	}

	@Test
	public void testEqualsGood() {
		assertTrue(bigWhite.equals(new TileEdge(Color.WHITE, Size.BIG)));
	}
	
	@Test
	public void testEqualsBadColor() {
		assertFalse(bigWhite.equals(new TileEdge(Color.YELLOW, Size.BIG)));
	}

	@Test
	public void testEqualsBadSize() {
		assertFalse(bigWhite.equals(new TileEdge(Color.WHITE, Size.SMALL)));
	}

	@Test
	public void testCopyConstructor() {
		TileEdge te = new TileEdge(bigWhite);
		assertTrue(bigWhite.equals(te));
	}
}
