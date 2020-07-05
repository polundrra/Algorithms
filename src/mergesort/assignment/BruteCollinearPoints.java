package mergesort.assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("null argument to constructor");
        }
        checkNullEntries(points);
        int n = points.length;
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        checkDuplicatedEntries(pointsCopy);
        ArrayList<LineSegment> segmentsList = new ArrayList<>();
        if (pointsCopy.length > 3) {
            for (int i = 0; i < n - 3; i++) {
                for (int j = i + 1; j < n - 2; j++) {
                    for (int k = j + 1; k < n - 1; k++) {
                        for (int l = k + 1; l < n; l++) {
                            if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k]) &&
                                    pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[l])) {
                                LineSegment temp = new LineSegment(pointsCopy[i], pointsCopy[l]);
                                if (!segmentsList.contains(temp)) {
                                    segmentsList.add(temp);
                                }
                            }
                        }
                    }
                }
            }
        }
        segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
    }



    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    private void checkNullEntries(Point[] points) {
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("One of the point in points array is null");
            }
        }
    }

    private void checkDuplicatedEntries(Point[] points) {
        if (points.length > 1) {
            for (int i = 1; i < points.length; i++) {
                if (points[i].compareTo(points[i - 1]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points");
                }
            }
        }
    }
}
