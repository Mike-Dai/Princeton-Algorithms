import java.util.Arrays;

public class FastCollinearPoints {
	private Point[] mypoints;
	private LineSegment[] segarray;
	private int segnumber;
	private Double[] segslope;

	public FastCollinearPoints(Point[] points) {
		segnumber = 0;
		segarray = new LineSegment[points.length];
		segslope = new Double[points.length];
		mypoints = points;

		Point origin = new Point(0, 0);
		Arrays.sort(mypoints, origin.slopeOrder());
		for (int i = 0; i < mypoints.length - 1; i++) {
			if (mypoints[i].compareTo(mypoints[i+1]) == 0) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = 0; i < mypoints.length; i++) {
			Point p = mypoints[i];
			Arrays.sort(mypoints, p.slopeOrder());
			for (int j = 1; j < mypoints.length - 2;j++) {
				Point q = mypoints[j], r = mypoints[j+1], s = mypoints[j+2];
				int valid = 1;
				if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
					double slope = p.slopeTo(q);
					for (int x = 0; x < segnumber; x++) {
						if (slope == segslope[x]) {
							valid = 0;
							break;
						}
					}
					if (valid == 1) {
						Point pmax = q;
						int imax = j;
						int lmax = Math.abs(p.compareTo(q));

						if (j < mypoints.length - 3) {
							Point t = mypoints[j+3];
							if (p.slopeTo(t) == slope) {
								for (int y = j; y <= j+3; y++) {
									if (Math.abs(p.compareTo(mypoints[y])) > lmax) {
										imax = y;
										pmax = mypoints[y];
										lmax = Math.abs(p.compareTo(mypoints[y]));
									}
								}
								segarray[segnumber] = new LineSegment(p, pmax);
								segslope[segnumber] = slope;
								segnumber++;
							}
						}
						for (int y = j; y <= j+2; y++) {
							if (Math.abs(p.compareTo(mypoints[y])) > lmax) {
								imax = y;
								pmax = mypoints[y];
								lmax = Math.abs(p.compareTo(mypoints[y]));
							}
						}
						segarray[segnumber] = new LineSegment(p, pmax);
						segslope[segnumber] = slope;
						segnumber++;
					}
					
				}
			}
		}
		/*
		for (int i = 0; i < mypoints.length; i++) {
			for (int j = i + 1; j < mypoints.length; j++) {
				for (int k = j + 1; k < mypoints.length; k++) {
					for (int l = k + 1; l < mypoints.length; l++) {
						Point p = mypoints[i], q = mypoints[j], r = mypoints[k], s = mypoints[l];
						if (p.slopeTo(q) == q.slopeTo(r) && q.slopeTo(r) == r.slopeTo(s)) {
							
							int low = i, high = l;
							Arrays.sort(points, low, high, slopeOrder);
							p = points[i];
							s = points[l];
							
							segarray[segnumber] = new LineSegment(p, s);
							segnumber++;
						}
					}
				}
			}
		}
		*/

		LineSegment[] copy = new LineSegment[segnumber];
		for (int i = 0; i < segnumber; i++) {
			copy[i] = segarray[i];
		}
		segarray = copy;
	}

	public int numberOfSegments() {
		return segnumber;
	}

	public LineSegment[] segments() {
		return segarray;
	}
}