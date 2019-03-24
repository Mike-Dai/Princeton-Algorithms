import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int compareTo(Point that) {
		if (this.x == that.x && this.y == that.y) {
			return 0;
		}
		else if (this.y < that.y) {
			return this.y - that.y;
		}
		else if (this.y == that.y && this.x < that.x) {
			return this.x - that.x;
		}
		else if (this.y == that.y && this.x > that.x) {
			return this.x - that.x;
		} 
		else {
			return this.y - that.y;
		}
	}

	public double slopeTo(Point that) {
		double result;
		if (this.y == that.y && this.x != that.x) {
			result = +0.0;
		}
		else if (this.x == that.x && this.y != that.y) {
			result = Double.POSITIVE_INFINITY; 
		}
		else if (this.x == that.x && this.y == that.y) {
			result = Double.NEGATIVE_INFINITY;
		}
		else {
			result = (double)(that.y - this.y) / (this.x - this.y);
		}
		return result;
	}

	private class SlopeOrder implements Comparator<Point> {

		public int compare(Point q, Point r) {
			if (p.slopeTo(q) > p.slopeTo(r)) return 1;
			else if (p.slopeTo(q) < p.slopeTo(r)) return -1;
			else return 0;
		}
	}

	public Comparator<Point> slopeOrder() {
		SlopeOrder order = new SlopeOrder();
		return order;
	}

	public static void main(String[] args) {
	}
}