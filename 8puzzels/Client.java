import edu.princeton.cs.algs4.In;

public class Client {
	public static void main(String[] args) {

	    // create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);
	    /*
	    for (Board board : initial.neighbors()) {
	    	System.out.print(board.toString());
	    }
	    */
	}
}