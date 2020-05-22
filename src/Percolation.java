import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private boolean[][] isopen;
    private int opensites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be bigger than 0");
        }
        int newn = n + 2;
        isopen = new boolean[newn][newn];
        grid = new int[newn][newn];
        int idx = 1;
        for (int i = 1; i < newn - 1; i++) {
            for (int j = 1; j < newn - 1; j++) {
                grid[i][j] = idx;
                isopen[i][j] = false;
                idx++;
            }
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 1; i < newn - 1; i++) {
            uf.union(0, grid[1][i]);
        }
        for (int i = newn - 2; i >= 1; i--) {
            uf.union(n * n + 1, grid[newn - 2][i]);
        }
    }

    private void validate(int row, int col) {
        int n = grid.length - 2;
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int site = grid[row][col];
        int[] neighbours = new int[4];
        neighbours[0] = grid[row - 1][col];
        neighbours[1] = grid[row][col + 1];
        neighbours[2] = grid[row + 1][col];
        neighbours[3] = grid[row][col - 1];
        boolean[] opened = new boolean[4];
        opened[0] = isOpen(row - 1, col);
        opened[1] = isOpen(row, col + 1);
        opened[2] = isOpen(row + 1, col);
        opened[3] = isOpen(row, col - 1);
        for (int i = 0; i < 4; i ++) {
            if (neighbours[i] != 0 && opened[i]) {
                uf.union(site, neighbours[i]);
            }
        }
        isopen[row][col] = true;
        opensites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isopen[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int site = grid[row][col];
        int n = grid[0].length;
        for (int i = 1; i < n - 1; i++) {
            if (uf.find(site) == uf.find(grid[1][i])) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opensites;
    }

    // does the system percolate?
    public boolean percolates() {
        int n = grid.length - 2;
        return uf.find(0) == uf.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        for (int i = 0; i < p.grid.length; i++) {
            for (int j = 0; j < p.grid[0].length; j++) {
                System.out.print(p.grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println(p.isOpen(1, 1));
        p.open(1, 2);
        System.out.println(p.isOpen(1, 2));
        p.open(2, 2);
        System.out.println(p.isOpen(2, 2));
        System.out.println(p.isFull(2, 2));
        System.out.println(p.numberOfOpenSites());
        p.open(3, 2);
        p.open(1, 1);
        p.open(2, 1);
        System.out.println(p.isFull(2, 1));
        System.out.println(p.isFull(2, 3));
        System.out.println(p.isOpen(1, 3));
        System.out.println(p.uf.find(1) + " " + p.uf.find(6));
        System.out.println(p.numberOfOpenSites());
        System.out.println(p.percolates());
    }
}
