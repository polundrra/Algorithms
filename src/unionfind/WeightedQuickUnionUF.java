package unionfind;

import java.util.Scanner;

public class WeightedQuickUnionUF {
        private int[] parent;
        private int[] size;
        private int count;

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                size[i] = 1;
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
            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public void readInput(Scanner sc, WeightedQuickUnionUF uf) {
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
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
            uf.readInput(sc, uf);
        }
}
