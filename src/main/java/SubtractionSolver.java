import java.util.*;
import java.util.stream.Collectors;

public class SubtractionSolver {

	private Rectangle bounds;

	public SubtractionSolver(Rectangle bounds) {
		this.bounds = bounds;
	}

	public List<Integer> solve(List<Rectangle> barrens) {
		List<Rectangle> arable = subtractAll(barrens, Collections.singletonList(bounds));
		return areas(arable);
	}

	private List<Rectangle> subtractAll(List<Rectangle> take, List<Rectangle> from) {
		List<Rectangle> subtracted = subtract(take.get(0), from);
		if (take.size() == 1) {
			return subtracted;
		}
		return subtractAll(take.subList(1, take.size()), subtracted);
	}

	private List<Rectangle> subtract(Rectangle take, List<Rectangle> from) {
		return from.stream()
				.flatMap(r -> subtract(take, r).stream())
				.collect(Collectors.toList());
	}

	private List<Rectangle> subtract(Rectangle take, Rectangle from) {
		if (!isOverlapping(take, from)) return Collections.singletonList(from);
		final Rectangle boundedTake = intersect(take, from);
		List<Rectangle> subdivisions = new ArrayList<>(4);
		if (from.left < boundedTake.left) {
			subdivisions.add(new Rectangle(from.left, from.bottom, boundedTake.left, from.top));
		}
		if (from.bottom < boundedTake.bottom) {
			subdivisions.add(new Rectangle(boundedTake.left, from.bottom, boundedTake.right, boundedTake.bottom));
		}
		if (boundedTake.right < from.right) {
			subdivisions.add(new Rectangle(boundedTake.right, from.bottom, from.right, from.top));
		}
		if (boundedTake.top < from.top) {
			subdivisions.add(new Rectangle(boundedTake.left, boundedTake.top, boundedTake.right, from.top));
		}
		return subdivisions;
	}

	private boolean isOverlapping(Rectangle r1, Rectangle r2) {
		return r1.left < r2.right && r2.left < r1.right && r1.bottom < r2.top && r2.bottom < r1.top;
	}

	private Rectangle intersect(Rectangle r1, Rectangle r2) {
		return new Rectangle(
				Math.max(r1.left, r2.left),
				Math.max(r1.bottom, r2.bottom),
				Math.min(r1.right, r2.right),
				Math.min(r1.top, r2.top)
		);
	}

	private List<Integer> areas(List<Rectangle> rs) {
		return graph(rs)
				.disconnectedSubgraphs()
				.stream()
				.mapToInt(this::area)
				.sorted()
				.boxed()
				.collect(Collectors.toList());
	}

	private Graph<Rectangle> graph(List<Rectangle> rs) {
		Graph<Rectangle> graph = new Graph<>();
		Rectangle left, right;
		for (int i = 0; i < rs.size(); i++) {
			graph.addNode(left = rs.get(i));
			for (int j = i + 1; j < rs.size(); j++) {
				if (isAdjacent(left, right = rs.get(j))) {
					graph.addEdge(left, right);
				}
			}
		}
		return graph;
	}

	private boolean isAdjacent(Rectangle r1, Rectangle r2) {
		return ((r1.right == r2.left || r2.right == r1.left) && (r1.bottom < r2.top && r2.bottom < r1.top)) ||
				((r1.bottom == r2.top || r2.bottom == r1.left) && (r1.left < r2.right && r2.left < r1.right));
	}

	private int area(Collection<Rectangle> rs) {
		return rs.stream().mapToInt(Rectangle::area).sum();
	}

}
