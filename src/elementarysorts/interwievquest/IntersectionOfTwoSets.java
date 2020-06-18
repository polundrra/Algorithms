package elementarysorts.interwievquest;

import elementarysorts.Insertion;

public class IntersectionOfTwoSets {
    private static int count;

    private static class Point2D implements Comparable<Point2D> {

        public int x;
        public int y;

        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point2D point) {
            return (this.x - point.x);
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static int intersection(Point2D[] a, Point2D[] b) {
        Insertion.sort(a);
        Insertion.sort(b);
        Insertion.show(a);
        Insertion.show(b);
        int n = a.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (b[j].x < a[i].x) {
                j++;
                }
            if (a[i].x == b[j].x && a[i].y == b[j].y) {
                count++;
            }
            if (j == n - 1) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Point2D[] a = new Point2D[6];
        Point2D[] b = new Point2D[6];
        a[0] = new Point2D(10, 5);
        a[1] = new Point2D(4, 2);
        a[2] = new Point2D(7, 1);
        a[3] = new Point2D(8, 4);
        a[4] = new Point2D(2, 1);
        a[5] = new Point2D(11, 1);
        b[0] = new Point2D(3, 1);
        b[1] = new Point2D(4, 2);
        b[2] = new Point2D(8, 4);
        b[3] = new Point2D(7, 4);
        b[4] = new Point2D(5, 1);
        b[5] = new Point2D(10, 5);
        System.out.println(intersection(a, b));
    }
}



