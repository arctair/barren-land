import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

	public static void main(String[] args) {
		List<Rectangle> barren = parse(args[0]);
		SubtractionSolver solver = new SubtractionSolver(new Rectangle("0 0 399 599"));
		List<Integer> arableAreas = solver.solve(barren);
		String out = arableAreas.stream()
				.map(Object::toString)
				.collect(Collectors.joining(" "));
		System.out.println(out);
	}

	public static List<Rectangle> parse(String s) {
		if (!s.matches("\\{(\"\\d+ \\d+ \\d+ \\d+\", )*(\"\\d+ \\d+ \\d+ \\d+\")\\}")) {
			throw new RuntimeException("Invalid input format");
		}
		String[] barrenRaw = s.substring(1, s.length() - 1).split(", ");
		return Arrays.asList(barrenRaw).stream()
				.map(withQuotes -> withQuotes.substring(1, withQuotes.length() - 1))
				.map(Rectangle::new)
				.collect(Collectors.toList());
	}

}
