/*        Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed immediately
        by a decreasing sequence of integers. Write a program that, given a bitonic array of nn distinct integer values,
        determines whether a given integer is in the array.

        Standard version: Use ∼ 3 lg n compares in the worst case.
        Signing bonus: Use ∼ 2 lg n compares in the worst case (and prove that no algorithm can guarantee to
                perform fewer than ∼ 2 lg n compares in the worst case).*/

package analysisOfAlgorithms.interviewquestions;

public class BitonicSearch {

    public int recursiveBitonicSearch(int[] arr, int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                return recursiveBitonicSearch(arr, mid + 1, hi);
            } else {
                return recursiveBitonicSearch(arr, lo, mid);
            }
        }
        return -1;
    }

    public int bitonicSearch(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                lo = mid + 1;
            } else if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int binarySearch(int[] arr, int lo, int hi, int key) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[lo] < arr[hi]) {
                if(arr[mid] > key) {
                    hi = mid - 1;
                } else if (arr[mid] < key) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            } else {
                if(arr[mid] < key) {
                    hi = mid - 1;
                } else if (arr[mid] > key) {
                    lo = mid + 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    public int standartSearch(int[] arr, int key) { //3 lg n compares in the worst case.
        //find a max with binary search
        int max = bitonicSearch(arr);
        if (arr[max] == key) {
            return max;
        } else {
            // do binary search at left half, than at right half.
            int mid1 = binarySearch(arr, 0, max - 1, key);
            int mid2 = binarySearch(arr, max + 1, arr.length - 1, key);
            return Math.max(mid1, mid2);
        }
    }

    public int betterSearch(int[] arr, int lo, int hi, int key) {
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == key) {
            return mid;
        }
        if (arr[mid] > arr[mid - 1]) {
            if (arr[mid] < key) {
                return betterSearch(arr, mid + 1, hi, key);
            } else {
                int idx1 = binarySearch(arr, lo, mid, key);
                int idx2 = binarySearch(arr, mid + 1, hi, key);
                return Math.max(idx1, idx2);
            }
        } else {
            if (arr[mid] < key) {
                return betterSearch(arr, lo, mid, key);
            } else {
                int idx1 = binarySearch(arr, lo, mid, key);
                int idx2 = binarySearch(arr, mid + 1, hi, key);
                return Math.max(idx1, idx2);
            }
        }
    }

    public static void main(String[] args) {
         int[] arr = {0, 2, 4, 6, 10, 22, 30, 18, 12, 8, 5, 3};
        BitonicSearch bs = new BitonicSearch();
        int a = bs.standartSearch(arr, 0);
        int b = bs.betterSearch(arr, 0, arr.length - 1, 0);
        System.out.println(a + " " + b);
        int x = bs.bitonicSearch(arr);
        int y = bs.recursiveBitonicSearch(arr, 0, arr.length - 1);
        System.out.println(x + " " + y);
    }
}
