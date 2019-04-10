import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;
import java.lang.Iterable;

public class PointSET {
	private int size;
	private SET pset;

	public PointSET() {
		size = 0;
		pset = new SET<Point2D>();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		pset.add(p);
		size++;
	}

	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return pset.contains(p);
	}

	public void draw() {
		for (Point2D p : pset.iterator()) {
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException();
		}
		ArrayList array = new ArrayList<Point2D>();
		Point2D p;
		for (Iterator<Point2D> iter = pset.iterator(); iter.hasNext(); p = iter.next()) {
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
		Point2D big = pset.ceiling(p);
		Point2D small = pset.floor(p);
		double disBig = p.distanceTo(big);
		double disSmall = p.distanceTo(small);
		if (disBig < disSmall) {
			return big;
		}
		else {
			return small;
		}
	}

	public static void main(String[] args) {
		return;
	}
}