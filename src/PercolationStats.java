import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] x;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = trials;
        Percolation p = new Percolation(n);
        x = new double[trials];
        for (int i = 0; i < trials; i++) {
            while (!p.percolates()) {
                int row = (int) (StdRandom.uniform() * n) + 1;
                int col = (int) (StdRandom.uniform() * n) + 1;
                if (p.isOpen(row, col)) {
                    continue;
                }
                p.open(row, col);
            }
            x[i] = (double) (p.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, T);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
