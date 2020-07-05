package elementarysorts.interwievquest;

import java.util.Arrays;

public class Permutation {

    public static boolean isPermutation(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] b = { 1, 3, 4, 6 };
        int[] c = { 1, 3, 5, 7, 9, 2, 4, 6, 8 };
        System.out.println(isPermutation(a, b));
        System.out.println(isPermutation(a, c));
        System.out.println(isPermutation(a, a));
        System.out.println(isPermutation(b, c));
    }
}
