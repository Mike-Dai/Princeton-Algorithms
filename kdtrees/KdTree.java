import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;
//import java.util.Iterator;

public class KdTree {
	private int size;
	private SET<Point2D> pset;
	private Node root;

	private static class Node {
		private Point2D p;
		private RectHV rect;
		private Node lb;
		private Node rt;
		private boolean isVertical;

		public Node(Point2D p) {
			this.p = p;
			lb = null;
			rt = null;
		}

		public Node(Point2D p, Node lb, Node rt, boolean isVertical) {
			this.p = p;
			this.lb = lb;
			this.rt = rt;
			this.isVertical = isVertical;
		}

		public double x() {
			return p.x();
		}

		public double y() {
			return p.y();
		}

		public void setlb(Node node) {
			lb = node;
		}

		public void setrt(Node node) {
			rt = node;
		}

		public double xmin() {
			return rect.xmin();
		}

		public double ymin() {
			return rect.ymin();
		}

		public double xmax() {
			return rect.xmax();
		}

		public double ymax() {
			return rect.ymax();
		}

		public void setRect(double xmin, double ymin, double xmax, double ymax) {
			rect = new RectHV(xmin, ymin, xmax, ymax);
		}

		public RectHV getRect() {
			return rect;
		}

		public Point2D getP() {
			return p;
		}

		public boolean isVertical() {
			return isVertical;
		}

		public void setDir(int direction) {
			if (direction == 1) {
				isVertical = true;
			}
			else {
				isVertical = false;
			}
		}
	}

	public KdTree() {
		size = 0;
		pset = new SET<Point2D>();
		root = null;
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
		root = insert(root, p, true);
	}

	private Node insert(Node node, Point2D p, boolean isVertical) {
		if (node == null) {
			size++;
			return new Node(p, null, null, isVertical);
		}
		if (node.x() == p.x() && node.y() == p.y()) {
			return node;
		}
		if (p.x() < node.x() && isVertical == true || p.y() < node.y() && isVertical == false) {
			node.lb = insert(node.lb, p, !isVertical);
		}
		else {
			node.rt = insert(node.rt, p, !isVertical);
		}
		return node;
	}

	private boolean containP(Node node, Point2D p) {
		Point2D point = node.getP();
		if (point.x() == p.x() && point.y() == p.y()) {
			return true;
		}
		/*
		if (node.lb == null && node.rt == null) {
			return false;
		}
		*/
		boolean lb = false, rt = false;
		if (node.isVertical() == true) {
			if (p.x() < node.x()) {
				if (node.lb != null) {
					lb = containP(node.lb, p);
				}
			}
			else {
				if (node.rt != null) {
					rt = containP(node.rt, p);
				}
			}
		}
		else {
			if (p.y() < node.y()) {
				if (node.lb != null) {
					lb = containP(node.lb, p);
				}
			}
			else {
				if (node.rt != null) {
					rt = containP(node.rt, p);
				}
			}
		}
		return lb || rt;
	}

	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		if (root == null) {
			return false;
		}
		return containP(root, p);
		/*
		int level = 1;
		while (node.lb != null || node.rt != null) {
			if (level % 2 == 1) {
				double value = Double.compare(p.x(), node.x());
				if (value < 0) {
					if (node.lb != null) {
						node = node.lb;
					}
					else {
						break;
					}
				}
				else if (value == 0 && Double.compare(p.y(), node.y()) == 0) {
					return true;
				}
				else {
					if (node.rt != null) {
						node = node.rt;
					}
					else {
						break;
					}
				}
			}
			else if (level % 2 == 0) {
				double value = Double.compare(p.y(), node.y());
				if (value < 0) {
					if (node.lb != null) {
						node = node.lb;
					}
					else {
						break;
					}
				}
				else if (value == 0 && Double.compare(p.y(), node.y()) == 0) {
					return true;
				}
				else {
					if (node.rt != null) {
						node = node.rt;
					}
					else {
						break;
					}
				}
			}
			level++;
		}
		*/
	}

	public void draw() {
		for (Point2D p : pset) {
			p.draw();
		}
	}

	private void search(Node node, Queue<Point2D> array, RectHV rect) {
		if (!node.getRect().intersects(rect)) {
			return;
		}
		if (node.lb != null) {
			Node lb = node.lb;
			if (rect.contains(lb.getP())) {
				array.enqueue(lb.getP());
			}
			search(lb, array, rect);
		}
		if (node.rt != null) {
			Node rt = node.rt;
			if (rect.contains(rt.getP())) {
				array.enqueue(rt.getP());
			}
			search(rt, array, rect);
		}
		return;
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new IllegalArgumentException();
		}
		if (pset.isEmpty()) {
			return null;
		}
		Queue array = new Queue<Point2D>();
		search(root, array, rect);
		/*
		for (Point2D p : pset) {
			if (rect.contains(p)) {
				array.add(p);
			}
		}
		*/
		return array;
	}

	
	private Point2D nearest(Node node, double x, double y, RectHV rect, Point2D candidate) {
		if (node == null) {
			return candidate;
		}
		Point2D query = new Point2D(x, y);
		double dqn = 0.0;
		double drq = 0.0;
		Point2D nearest = candidate;
		if (nearest != null) {
			dqn = query.distanceSquaredTo(nearest);
			drq = rect.distanceSquaredTo(query);
		}
		if (nearest == null || dqn > drq) {
			Point2D point = new Point2D(node.x(), node.y());
			if (node.isVertical()) {
				RectHV left = new RectHV(rect.xmin(), rect.ymin(), node.x(), rect.ymax());
				RectHV right = new RectHV(node.x(), rect.ymin(), rect.xmax(), rect.ymax());
				if (x < node.x()) {
					nearest = nearest(node.lb, x, y, left, nearest);
				}
				else {
					nearest = nearest(node.rt, x, y, right, nearest);
				}
			}
			else {
				RectHV left = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.y());
				RectHV right = new RectHV(rect.xmin(), node.y(), rect.xmax(), rect.ymax());
				if (y < node.y()) {
					nearest = nearest(node.lb, x, y, left, nearest);
				}
				else {
					nearest = nearest(node.rt, x, y, right, nearest);
				}
			}
		}
		return nearest;
	}

	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		RectHV rect = new RectHV(0, 0, 1, 1);
		Point2D nearest = nearest(root, p.x(), p.y(), rect, null);
		return nearest;
		/*
		for (Point2D point : pset) {
			if (p.distanceSquaredTo(point) < min) {
				min = p.distanceSquaredTo(point);
				minp = point;
			}
		}
		*/
	}

	public static void main(String[] args) {
		return;
	}
}