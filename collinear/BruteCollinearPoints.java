import java.util.Arrays;

public class BruteCollinearPoints {
	private Point[] mypoints;
	private LineSegment[] segarray;
	private int segnumber;

	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}
		for (Point p : points) {
			if (p == null) {
				throw new IllegalArgumentException();
			}
		}
		this.segarray = new LineSegment[points.length];
		this.segnumber = 0;
		this.mypoints = points;
		Arrays.sort(mypoints);
		for (int i = 0; i < mypoints.length - 1; i++) {
			if (mypoints[i].compareTo(mypoints[i+1]) == 0) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = 0; i < mypoints.length; i++) {
			for (int j = i + 1; j < mypoints.length; j++) {
				for (int k = j + 1; k < mypoints.length; k++) {
					for (int l = k + 1; l < mypoints.length; l++) {
						Point p = mypoints[i], q = mypoints[j], r = mypoints[k], s = mypoints[l];
						if (p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s)) {
							/*
							int low = i, high = l;
							Arrays.sort(points, low, high, slopeOrder);
							p = points[i];
							s = points[l];
							*/
							segarray[segnumber] = new LineSegment(p, s);
							segnumber++;
						}
					}
				}
			}
		}
		LineSegment[] copy = new LineSegment[segnumber];
		for (int i = 0; i < segnumber; i++) {
			copy[i] = segarray[i];
		}
		segarray = copy;
		/*
		Point origin = new Point(0, 0);
		Arrays.sort(points, origin.slopeOrder());
		for (int i = 0; i < points.length; i++) {
			double slope = points[i].slopeTo(points[i + 1]);
			int[] segpoint = new int[points.length];
			segpoint[0] = i;
			segpoint[1] = i + 1;
			int number = 1;
			int k = 2;
			for (int j = 0; j < points.length; j++) {
				if (j == i) {
					continue;
				}
				if (points[i].slopeTo(points[j]) == slope) {
					number++;
					segpoint[k++] = j;
				}
			}
			if (number >= 3) {
				int end = segpoint[1];
				int far = Math.abs(points[segpoint[0]].compareTo(points[segpoint[1]]));
				for (int p = 1; p < k; p++) {
					if (Math.abs(points[segpoint[0]].compareTo(points[segpoint[p]])) > far) {
						far = Math.abs(points[segpoint[0]].compareTo(points[segpoint[p]]));
						end = segpoint[p];
					}
				}
				segments[segnumber++] = new LineSegment(points[segpoint[0]], points[segpoint[end]]);
			}
		}
		*/
	}
	
	public int numberOfSegments() {
		return segnumber;
	}
	public LineSegment[] segments() {
		return segarray;
	}
}