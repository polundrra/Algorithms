package mergesort.assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("null argument to constructor");
        }
        checkNullEntries(points);
        Point[] pointsCopyNO = points.clone();
        Point[] pointsCopySO = points.clone();
        Arrays.sort(pointsCopyNO);
        checkDuplicatedEntries(pointsCopyNO);
        ArrayList<LineSegment> segmentsList = new ArrayList<>();
        if (pointsCopyNO.length > 3) {
            for (int i = 0; i < pointsCopyNO.length; i++) {
                Point origin = pointsCopyNO[i];
                Arrays.sort(pointsCopySO);
                Arrays.sort(pointsCopySO, origin.slopeOrder());
                int count = 1;
                Point lineBeginning = null;
                for (int j = 0; j < pointsCopySO.length - 1; j++) {
                    if (pointsCopySO[j].slopeTo(origin) == pointsCopySO[j + 1].slopeTo(origin)) {
                        count++;
                        if (count == 2) {
                            lineBeginning = pointsCopySO[j];
                            count++;
                        } else if (count >= 4 && j + 1 == pointsCopySO.length - 1) {
                            if (lineBeginning.compareTo(origin) > 0) {
                                segmentsList.add(new LineSegment(origin, pointsCopySO[j + 1]));
                            }
                            count = 1;
                        }
                    } else if (count >= 4) {
                        if (lineBeginning.compareTo(origin) > 0) {
                            segmentsList.add(new LineSegment(origin, pointsCopySO[j]));
                        }
                        count = 1;
                    }
                    else {
                        count = 1;
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
            for (int i = 0; i < points.length - 1; i++) {
                if (points[i].compareTo(points[i + 1]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points");
                }
            }
        }
    }
}
