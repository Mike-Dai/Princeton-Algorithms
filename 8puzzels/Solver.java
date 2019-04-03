import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
	private Board board;
	private int moves;
	private boolean isSolvable;
	private ArrayList<Board> solution;
	

	private class SearchNode implements Comparable<SearchNode>{
		private Board sboard;
		private Board prev;
		private int smoves;
		private int manhattan;

		public SearchNode(Board board) {
			this.sboard = board;
			smoves = moves;
			manhattan = board.manhattan();
		}

		public int compareTo(SearchNode that) {
			int priority1 = this.smoves + this.manhattan;
			int priority2 = that.smoves + that.manhattan;
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
		isSolvable = true;
		solution = new ArrayList<Board>();
		//int valid = 1;
		MinPQ<SearchNode> minpq = new MinPQ<SearchNode>();
		SearchNode node = new SearchNode(board);
		node.prev = null;
		SearchNode min = node;
		solution.add(node.sboard);
		while (!min.sboard.isGoal()) {
			for (Board neighbor : min.sboard.neighbors()) {
				if (!neighbor.equals(min.prev)) {
					SearchNode snode = new SearchNode(neighbor);
					snode.prev = min.prev;
					minpq.insert(snode);
				}
				/*
				for (Board pred : this.solution()) {
					if (pred.equals(neighbor)) {
						valid = 0;
						break;
					}
				}
				if (valid == 1) {
					SearchNode snode = new SearchNode(neighbor);
					minpq.insert(snode);
				}
				else {
					valid = 1;
				}
				*/
			}
			if (!minpq.isEmpty()) {
				min = minpq.delMin();
				moves++;
				solution.add(min.sboard);
				while (!minpq.isEmpty()) {
					SearchNode del = minpq.delMin();
				}
			}
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
		MinPQ<SearchNode> originpq = new MinPQ<SearchNode>();
		MinPQ<SearchNode> twinpq = new MinPQ<SearchNode>();
		Board twinboard = board.twin();
		SearchNode sorigin = new SearchNode(board);
		SearchNode stwin = new SearchNode(twinboard);
		SearchNode minOrigin = sorigin;
		SearchNode mintwin = stwin;
		while (true) {
			for (Board neighbor : minOrigin.sboard.neighbors()) {
				for (Board pred : this.solution) {
					if (pred.equals(neighbor)) {
						valid = 0;
						break;
					}
				}
				if (valid == 1) {
					SearchNode snode = new SearchNode(neighbor);
					originpq.insert(snode);
				}
				else {
					valid = 1;
				}
			}
			if (!originpq.isEmpty()) {
				minOrigin = originpq.delMin();
				moves++;
				solution.add(minOrigin.sboard);
				while (!originpq.isEmpty()) {
					SearchNode del = originpq.delMin();
				}
			}
		}
		*/
		return true;
	}

	public int moves() {
		if (isSolvable()) {
			return this.moves;
		}
		else {
			return -1;
		}
	}

	public Iterable<Board> solution() {
		if (isSolvable()) {
			return this.solution;
		}
		else {
			return null;
		}
	}

	public static void main(String[] args) {
		return;
	}
}