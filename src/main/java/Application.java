import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

	public static void main(String[] args) {
		List<Rectangle> barren = parse(getStdin());
		SubtractionSolver solver = new SubtractionSolver(new Rectangle("0 0 399 599"));
		List<Integer> arableAreas = solver.solve(barren);
		String out = arableAreas.stream()
				.map(Object::toString)
				.collect(Collectors.joining(" "));
		System.out.println(out);
	}

	public static String getStdin() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return reader.readLine();
		}
		catch (IOException exception) {
			throw new RuntimeException("Could not read from stdin", exception);
		}
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
