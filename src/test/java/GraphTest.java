import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;

public class GraphTest {

	private void assertGraphsEquals(List<Set<Object>> expected, List<Set<Object>> actual) {
		for (Set<Object> subgraph_expected : expected) {
			assertTrue(String.format("Actual list %s did not contain %s", actual, subgraph_expected), actual.contains(subgraph_expected));
		}
		for (Set<Object> subgraph_actual : actual) {
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
		Object r1 = new Object();
		Object r2 = new Object();
		Object r3 = new Object();
		Object r4 = new Object();

		List<Set<Object>> expected = new ArrayList<Set<Object>>() {{
			add(new HashSet<Object>() {{
				add(r1);
				add(r4);
			}});
			add(new HashSet<Object>() {{
				add(r2);
				add(r3);
			}});
		}};

		Graph<Object> graph = new Graph<>();
		graph.addNode(r1);
		graph.addNode(r2);
		graph.addNode(r3);
		graph.addNode(r4);
		graph.addEdge(r1, r4);
		graph.addEdge(r2, r3);

		List<Set<Object>> actual = graph.disconnectedSubgraphs();
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
		Object r0 = new Object();
		Object r1 = new Object();
		Object r2 = new Object();
		Object r3 = new Object();
		Object r4 = new Object();
		Object r5 = new Object();
		Object r6 = new Object();
		Object r7 = new Object();
		Object r8 = new Object();
		Object r9 = new Object();
		Object r10 = new Object();
		Object r11 = new Object();
		Object r12 = new Object();
		Object r13 = new Object();
		Object r14 = new Object();

		List<Set<Object>> expected = new ArrayList<Set<Object>>() {{
			add(new HashSet<Object>() {{
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
			add(new HashSet<Object>() {{
				add(r8);
			}});
		}};

		Graph<Object> graph = new Graph<>();
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

		List<Set<Object>> actual = graph.disconnectedSubgraphs();
		assertGraphsEquals(expected, actual);
	}

}
