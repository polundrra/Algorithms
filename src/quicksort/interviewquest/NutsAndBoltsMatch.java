package quicksort.interviewquest;

public class NutsAndBoltsMatch {

    public static void main(String[] args) {
        // Nuts and bolts are represented as array of characters 
        char[] nuts = {'@', '#', '$', '%', '^', '&'};
        char[] bolts = {'$', '%', '&', '^', '@', '#'};

        // Method based on quick sort which matches nuts and bolts 
        matchPairs(nuts, bolts, 0, 5);

        System.out.println("Matched nuts and bolts are : ");
        printArray(nuts);
        printArray(bolts);
    }

    private static void printArray(char[] arr) {
        for (char ch : arr){
            System.out.print(ch + " ");
        }
        System.out.println();
    }

    private static void matchPairs(char[] nuts, char[] bolts, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(nuts, lo, hi, bolts[hi]);

            partition(bolts, lo, hi, nuts[pivot]);

            matchPairs(nuts, bolts, lo, pivot - 1);
            matchPairs(nuts, bolts, pivot + 1, hi);
    }
}

    private static int partition(char[] arr, int lo, int hi, char pivot) {
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (arr[j] < pivot) {
                exch(arr, j, i);
                i++;
            } else if (arr[j] == pivot) {
                exch(arr, j, hi);
                j--;
            }
        }
        exch(arr, i, hi);
        return i;
    }

    public static void exch(char[] a, int i, int j) {
        char swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
