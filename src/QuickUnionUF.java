import java.util.Scanner;

public class QuickUnionUF {
    private int[] parent;
    private int count;

    public QuickUnionUF(int n) {
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
        parent[rootP] = rootQ;
        count--;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        QuickUnionUF uf = new QuickUnionUF(n);
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
