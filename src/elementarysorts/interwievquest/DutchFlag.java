package elementarysorts.interwievquest;

import java.util.Arrays;
import java.util.Random;

public class DutchFlag {

    public static String dutchSort(int n) {
        int[] a = new int[n];
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(3);
        }
        int lo = 0;
        int mid = 0;
        int hi = n - 1;
        for (int i = 0; i < a.length; i++) {
            while (mid <= hi) {
                if (a[mid] == 0) {
                    int temp = a[mid];
                    a[mid] = a[lo];
                    a[lo] = temp;
                    lo++;
                    mid++;
                } else if (a[mid] == 1) {
                    mid++;
                } else {
                    int temp = a[mid];
                    a[mid] = a[hi];
                    a[hi] = temp;
                    hi--;
                }
            }
        }
        return Arrays.toString(a);
    }

    public static void main(String[] args) {
        System.out.println(dutchSort(10));
    }
}
