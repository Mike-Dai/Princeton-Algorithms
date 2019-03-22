import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double constant = 1.96;
	private final double[] threshold;
	private final int number;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid 
    {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
    	number = trials;
    	threshold = new double[trials];
    	for (int i = 0; i < trials; i++) {
    		Percolation site = new Percolation(n);
    		while (!site.percolates()) {
    			int row = StdRandom.uniform(1, n+1);
    			int col = StdRandom.uniform(1, n+1);
    			site.open(row, col);
    		}
    		threshold[i] = (double) site.numberOfOpenSites();
            threshold[i] /= (n * n);
    	}
    }

    public double mean()                          // sample mean of percolation threshold
    {
    	mean = StdStats.mean(threshold);
        return mean;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
    	stddev = StdStats.stddev(threshold);
        return stddev;
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
    	double low = this.mean - constant * Math.sqrt(this.stddev) / Math.sqrt(this.number);
    	return low;
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
    	double high = this.mean + constant * Math.sqrt(this.stddev) / Math.sqrt(this.number);
    	return high;
    }

    public static void main(String[] args)        // test client (described below)
    {
    	return;
    }
}