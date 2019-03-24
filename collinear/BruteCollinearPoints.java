import java.utils.Arrays;

public class BruteCollinearPoints {
	private final Point[] points;
	private final LineSegment segments;
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
		this.segnumber = 0;
		this.points = points;
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
				segments[signumber++] = new LineSegment(points[segpoint[0]], points[segpoint[end]]);
			}
		}
	}
	public int numberOfSegments() {
		return segnumber;
	}
	public LineSegment[] segments() {
		return segments;
	}
}