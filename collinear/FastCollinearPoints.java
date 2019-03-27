import java.util.Arrays;

public class FastCollinearPoints {
	//private Point[] alux;
	private LineSegment[] segarray;
	private int number;
	//private Double[] segslope;

	public FastCollinearPoints(Point[] points) {
		//segnumber = 0;
		number = 0;
		segarray = new LineSegment[points.length];
		//segslope = new Double[points.length];
		Point[] alux = points;
		int same = 0;
		//Point origin = new Point(0, 0);
		Arrays.sort(alux);
		for (int i = 0; i < alux.length - 1; i++) {
			if (alux[i].compareTo(alux[i+1]) == 0) {
				throw new IllegalArgumentException();
			}
		}
		for (int i = 0; i < alux.length - 3; i++) {
			Arrays.sort(alux);
			Point p = alux[i];
			Arrays.sort(alux, p.slopeOrder());
			for (int j = 0, first = 1, last = 2; last < alux.length; last++) {
				while (last < alux.length && Double.compare(alux[j].slopeTo(alux[first]), alux[j].slopeTo(alux[last])) == 0) {
					last++;
				} 
				if (last - first >= 3 && alux[j].compareTo(alux[first]) < 0) {
					segarray[number] = new LineSegment(alux[j], alux[last - 1]);
					number++;
				}
				first = last;
			}
			/*
			while (j < alux.length - 3) {
				Point q = alux[j], s = alux[j+2];
				int valid = 1;
				if (p.slopeTo(q) == p.slopeTo(s)) {
					j++;
				}
				else if (p.slopeTo(q) == p.slopeTo(s)) {
					Point max = q;
					int offset;
					for (offset = 0; p.slopeTo(q) == p.slopeTo(alux[j+offset]) && j + offset < alux.length && valid == 1; offset++) {
						if (alux[j+offset].compareTo(max) > 0) {
							max = alux[j+offset];
						}
						if (p.compareTo(alux[j+offset]) > 0) {
							valid = 0;
						}
						else if (p.compareTo(alux[j+offset]) == 0) {
							same = 1;
						}
					}
					j += offset;
					if (valid == 1 && offset - same >= 2) {
						segarray[number++] = new LineSegment(p, max);
						same = 0;
						
						Point pmax = q;
						int imax = j;
						int lmax = Math.abs(p.compareTo(q));

						if (j < alux.length - 3) {
							Point t = alux[j+3];
							if (p.slopeTo(t) == slope) {
								for (int y = j; y <= j+3; y++) {
									if (Math.abs(p.compareTo(alux[y])) > lmax) {
										imax = y;
										pmax = alux[y];
										lmax = Math.abs(p.compareTo(alux[y]));
									}
								}
								segarray[segnumber] = new LineSegment(p, pmax);
								segslope[segnumber] = slope;
								segnumber++;
							}
						}
						for (int y = j; y <= j+2; y++) {
							if (Math.abs(p.compareTo(alux[y])) > lmax) {
								imax = y;
								pmax = alux[y];
								lmax = Math.abs(p.compareTo(alux[y]));
							}
						}
						segarray[segnumber] = new LineSegment(p, pmax);
						segslope[segnumber] = slope;
						segnumber++;
						
					}
				}
			}
			*/
		}
		/*
		for (int i = 0; i < alux.length; i++) {
			for (int j = i + 1; j < alux.length; j++) {
				for (int k = j + 1; k < alux.length; k++) {
					for (int l = k + 1; l < alux.length; l++) {
						Point p = alux[i], q = alux[j], r = alux[k], s = alux[l];
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

		LineSegment[] copy = new LineSegment[number];
		for (int i = 0; i < number; i++) {
			copy[i] = segarray[i];
		}
		segarray = copy;
	}

	public int numberOfSegments() {
		return number;
	}

	public LineSegment[] segments() {
		return segarray;
	}
}