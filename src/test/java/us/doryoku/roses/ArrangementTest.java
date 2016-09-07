package us.doryoku.roses;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import us.doryoku.roses.TileEdge.Color;
import us.doryoku.roses.TileEdge.Size;

public class ArrangementTest {

	private static List<Tile> correctTileOrder = new ArrayList<Tile>();
	
	private Arrangement arrangement;
	
	@BeforeClass
	public static void setup() {
		
		Tile t1 = new Tile(1, 
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.SMALL)
				);

		Tile t2 = new Tile(2, 
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.SMALL)
				);

		Tile t3 = new Tile(3, 
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.SMALL)
				);

		Tile t4 = new Tile(4, 
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG)
				);

		Tile t5 = new Tile(5, 
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.SMALL)
				);

		Tile t6 = new Tile(6, 
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.SMALL)
				);

		Tile t7 = new Tile(7, 
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG)
				);

		Tile t8 = new Tile(8, 
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG)
				);

		Tile t9 = new Tile(9, 
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.SMALL),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG),
				new TileEdge(Color.WHITE, Size.SMALL)
				);

		correctTileOrder.add(t1);
		correctTileOrder.add(t5);
		correctTileOrder.add(t2);
		correctTileOrder.add(t7);
		correctTileOrder.add(t6);
		correctTileOrder.add(t8);
		correctTileOrder.add(t4);
		correctTileOrder.add(t3);
		correctTileOrder.add(t9);

	}
	
	@Before
	public void before() {
		arrangement = new Arrangement();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullTileList() {
		arrangement.layTiles(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTileListTooSmall() {
		List<Tile> tileList = new ArrayList<Tile>();
		tileList.add(correctTileOrder.get(0));
		arrangement.layTiles(tileList);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTileListTooBig() {
		List<Tile> tileList = new ArrayList<Tile>();
		tileList.addAll(correctTileOrder);
		tileList.add(new Tile(9, 
				new TileEdge(TileEdge.Color.YELLOW, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.RED, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.PINK, TileEdge.Size.BIG),
				new TileEdge(TileEdge.Color.WHITE, TileEdge.Size.BIG)));
		assertEquals(10, tileList.size());
		assertEquals(9, correctTileOrder.size());
		arrangement.layTiles(tileList);
	}
	
	@Test
	public void testIsSolvedNope() {
		arrangement.layTiles(correctTileOrder);
		assertFalse(arrangement.isSolved());
	}
	
	@Test
	public void testIsSolvedYes() {
		arrangement.layTiles(correctTileOrder);
		arrangement.rotateTile(0, 0, 3);
		arrangement.rotateTile(0, 1, 2);
		arrangement.rotateTile(0, 2, 2);
		arrangement.rotateTile(1, 0, 0);
		arrangement.rotateTile(1, 1, 2);
		arrangement.rotateTile(1, 2, 0);
		arrangement.rotateTile(2, 0, 2);
		arrangement.rotateTile(2, 1, 0);
		arrangement.rotateTile(2, 2, 0);
		assertTrue(arrangement.isSolved());
	}
	
	@Test
	public void testClear() {
		arrangement.clear();
		assertNull(arrangement.getTile(0, 0));
		assertNull(arrangement.getTile(2, 2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTileRowNeg() {
		arrangement.getTile(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTileColNeg() {
		arrangement.getTile(0, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTileRowTooBig() {
		arrangement.getTile(3, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTileColTooBig() {
		arrangement.getTile(0, 3);
	}
	
	@Test
	public void testCopyConstructor() {
		arrangement.layTiles(correctTileOrder);
		Arrangement copy = new Arrangement(arrangement);
		assertTrue(copy.equals(arrangement));
	}
}
