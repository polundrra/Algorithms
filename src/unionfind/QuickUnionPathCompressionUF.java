package unionfind;

import java.util.Scanner;

public class QuickUnionPathCompressionUF {
    private int[] parent;
    private int count;

    public QuickUnionPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public int count() { //number of sets
        return count;
    }

    private int find(int p) { //return root
        validate(p);
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (p != root) {
            int newp = parent[p];
            parent[newp] = root;
            p = newp;
        }
        return root;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p > n) { // validate that p is a valid index
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
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
        parent[rootP] = rootQ;
        count--;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(n);
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

