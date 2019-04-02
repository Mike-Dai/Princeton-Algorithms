import java.util.ArrayList;

public class Board {
	private final int[][] blocks;

	public Board(int[][] blocks) {
		this.blocks = blocks;
	}

	public int dimension() {
		return blocks.length;
	}

	private int two2one(int row, int col) {
		return row * dimension() + col + 1;
	}

	private int[] one2two(int num) {
		num--;
		int[] axis = new int[2];
		axis[0] = num / dimension();
		axis[1] = num % dimension();
		return axis;
	}

	private boolean isLegal(int row, int col) {
		int n = dimension();
		if (row >= 0 && row < n && col >= 0 && col < n) return true;
		return false;
	} 

	private void swap(int row1, int col1, int row2, int col2) {
		int tmp;
		tmp = blocks[row1][col1];
		blocks[row1][col1] = blocks[row2][col2];
		blocks[row2][col2] = tmp;
	}

	public int hamming() {
		int sum = 0;
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] == 0) { continue; }
				if (two2one(i, j) != blocks[i][j]) { sum++; }
			}
		}
		return sum;
	}

	public int manhattan() {
		int sum = 0;
		int[] axis = new int[2];
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] == 0) { continue; }
				axis = one2two(blocks[i][j]);
				sum += Math.abs(axis[0] - i) + Math.abs(axis[1] - j);
			}
		}
		return sum;
	}

	public boolean isGoal() {
		for (int i = 0; i < dimension(); i++) {
			for (int j = 0; j < dimension(); j++) {
				if (blocks[i][j] == 0) { continue; }
				if (two2one(i, j) != blocks[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public Board twin() {
		Board atwin = new Board(blocks);
		if (atwin.blocks[0][1] != 0) {
			atwin.swap(0, 0, 0, 1);
		}
		else {
			atwin.swap(0, 0, 1, 0);
		}
		return atwin;
	}

	public boolean equals(Object y) {
		if (y.getClass() != this.getClass()) {
			return false;
		}
		return toString().equals(y.toString());
	}

	public Iterable<Board> neighbors() {
		ArrayList<Board> queue = new ArrayList<Board>();
		int n = dimension();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (blocks[i][j] == 0) {
					if (isLegal(i - 1, j)) {
						Board blocks1 = new Board(blocks);
						blocks1.swap(i, j, i - 1, j);
						queue.add(blocks1);
					}
					if (isLegal(i + 1, j)) {
						Board blocks2 = new Board(blocks);
						blocks2.swap(i, j, i + 1, j);
						queue.add(blocks2);
					}
					if (isLegal(i, j - 1)) {
						Board blocks3 = new Board(blocks);
						blocks3.swap(i, j, i, j - 1);
						queue.add(blocks3);
					}
					if (isLegal(i, j + 1)) {
						Board blocks4 = new Board(blocks);
						blocks4.swap(i, j, i, j + 1);
						queue.add(blocks4);
					}
					return queue;
				}
			}
		}
		return queue;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		int n = dimension();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		return;
	}
}