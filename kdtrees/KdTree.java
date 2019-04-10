import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.ArrayList;

public class KdTree {
	private int size;
	private SET pset;
	private Node root;

	private static class Node {
		private Point2D p;
		private RectHV rect;
		private Node lb;
		private Node rt;

		public Node(Point2D p) {
			this.p = p;
			lb = null;
			rt = null;

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
	}

	public KdTree() {
		size = 0;
		pset = new SET<Node>();
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
		Node node = new Node(p);
		pset.add(p);
		if (isEmpty()) {
			root = node;
		}
		size++;
		Node parent = root;
		Node child;
		int level = 1;
		while (true) {
			if (level % 2 == 1) {
				if (p.x() < parent.x()) {
					if (parent.lb != null) {
						parent = parent.lb;
					}
					else {
						child = new Node(p);
						parent.setlb(child);
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
						return;
					}
				}
			}
			level++;
		}
	}

	public boolean contains(Point2D p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		Node node = root;
		int level = 1;
		while (node.lb != null || node.rt != null) {
			if (level % 2 == 1) {
				if (p.x() < node.x()) {
					if (node.lb != null) {
						node = node.lb;
					}
					else {
						break;
					}
				}
				else if (p.x() == node.x() && p.y() == node.y()) {
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
				if (p.y() < node.y()) {
					if (node.lb != null) {
						node = node.lb;
					}
					else {
						break;
					}
				}
				else if (p.x() == node.x() && p.y() == node.y()) {
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
		return false;
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
		for (Point2D p : pset.iterator()) {
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