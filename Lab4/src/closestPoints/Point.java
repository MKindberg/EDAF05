package closestPoints;

public class Point implements Comparable<Point> {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double distanceTo(Point p) {
		return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int compareTo(Point p) {
		return Double.compare(x, p.getX());
	}

	public boolean equals(Point p) {
		return x == p.getX() && y == p.getY();
	}
}
