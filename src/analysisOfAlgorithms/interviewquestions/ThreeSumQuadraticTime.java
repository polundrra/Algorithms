/*3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n^2
        in the worst case. You may assume that you can sort the n integers in time proportional to n^2  or better.
        Ex.
 -25 -10 -7 -3 2 4 8 10  (a+b+c==-25)
 -25 -10 -7 -3 2 4 8 10  (a+b+c==-22)
 . . .
 -25 -10 -7 -3 2 4 8 10  (a+b+c==-7)
 -25 -10 -7 -3 2 4 8 10  (a+b+c==-7)
 -25 -10 -7 -3 2 4 8 10  (a+b+c==-3)
 -25 -10 -7 -3 2 4 8 10  (a+b+c==2)
 -25 -10 -7 -3 2 4 8 10  (a+b+c==0)
        */



package analysisOfAlgorithms.interviewquestions;

public class ThreeSumQuadraticTime {

    private int[] sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public void threeSum(int[] arr) {
        int[] sortedArr = sort(arr);
        int n = sortedArr.length;
        for (int i = 0; i < n - 2; i++) {
            int curr = sortedArr[i];
            int start = i + 1;
            int end = n - 1;

            while (start < end) {
                int s = sortedArr[start];
                int e = sortedArr[end];
                if (curr + s + e == 0) {
                    System.out.println(curr + " + " + s + " + " + e + " = 0");
                    start++;
                    end--;
                } else if (curr + s + e < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
    }


    public static void main (String[] args) {
        int[] arr = {30, -40, -20, -10, 40, 0, 10, 5};
        ThreeSumQuadraticTime sum = new ThreeSumQuadraticTime();
        sum.threeSum(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }
}
