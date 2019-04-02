import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	private Board board;
	private int moves;
	private ArrayList<Board> solution;

	private class SearchNode implements Comparable<SearchNode>{
		private Board board;
		private Board prev;
		private int hamming;
		private int manhattan;

		public SearchNode(Board board) {
			this.board = board;
			hamming = board.hamming();
			manhattan = board.manhattan();
		}

		public int compareTo(SearchNode that) {
			int priority1 = this.hamming + this.manhattan;
			int priority2 = that.hamming + that.manhattan;
			if (priority1 < priority2) {
				return -1;
			}
			else if (priority1 > priority2) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

	public Solver(Board initial) {
		board = initial;
		moves = 0;
		solution = new ArrayList<Board>();
		MinPQ<SearchNode> minpq = new MinPQ<SearchNode>();
		SearchNode node = new SearchNode(board);
		SearchNode min = node;
		while (!min.board.isGoal()) {
			for (Board neighbor : min.board.neighbors()) {
				SearchNode snode = new SearchNode(neighbor);
				minpq.insert(snode);
			}
			min = minpq.delMin();
			moves++;
			solution.add(min.board);
		}
	}

	private int two2one(int row, int col) {
		return row * board.dimension() + col + 1;
	}
/*
	private int[] one2two(int num) {
		num--;
		int[] axis = new int[2];
		axis[0] = num / dimension();
		axis[1] = num % dimension();
		return axis;
	}
*/

	public boolean isSolvable() {
		/*
		int n = board.dimension();
		int inversion;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int number = 
				int order = two2one(i, j) + 1;
				while (order <= n * n) {

					order++;
				}
			}
		}
		*/
		return true;
	}

	public int moves() {
		return this.moves;
	}

	public Iterable<Board> solution() {
		return this.solution;
	}

	public static void main(String[] args) {
		return;
	}
}