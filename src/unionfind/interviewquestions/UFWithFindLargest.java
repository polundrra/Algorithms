/* Union-find with specific canonical element.
 * Add a method find() to the union-find data type so that find(i)
 * returns the largest element in the connected component containing i.
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is {1,2,6,9},
 * then the find() method should return 9 for each of the four elements in the connected components.
 *
 * Solution: using Weighted Quick union with find() method updating.
 */


package unionfind.interviewquestions;

import java.util.Scanner;

public class UFWithFindLargest {
    public int[] parent;
    private int count;
    private int[] size;
    public int[] max;

    public UFWithFindLargest(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        max = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }

    public int count() { //number of sets
        return count;
    }

    public int root(int p) { //return root
        int n = parent.length;
        if (p < 0 || p >= n) { // validate that p is a valid index
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
        int max = p;
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public int find(int p) {
        return max[root(p)];
    }

    private boolean connected(int p, int q) { //Returns true if the two elements are in the same set.
        return root(p) == root(q);
    }

    public void union(int p, int q) { //Merges the set containing element p with the the set containing element q.
        if (connected(p, q)) {
            return;
        }
        int rootP = root(p);
        int rootQ = root(q);
        int largestP = max[rootP];
        int largestQ = max[rootQ];
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            if (largestQ > largestP) {
                max[rootP] = largestQ;
            }
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
            if (largestP > largestQ) {
                max[rootQ] = largestP;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        UFWithFindLargest uf = new UFWithFindLargest(n);
        while (sc.hasNextInt()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
        System.out.println(uf.count() + " components");
    }
}
