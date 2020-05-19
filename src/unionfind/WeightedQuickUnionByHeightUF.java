package unionfind;

import java.util.Scanner;

public class WeightedQuickUnionByHeightUF {
    private int[] parent;
    private int[] height;
    private int count;

    public WeightedQuickUnionByHeightUF(int n) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int count() { //number of sets
        return count;
    }

    private int find(int p) { //return root
        int n = parent.length;
        if (p < 0 || p > n) { // validate that p is a valid index
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    private boolean connected(int p, int q) { //Returns true if the two elements are in the same set.
        return find(p) == find(q);
    }

    public void union(int p, int q) { //Merges the set containing element p with the the set containing element q.
        if (connected(p, q)) {
            return;
        }
        int rootP = find(p);
        int rootQ = find(q);
        if (height[rootP] == height[rootQ]) {
            parent[rootQ] = rootP;
            height[rootP]++;
        } else if (height[rootP] < height[rootQ]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootQ] = rootP;
        }
        count--;
    }

    public void readInput(Scanner sc, WeightedQuickUnionByHeightUF uf) {
        while (sc.hasNextInt()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(n);
        uf.readInput(sc, uf);
    }
}
