package us.doryoku.roses;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import us.doryoku.roses.TileEdge.Color;
import us.doryoku.roses.TileEdge.Size;

public class BruteForceSolverTest {

	private static List<Tile> correctTileOrder = new ArrayList<Tile>();
	
	private Solver solver;
	
	@BeforeClass
	public static void setup() {
		
		Tile t1 = new Tile(1, 
				new TileEdge(Color.PINK, Size.SMALL),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.WHITE, Size.SMALL),
				new TileEdge(Color.RED, Size.SMALL)
				);

		Tile t2 = new Tile(2, 
				new TileEdge(Color.RED, Size.BIG),
				new TileEdge(Color.WHITE, Size.BIG),
				new TileEdge(Color.PINK, Size.BIG),
				new TileEdge(Color.WHITE, Size.SMALL)
				);

		Tile t3 = new Tile(3, 
				new TileEdge(Color.RED, Size.BIG),
				new TileEdge(Color.WHITE, Size.BIG),
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.PINK, Size.SMALL)
				);

		Tile t4 = new Tile(4, 
				new TileEdge(Color.WHITE, Size.SMALL),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.PINK, Size.BIG)
				);

		Tile t5 = new Tile(5, 
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.PINK, Size.BIG),
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.WHITE, Size.SMALL)
				);

		Tile t6 = new Tile(6, 
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.WHITE, Size.BIG),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.PINK, Size.SMALL)
				);

		Tile t7 = new Tile(7, 
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.WHITE, Size.SMALL),
				new TileEdge(Color.RED, Size.BIG),
				new TileEdge(Color.PINK, Size.BIG)
				);

		Tile t8 = new Tile(8, 
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.YELLOW, Size.BIG),
				new TileEdge(Color.PINK, Size.BIG)
				);

		Tile t9 = new Tile(9, 
				new TileEdge(Color.YELLOW, Size.SMALL),
				new TileEdge(Color.RED, Size.SMALL),
				new TileEdge(Color.PINK, Size.BIG),
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
		solver = new BruteForceSolver();
	}
	
	@Test
	public void testSolverGood() {
		solver.setTiles(correctTileOrder);
		List<Arrangement> solutions = solver.solve(false);
		assertEquals(1, solutions.size());
		Arrangement arrangement = solutions.get(0);
		assertEquals(new TileEdge(Color.RED, Size.SMALL), arrangement.getTile(0, 0).getEdgeTop());
		assertEquals(new TileEdge(Color.RED, Size.SMALL), arrangement.getTile(0, 1).getEdgeTop());
		assertEquals(new TileEdge(Color.PINK, Size.BIG), arrangement.getTile(0, 2).getEdgeTop());
		assertEquals(new TileEdge(Color.YELLOW, Size.SMALL), arrangement.getTile(1, 0).getEdgeTop());
		assertEquals(new TileEdge(Color.YELLOW, Size.BIG), arrangement.getTile(1, 1).getEdgeTop());
		assertEquals(new TileEdge(Color.RED, Size.SMALL), arrangement.getTile(1, 2).getEdgeTop());
		assertEquals(new TileEdge(Color.RED, Size.SMALL), arrangement.getTile(2, 0).getEdgeTop());
		assertEquals(new TileEdge(Color.RED, Size.BIG), arrangement.getTile(2, 1).getEdgeTop());
		assertEquals(new TileEdge(Color.YELLOW, Size.SMALL), arrangement.getTile(2, 2).getEdgeTop());
	}

	@Test
	public void testSolverNoSolution() {
	
		List<Tile> badList = new ArrayList<Tile>();
		for (int i = 0; i < (Arrangement.ROWS * Arrangement.COLS); i++) {
			badList.add(new Tile((i + 1), 
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL)
					));
		}
		solver.setTiles(badList);
		List<Arrangement> solutions = solver.solve(false);
		assertEquals(0, solutions.size());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testNullTileList() {
		solver.solve(false);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testTileListTooSmall() {
		List<Tile> badList = new ArrayList<Tile>();
		for (int i = 0; i < (Arrangement.ROWS * Arrangement.COLS - 1); i++) {
			badList.add(new Tile((i + 1), 
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL)
					));
		}
		solver.setTiles(badList);
		solver.solve(false);
	}

	@Test(expected = IllegalStateException.class)
	public void testTileListTooLarge() {
		List<Tile> badList = new ArrayList<Tile>();
		for (int i = 0; i < (Arrangement.ROWS * Arrangement.COLS + 1); i++) {
			badList.add(new Tile((i + 1), 
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL),
					new TileEdge(Color.PINK, Size.SMALL)
					));
		}
		solver.setTiles(badList);
		solver.solve(false);
	}
	
}
