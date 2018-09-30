import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GraphTest {

	@Test
	public void GraphTest() {
		/*
		+----+-+----+
		| r2 | | r1 |
		+----+ +----+
		| r3 | | r4 |
		+----+-+----+
		 */
		Rectangle r1 = new Rectangle(60, 50, 100, 100);
		Rectangle r2 = new Rectangle(0, 50, 40, 100);
		Rectangle r3 = new Rectangle(0, 0, 40, 50);
		Rectangle r4 = new Rectangle(60, 0, 100, 50);

		List<Set<Rectangle>> expected = new ArrayList<Set<Rectangle>>() {{
			add(new HashSet<Rectangle>() {{
				add(r1);
				add(r4);
			}});
			add(new HashSet<Rectangle>() {{
				add(r2);
				add(r3);
			}});
		}};

		Graph<Rectangle> graph = new Graph<>();
		graph.addNode(r1);
		graph.addNode(r2);
		graph.addNode(r3);
		graph.addNode(r4);
		graph.addEdge(r1, r4);
		graph.addEdge(r2, r3);

		List<Set<Rectangle>> actual = graph.disconnectedSubgraphs();
		assertEquals(expected, actual);
	}

}
