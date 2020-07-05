package elementarysorts;

public class Shell {

    public Shell() { }

    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int h = a.length - 1;
        int n = a.length;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j > h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                    }
                }
            }
            h = h/3; // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        }
    }

    public static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
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
