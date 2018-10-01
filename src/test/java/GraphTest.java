import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;

public class GraphTest {

	private void assertGraphsEquals(List<Set<Rectangle>> expected, List<Set<Rectangle>> actual) {
		for (Set<Rectangle> subgraph_expected : expected) {
			assertTrue(String.format("Actual list %s did not contain %s", actual, subgraph_expected), actual.contains(subgraph_expected));
		}
		for (Set<Rectangle> subgraph_actual : actual) {
			assertTrue(String.format("Expected list %s did not contain %s", expected, subgraph_actual), expected.contains(subgraph_actual));
		}
	}

	@Test
	public void SimpleDisconnectedGraphTest() {
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
		assertGraphsEquals(expected, actual);
	}

	@Test
	public void CyclicalGraphTest() {
		/*
		+-----+-----+-----+-----+-----+-----+-----+
		|     | r10 | r14 | r11 | r13 | r12 |     |
		|     |     +-----+     +-----+     +     |
		|     +-----+     +-----+     +-----+     |
		|     |                             |     |
		|     +-----+     +-----+     +-----+     |
		| r0  | r7  |     | r8  |     | r9  | r6  |
		|     +-----+     +-----+     +-----+     |
		|     |                             |     |
		|     +-----+     +-----+     +-----+     |
		|     |     +-----+     +-----+     +     |
		|     | r1  | r2  | r3  | r4  | r5  |     |
		+-----+-----+-----+-----+-----+-----+-----+
		 */
		Rectangle r0 = new Rectangle(0, 0, 48, 600);
		Rectangle r1 = new Rectangle(48, 0, 120, 192);
		Rectangle r2 = new Rectangle(120, 0, 136, 52);
		Rectangle r3 = new Rectangle(136, 0, 260, 192);
		Rectangle r4 = new Rectangle(260, 0, 276, 52);
		Rectangle r5 = new Rectangle(276, 0, 352, 192);
		Rectangle r6 = new Rectangle(352, 0, 400, 600);
		Rectangle r7 = new Rectangle(48, 208, 120, 392);
		Rectangle r8 = new Rectangle(136, 208, 260, 392);
		Rectangle r9 = new Rectangle(276, 208, 352, 392);
		Rectangle r10 = new Rectangle(48, 408, 120, 600);
		Rectangle r11 = new Rectangle(136, 408, 260, 600);
		Rectangle r12 = new Rectangle(276, 408, 352, 600);
		Rectangle r13 = new Rectangle(260, 548, 276, 600);
		Rectangle r14 = new Rectangle(120, 548, 136, 600);

		List<Set<Rectangle>> expected = new ArrayList<Set<Rectangle>>() {{
			add(new HashSet<Rectangle>() {{
				add(r0);
				add(r1);
				add(r2);
				add(r3);
				add(r4);
				add(r5);
				add(r6);
				add(r7);
				add(r9);
				add(r10);
				add(r11);
				add(r12);
				add(r13);
				add(r14);
			}});
			add(new HashSet<Rectangle>() {{
				add(r8);
			}});
		}};

		Graph<Rectangle> graph = new Graph<>();
		graph.addNode(r0);
		graph.addNode(r1);
		graph.addNode(r2);
		graph.addNode(r3);
		graph.addNode(r4);
		graph.addNode(r5);
		graph.addNode(r6);
		graph.addNode(r7);
		graph.addNode(r8);
		graph.addNode(r9);
		graph.addNode(r10);
		graph.addNode(r11);
		graph.addNode(r12);
		graph.addNode(r13);
		graph.addNode(r14);
		graph.addEdge(r0, r1);
		graph.addEdge(r0, r7);
		graph.addEdge(r0, r10);
		graph.addEdge(r1, r2);
		graph.addEdge(r2, r3);
		graph.addEdge(r3, r4);
		graph.addEdge(r4, r5);
		graph.addEdge(r5, r6);
		graph.addEdge(r6, r9);
		graph.addEdge(r6, r12);
		graph.addEdge(r10, r14);
		graph.addEdge(r11, r13);
		graph.addEdge(r11, r14);
		graph.addEdge(r12, r13);

		List<Set<Rectangle>> actual = graph.disconnectedSubgraphs();
		assertGraphsEquals(expected, actual);
	}

}
