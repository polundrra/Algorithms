package quicksort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        Comparable v = a[lo];
        int i = lo + 1;
        int lt = lo;
        int gt = hi;
        while (i <= gt) {
            if (less(a[i], v)) {
                exch(a, i++, lt++);
            } else if (less(v, a[i])) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
        assert isSorted(a, lo, hi);
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (!less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]);
        }
    }

    public static void main(String[] args) {
        String s = "S O R T E X A M P L E";
        String[] a = s.split(" ");
        Quick3way.sort(a);
        show(a);
        assert isSorted(a);
    }
}
