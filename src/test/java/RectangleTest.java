import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

	@Test
	public void CellToBoundaryTest() {
		Rectangle expected = new Rectangle(0, 0, 100, 100);
		Rectangle actual = new Rectangle("0 0 99 99");
		assertEquals(expected, actual);
	}

	@Test
	public void AreaTest() {
		int expected = 10000;
		Rectangle r = new Rectangle(0, 0, 100, 100);
		int actual = r.area();
		assertEquals(expected, actual);
	}

}
