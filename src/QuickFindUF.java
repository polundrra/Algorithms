import java.util.Scanner;

public class QuickFindUF {
        private int[] id; // id[i] = component identifier of i
        private int count; // number of components

        public QuickFindUF(int n) {
            count = n;
            id = new int[n];
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
            }
        }

        public int count() { //number of sets
            return count;
        }

        private int find(int p) { //return root
            int n = id.length;
            if (p < 0 || p > n ) { // validate that p is a valid index
                throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
            }
            return id[p];
        }

        private boolean connected(int p, int q) { //Returns true if the two elements are in the same set.
            return find(p) == find(q);
        }

        public void union(int p, int q) { //Merges the set containing element p with the the set containing element q.
            if (connected(p, q)) {
                return;
            }
            int idP = id[p];
            int idQ = id[q];
            for(int i = 0; i < id.length; i++) {
                if (id[i] == idP) {
                    id[i] = idQ;
                }
            }
            count--;
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            QuickFindUF uf = new QuickFindUF(n);
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
