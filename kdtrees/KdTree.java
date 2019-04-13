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
		/*
		Node node = new Node(p);
		pset.add(p);
		if (isEmpty()) {
			root = node;
			node.setRect(0, 0, 1, 1);
		}
		size++;
		Node parent = root;
		Node child;
		int level = 1;
		double xmin, ymin, xmax, ymax;
		while (true) {
			if (level % 2 == 1) {
				if (p.x() < parent.x()) {
					if (parent.lb != null) {
						parent = parent.lb;
					}
					else {
						child = new Node(p);
						parent.setlb(child);
						xmin = parent.xmin();
						ymin = parent.ymin();
						xmax = parent.x();
						ymax = parent.ymax();
						child.setRect(xmin, ymin, xmax, ymax);
						child.setDir(0);
						return;
					}
				}
				else {
					if (parent.rt != null) {
						parent = parent.rt;
					}
					else {
						child = new Node(p);
						parent.setrt(child);
						xmin = parent.x();
						ymin = parent.ymin();
						xmax = parent.xmax();
						ymax = parent.ymax();
						child.setRect(xmin, ymin, xmax, ymax);
						child.setDir(0);
						return;
					}
				}
			}
			else if (level % 2 == 0) {
				if (p.y() < parent.y()) {
					if (parent.lb != null) {
						parent = parent.lb;
					}
					else {
						child = new Node(p);
						parent.setlb(child);
						xmin = parent.xmin();
						ymin = parent.ymin();
						xmax = parent.xmax();
						ymax = parent.y();
						child.setRect(xmin, ymin, xmax, ymax);
						child.setDir(1);
						return;
					}
				}
				else {
					if (parent.rt != null) {
						parent = parent.rt;
					}
					else {
						child = new Node(p);
						parent.setrt(child);
						xmin = parent.xmin();
						ymin = parent.y();
						xmax = parent.xmax();
						ymax = parent.ymax();
						child.setRect(xmin, ymin, xmax, ymax);
						child.setDir(1);
						return;
					}
				}
			}
			level++;
		}
		*/
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

	/*
	private void nearestP(Node node, Point2D p, double min) {
		if (node == null) {
			return null;
		}
		if (p.x() < node.x()) {

		}
	}
	*/

	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		Point2D minp = null;
		double min = 10;
		//minp = nearestP(root, p, min);
		
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