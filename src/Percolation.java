import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[][] grid;
    private final WeightedQuickUnionUF index;
    private final WeightedQuickUnionUF topIndex;
    private final int n;
    private int openCnt = 0;
    private final int top;
    private final int bottom;

    private enum Side {
        RIGHT,
        BOTTOM,
        TOP,
        LEFT
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be bigger than 0");
        }
        this.n = n;
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        index = new WeightedQuickUnionUF(n * n + 2); // uf instance with connection with top and bottom
        topIndex = new WeightedQuickUnionUF(n * n + 1); // uf instance with connection only with top. All for correctness of isFull() method.
        top = n * n;
        bottom = n * n + 1;
        for (int i = 0; i < n; i++) {
            topIndex.union(top, i);
            index.union(top, i);
            index.union(bottom, n * n - 1 - i);
        }
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Indices must be between 1 and n");
        }
    }

    private int toIdx(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    private void union(int row, int col, Side s) {
        index.union(toIdx(row, col), getNeighbourIdx(row, col, s));
        topIndex.union(toIdx(row, col), getNeighbourIdx(row, col, s));
    }

    private boolean isOpened(int row, int col, Side s) {
        if (s == Side.TOP && row == 1
                || s == Side.BOTTOM && row == n
                || s == Side.LEFT && col == 1
                || s == Side.RIGHT && col == n)
            return false;

        switch (s) {
            case BOTTOM:
                return isOpen(row + 1, col);
            case RIGHT:
                return isOpen(row, col + 1);
            case LEFT:
                return isOpen(row, col - 1);
            case TOP:
                return isOpen(row - 1, col);
        }

        return false;
    }

    private int getNeighbourIdx(int row, int col, Side s) {
        switch (s) {
            case TOP:
                return toIdx(row - 1, col);
            case LEFT:
                return toIdx(row, col - 1);
            case RIGHT:
                return toIdx(row, col + 1);
            case BOTTOM:
                return toIdx(row + 1, col);
        }

        return -1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }

        grid[row - 1][col - 1] = true;
        openCnt++;

        for (Side s : Side.values()) {
            if (isOpened(row, col, s)) {
                union(row, col, s);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return topIndex.find(toIdx(row, col)) == topIndex.find(top) && isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCnt;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1) {
            return isOpen(1, 1);
        }
        return index.find(top) == index.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 10;
        Percolation p = new Percolation(n);
        System.out.println(p.index.find(100) + " " + p.index.find(101));
        while (!p.percolates()) {
            int row = (int) (StdRandom.uniform() * n) + 1;
            int col = (int) (StdRandom.uniform() * n) + 1;
            p.open(row, col);
        }
        double x = (double) p.numberOfOpenSites() / n / n;
        System.out.println(x);
        for (int i = 0; i < p.grid.length; i++) {
            for (int j = 0; j < p.grid[0].length; j++) {
                System.out.print(p.grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
