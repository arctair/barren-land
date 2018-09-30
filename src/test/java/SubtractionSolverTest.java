import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SubtractionSolverTest {

	SubtractionSolver solver = new SubtractionSolver(new Rectangle("0 0 399 599"));

	@Test
	public void GivenA() {
		List<Integer> expected = new ArrayList<Integer>() {{
			add(116800);
			add(116800);
		}};
		List<Rectangle> barren = new ArrayList<Rectangle>() {{
			add(new Rectangle("0 292 399 307"));
		}};
		List<Integer> actual = solver.solve(barren);
		assertEquals(expected, actual);
	}

	@Test
	public void GivenB() {
		List<Integer> expected = new ArrayList<Integer>() {{
			add(22816);
			add(192608);
		}};
		List<Rectangle> barren = new ArrayList<Rectangle>() {{
			add(new Rectangle("48 192 351 207"));
			add(new Rectangle("48 392 351 407"));
			add(new Rectangle("120 52 135 547"));
			add(new Rectangle("260 52 275 547"));
		}};
		List<Integer> actual = solver.solve(barren);
		assertEquals(expected, actual);
	}

}
