import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    
	private static final int BLOCKED = 0;
	private static final int OPEN = 1;
	private static final int FULL = 2;
	private final WeightedQuickUnionUF uf1, uf2;
	private int[][] site;
	private final int sidelen;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    	sidelen = n;
        validate(n);
    	uf1 = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
    	/*
        for (int i = 1; i <= n; i++) {
    		uf.union(i, 0);
    	}
    	for (int i = n * n - n + 1; i <= n * n; i++) {
    		uf.union(i, n * n + 1);
    	}
        */
    	site = new int[n + 2][n + 2];
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			site[i][j] = BLOCKED;
    		}
    	}
    }

    private void validate(int n) {
        if (n <= 0 || n > sidelen) {
            throw new IllegalArgumentException();
        }
        return;
    }

    private void union(int p, int q) {
        uf1.union(p, q);
        uf2.union(p, q);
    }

    private int[] one2two(int index) {
        int[] axis = new int[2];
        axis[1] = index % sidelen;
        axis[0] = index / sidelen + 1;
        return axis;
    }

    private int two2one(int row, int col) {
        return (row - 1) * sidelen + col;
    }

    public    void open(int row, int col)    // open site (row, col) if it is not open already
    {
        validate(row);
        validate(col);
    	if (site[row][col] == BLOCKED) {
    		site[row][col] = OPEN;
            if (row == 1) {
                site[row][col] = FULL;
                union(0, two2one(row, col));
            }
    		if (row - 1 >= 1 && (site[row-1][col] == OPEN || site[row-1][col] == FULL)) {
    			union(two2one(row, col), two2one(row - 1, col));
    		}
    		if (row + 1 <= sidelen && (site[row+1][col] == OPEN || site[row+1][col] == FULL)) {
    			union(two2one(row, col), two2one(row + 1, col));
    		}
    		if (col - 1 >= 1 && (site[row][col-1] == OPEN || site[row][col-1] == FULL)) {
    			union(two2one(row, col), two2one(row, col - 1));
    		}
    		if (col + 1 <= sidelen && (site[row][col+1] == OPEN || site[row][col+1] == FULL)) {
    			union(two2one(row, col), two2one(row, col + 1));
    		}
    		
            if (row == sidelen) {
                // site[row][col] = OPEN;
                uf1.union(sidelen * sidelen, two2one(row, col));
            }
            /*
			if (site[row][col] == FULL || row - 1 >= 1 && site[row-1][col] == FULL || row + 1 <= sidelen && site[row+1][col] == FULL || col - 1 >= 1 && site[row][col-1] == FULL || col + 1 <= sidelen && site[row][col+1] == FULL) 
    		{	
                site[row][col] = FULL;
                int root = uf.find(two2one(row, col));
                int[] axis = one2two(root);
                site[axis[0]][axis[1]] = FULL;
    		}
            */
    	}
    	else {
    		return;
    	}
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        validate(row);
        validate(col);
    	if (site[row][col] == BLOCKED) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        validate(row);
        validate(col);
        /*
    	int root = uf.find(two2one(row, col));
        int[] axis = new int[2];
        axis = one2two(root);
        if (site[axis[0]][axis[1]] == FULL) {
            return true;
        }
        return false;
        */
        
        if (uf2.connected(two2one(row, col), 0)) {
            return true;
        }
        return false;
        
    }

    public     int numberOfOpenSites()       // number of open sites
    {
    	int number = 0;
    	for (int i = 1; i <= sidelen; i++) {
    		for (int j = 1; j <= sidelen; j++) {
    			if (isOpen(i, j)) { number++; }
    		}
    	}
    	return number;
    }

    public boolean percolates()              // does the system percolate?
    {
    	return uf1.connected(0, sidelen * sidelen);
    }

    public static void main(String[] args)   // test client (optional)
    {
    	return;
    }
}