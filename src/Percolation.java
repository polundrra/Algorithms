import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private WeightedQuickUnionUF uf_top_bottom;
    private WeightedQuickUnionUF uf_top;
    private boolean[][] is_open;
    private int opened_sites;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be bigger than 0");
        }
        this.n = n;
        grid = new int[n][n];
        is_open = new boolean[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = idx;
                is_open[i][j] = false;
                idx++;
            }
        }
        uf_top_bottom = new WeightedQuickUnionUF(n * n + 2); //uf instance with connection with top and bottom
        for (int i = 0; i < n; i++) {
            uf_top_bottom.union(n * n, grid[0][i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            uf_top_bottom.union(n * n + 1, grid[n - 1][i]);
        }
        uf_top = new WeightedQuickUnionUF(n * n + 1); // uf instance with connection only with top. All for correctness of isFull() method.
        for (int i = 0; i < n; i++) {
            uf_top_bottom.union(n * n, grid[0][i]);
        }
    }

        private void validate(int row, int col) {
            if (row < 1 || row > n || col < 1 || col > n) {
                throw new IllegalArgumentException("Indices must be between 1 and n");
            }
        }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int site = grid[row - 1][col - 1];
        int cur_row = row - 1;
        int cur_col = col - 1;
        if (cur_row == 0) {
            int neighbour3 = grid[cur_row + 1][cur_col];
            if (isOpen(row + 1, col)) {
                uf_top_bottom.union(site, neighbour3);
                uf_top.union(site, neighbour3);
            }
            if (cur_col == 0) {
                int neighbour2 = grid[cur_row][cur_col + 1];
                if (isOpen(row, col + 1)) {
                    uf_top_bottom.union(site, neighbour2);
                    uf_top.union(site, neighbour2);
                }
            } else if (cur_col == n - 1) {
                int neighbour4 = grid[cur_row][cur_col - 1];
                if (isOpen(row, col - 1)) {
                    uf_top_bottom.union(site, neighbour4);
                    uf_top.union(site, neighbour4);
                }
            } else {
                int neighbour2 = grid[cur_row][cur_col + 1];
                if (isOpen(row, col + 1)) {
                    uf_top_bottom.union(site, neighbour2);
                    uf_top.union(site, neighbour2);
                }
                int neighbour4 = grid[cur_row][cur_col - 1];
                if (isOpen(row, col - 1)) {
                    uf_top_bottom.union(site, neighbour4);
                    uf_top.union(site, neighbour4);
                }
            }
        } else if (cur_row == n - 1) {
            int neighbour1 = grid[cur_row - 1][cur_col];
            if (isOpen(row - 1, col)) {
                uf_top_bottom.union(site, neighbour1);
                uf_top.union(site, neighbour1);
            }
            if (cur_col == 0) {
                int neighbour2 = grid[cur_row][cur_col + 1];
                if (isOpen(row, col + 1)) {
                    uf_top_bottom.union(site, neighbour2);
                    uf_top.union(site, neighbour2);
                }
            } else if (cur_col == n - 1) {
                int neighbour4 = grid[cur_row][cur_col - 1];
                if (isOpen(row, col - 1)) {
                    uf_top_bottom.union(site, neighbour4);
                    uf_top.union(site, neighbour4);
                }
            } else {
                int neighbour2 = grid[cur_row][cur_col + 1];
                if (isOpen(row, col + 1)) {
                    uf_top_bottom.union(site, neighbour2);
                    uf_top.union(site, neighbour2);
                }
                int neighbour4 = grid[cur_row][cur_col - 1];
                if (isOpen(row, col - 1)) {
                    uf_top_bottom.union(site, neighbour4);
                    uf_top.union(site, neighbour4);
                }
            }
        } else {
            int neighbour1 = grid[cur_row - 1][cur_col];
            if (isOpen(row - 1, col)) {
                uf_top_bottom.union(site, neighbour1);
                uf_top.union(site, neighbour1);
            }
            int neighbour3 = grid[cur_row + 1][cur_col];
            if (isOpen(row + 1, col)) {
                uf_top_bottom.union(site, neighbour3);
                uf_top.union(site, neighbour3);
            }
            if (cur_col == 0) {
                int neighbour2 = grid[cur_row][cur_col + 1];
                if (isOpen(row, col + 1)) {
                    uf_top_bottom.union(site, neighbour2);
                    uf_top.union(site, neighbour2);
                }
            } else if (cur_col == n - 1) {
                int neighbour4 = grid[cur_row][cur_col - 1];
                if (isOpen(row, col - 1)) {
                    uf_top_bottom.union(site, neighbour4);
                    uf_top.union(site, neighbour4);
                }
            } else {
                int neighbour2 = grid[cur_row][cur_col + 1];
                if (isOpen(row, col + 1)) {
                    uf_top_bottom.union(site, neighbour2);
                    uf_top.union(site, neighbour2);
                }
                int neighbour4 = grid[cur_row][cur_col - 1];
                if (isOpen(row, col - 1)) {
                    uf_top_bottom.union(site, neighbour4);
                    uf_top.union(site, neighbour4);
                }
            }
        }
        is_open[row - 1][col - 1] = true;
        opened_sites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return is_open[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int site = grid[row - 1][col - 1];
        for (int i = 0; i < n; i++) {
            if (uf_top.find(site) == uf_top.find(grid[0][i])) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opened_sites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf_top_bottom.find(n * n) == uf_top_bottom.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        for (int i = 0; i < p.grid.length; i++) {
            for (int j = 0; j < p.grid[0].length; j++) {
                System.out.print(p.grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        while (!p.percolates()) {
            int row = (int) (StdRandom.uniform() * 10) + 1;
            int col = (int) (StdRandom.uniform() * 10) + 1;
            if (p.isOpen(row, col)) {
                continue;
            }
            p.open(row, col);
        }
        double x = (double) p.numberOfOpenSites() / 100;
        System.out.println(x);
    }
}
