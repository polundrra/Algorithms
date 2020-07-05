package elementarysorts;

import java.util.Comparator;

public class Insertion {

    private Insertion() { }

    // using natural order and Comparable interface
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
            }
        }
    }

    // using custom-order and Comparator interface
    public static <Key> void sort(Key[] a, Comparator<Key> comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(comparator, a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
            }
        }
    }

    public static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static <Key> boolean less(Comparator<Key> comparator, Key v, Key w) {
        return comparator.compare(v, w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static <Key extends Comparable<Key>> boolean isSorted(Key[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static <Key extends Comparable<Key>> boolean isSorted(Key[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (!less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static <Key> boolean isSorted(Key[] a, Comparator<Key> comparator) {
        return isSorted(comparator, a, 0, a.length - 1);
    }

    public static <Key> boolean isSorted(Comparator<Key> comparator, Key[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            if (!less(comparator, a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }


    public static void show(Object[] a) {
        for (int     i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String line = "bed bug dad yes zoo ... all bad yet";
        String[] words = line.split(" ");
        Selection.sort(words);
        show(words);
    }
}
