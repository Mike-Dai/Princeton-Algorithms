import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;

public class PointSET {
	//private int size;
	private SET<Point2D> pset;

	public PointSET() {
		//size = 0;
		pset = new SET<Point2D>();
	}

	public boolean isEmpty() {
		return pset.size() == 0;
	}

	public int size() {
		return pset.size();
	}

	public void insert(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		pset.add(p);
		//size++;
	}

	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return pset.contains(p);
	}

	public void draw() {
		for (Point2D p : pset) {
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException();
		}
		ArrayList array = new ArrayList<Point2D>();
		for (Point2D p : pset) {
			if (rect.contains(p)) {
				array.add(p);
			}
		}
		return array;
	}

	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		Point2D minp = null;
		double min = 10;
		for (Point2D point : pset) {
			if (p.distanceSquaredTo(point) < min) {
				min = p.distanceSquaredTo(point);
				minp = point;
			}
		}
		return minp;
	}

	public static void main(String[] args) {
		return;
	}
}