public class Rectangle {

	int left;
	int bottom;
	int right;
	int top;

	public Rectangle(int left, int bottom, int right, int top) {
		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}

	public Rectangle(String bounds) {
		String[] parts = bounds.split(" ");
		left = Integer.parseInt(parts[0]);
		bottom = Integer.parseInt(parts[1]);
		// convert from cell notation to boundary notation
		right = Integer.parseInt(parts[2]) + 1;
		top = Integer.parseInt(parts[3]) + 1;
	}

	public int area() {
		return (right - left) * (top - bottom);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Rectangle)) {
			return false;
		}
		Rectangle r = (Rectangle) o;
		return r.left == left && r.bottom == bottom && r.right == right && r.top == top;
	}

}
