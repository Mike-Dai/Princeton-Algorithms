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
		if (initial == null) {
			throw new IllegalArgumentException();
		}
		board = initial;
		moves = 0;
		isSolvable = true;
		solution = new ArrayList<Board>();
		//int valid = 1;
		MinPQ<SearchNode> minpq = new MinPQ<SearchNode>();
		MinPQ<SearchNode> twinpq = new MinPQ<SearchNode>();
		SearchNode node = new SearchNode(board);
		SearchNode twinnode = new SearchNode(board.twin());
		node.prev = null;
		twinnode.prev = null;
		SearchNode min = node;
		SearchNode twinmin = twinnode;
		solution.add(node.sboard);
		while (true) {
			if (min.sboard.isGoal()) {
				isSolvable = true;
				break;
			}
			if (twinmin.sboard.isGoal()) {
				isSolvable = false;
				break;
			}

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

			for (Board twinneighbor : twinmin.sboard.neighbors()) {
				if (!twinneighbor.equals(twinmin.prev)) {
					SearchNode twinsnode = new SearchNode(twinneighbor);
					twinsnode.prev = twinmin.prev;
					twinpq.insert(twinsnode);
				}
			}
			if (!twinpq.isEmpty()) {
				twinmin = twinpq.delMin();
				//moves++;
				//solution.add(min.sboard);
				while (!twinpq.isEmpty()) {
					SearchNode twindel = twinpq.delMin();
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
		return isSolvable;
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