import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<T> {

	private class Edge<T> {

		T left;
		T right;

		Edge(T left, T right) {
			this.left = left;
			this.right = right;
		}

	}

	private Set<T> nodes = new HashSet<>();
	private List<Edge<T>> edges = new LinkedList<>();

	public void addNode(T node) {
		this.nodes.add(node);
	}

	public void addEdge(T left, T right) {
		edges.add(new Edge(left, right));
	}

	public List<Set<T>> disconnectedSubgraphs() {
		List<Set<T>> subgraphs = nodes.stream().map(node -> new HashSet<T>() {{ add(node); }}).collect(Collectors.toList());
		for (Edge<T> edge : edges) {
			Set<T> leftSubgraph = null;
			Set<T> rightSubgraph = null;
			for (Set<T> subgraph : subgraphs) {
				if (subgraph.contains(edge.left)) leftSubgraph = subgraph;
				if (subgraph.contains(edge.right)) rightSubgraph = subgraph;
				if (leftSubgraph != null && rightSubgraph != null) break;
			}
			if (leftSubgraph != rightSubgraph) {
				leftSubgraph.addAll(rightSubgraph);
				subgraphs.remove(rightSubgraph);
			}
		}
		return subgraphs;
	}

}
