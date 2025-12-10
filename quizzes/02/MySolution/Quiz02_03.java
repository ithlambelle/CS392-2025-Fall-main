//
// HX-2025-11-20: 50 points
// Some "hard" Sudoku puzzles can be
// found here: https://sudoku.com/hard/.
// This question is similar to Assign07_02.
// You are asked to use DFirstEnumerate and BFirstEnumerate
// in FnGtree to solve Sudoku puzzles. Your solution
// should be able to solve "hard" Sudoku puzzles effectively.
//
import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;
//
public class Quiz02_03 {
    // Functional Sudoku state that can be explored as a generic tree.
    public static class Sudoku implements FnGtree<Sudoku> {
	private static final int SIZE = 9;
	private final int[][] board = new int[SIZE][SIZE];

	public Sudoku(int[][] input) {
	    for (int r = 0; r < SIZE; r++) {
		System.arraycopy(input[r], 0, board[r], 0, SIZE);
	    }
	}

	// Create a follow-up state with a single placement applied.
	private Sudoku place(int row, int col, int val) {
	    int[][] next = new int[SIZE][SIZE];
	    for (int r = 0; r < SIZE; r++) {
		System.arraycopy(board[r], 0, next[r], 0, SIZE);
	    }
	    next[row][col] = val;
	    return new Sudoku(next);
	}

	public boolean isSolved() {
	    for (int r = 0; r < SIZE; r++) {
		for (int c = 0; c < SIZE; c++) {
		    if (board[r][c] == 0) return false;
		}
	    }
	    return isValid();
	}

	// Check that the current filled cells obey Sudoku constraints.
	private boolean isValid() {
	    for (int i = 0; i < SIZE; i++) {
		boolean[] rowSeen = new boolean[SIZE + 1];
		boolean[] colSeen = new boolean[SIZE + 1];
		for (int j = 0; j < SIZE; j++) {
		    int rv = board[i][j];
		    if (rv != 0) {
			if (rowSeen[rv]) return false;
			rowSeen[rv] = true;
		    }
		    int cv = board[j][i];
		    if (cv != 0) {
			if (colSeen[cv]) return false;
			colSeen[cv] = true;
		    }
		}
	    }
	    for (int br = 0; br < SIZE; br += 3) {
		for (int bc = 0; bc < SIZE; bc += 3) {
		    boolean[] seen = new boolean[SIZE + 1];
		    for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
			    int v = board[br + r][bc + c];
			    if (v != 0) {
				if (seen[v]) return false;
				seen[v] = true;
			    }
			}
		    }
		}
	    }
	    return true;
	}

	private boolean canPlace(int row, int col, int val) {
	    // Row and column checks.
	    for (int i = 0; i < SIZE; i++) {
		if (board[row][i] == val) return false;
		if (board[i][col] == val) return false;
	    }
	    // 3x3 block check.
	    int br = (row / 3) * 3;
	    int bc = (col / 3) * 3;
	    for (int r = 0; r < 3; r++) {
		for (int c = 0; c < 3; c++) {
		    if (board[br + r][bc + c] == val) return false;
		}
	    }
	    return true;
	}

	// Locate next empty cell (row-major). Returns {-1, -1} if none.
	private int[] nextEmpty() {
	    for (int r = 0; r < SIZE; r++) {
		for (int c = 0; c < SIZE; c++) {
		    if (board[r][c] == 0) return new int[]{r, c};
		}
	    }
	    return new int[]{-1, -1};
	}

	@Override
	public Sudoku value() {
	    return this;
	}

	public int priority() {
	    int empty = 0;
	    for (int r = 0; r < SIZE; r++) {
		for (int c = 0; c < SIZE; c++) {
		    if (board[r][c] == 0) empty += 1;
		}
	    }
	    return empty;
	}

	@Override
	public FnList<FnGtree<Sudoku>> children() {
	    int[] empty = nextEmpty();
	    int row = empty[0];
	    int col = empty[1];
	    if (row == -1) {
		return FnListSUtil.nil(); // solved state, no children
	    }

	    FnList<FnGtree<Sudoku>> next = FnListSUtil.nil();
	    // Try digits 1..9; prepend then reverse to preserve digit order.
	    for (int digit = 9; digit >= 1; digit--) {
		if (canPlace(row, col, digit)) {
		    next = FnListSUtil.cons(place(row, col, digit), next);
		}
	    }
	    return next;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (int r = 0; r < SIZE; r++) {
		if (r % 3 == 0 && r != 0) {
		    sb.append("---------------------\n");
		}
		for (int c = 0; c < SIZE; c++) {
		    if (c % 3 == 0 && c != 0) sb.append("| ");
		    int v = board[r][c];
		    sb.append(v == 0 ? ". " : v + " ");
		}
		sb.append("\n");
	    }
	    return sb.toString();
	}
    }

    public LnStrm<Sudoku> Soduku_dfs_solve(Sudoku puzzle) {
	return FnGtreeSUtil.DFirstEnumerate(puzzle).filter0(Sudoku::isSolved);
    }
    public LnStrm<Sudoku> Soduku_bfs_solve(Sudoku puzzle) {
	return FnGtreeSUtil.BFirstEnumerate(puzzle).filter0(Sudoku::isSolved);
    }
//
    public static void main (String[] args) {
	// Please add minimal testing code for Sudoku_dfs_solve
	// Please add minimal testing code for Sudoku_bfs_solve
	int[][] puzzle = {
	    {5, 1, 7, 6, 0, 0, 0, 3, 4},
	    {2, 8, 9, 0, 0, 4, 0, 0, 0},
	    {3, 4, 6, 2, 0, 5, 0, 9, 0},
	    {6, 0, 2, 0, 0, 0, 0, 1, 0},
	    {0, 3, 8, 0, 0, 6, 0, 4, 7},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0},
	    {0, 9, 0, 0, 0, 0, 0, 7, 8},
	    {7, 0, 3, 4, 0, 0, 5, 6, 0},
	    {0, 0, 0, 0, 0, 0, 0, 0, 0}
	};

	Sudoku instance = new Sudoku(puzzle);
	Quiz02_03 solver = new Quiz02_03();

	LnStcn<Sudoku> dfsResult = solver.Soduku_dfs_solve(instance).eval0();
	if (dfsResult.consq()) {
	    System.out.println("DFS solved board:");
	    System.out.println(dfsResult.hd());
	} else {
	    System.out.println("DFS could not find a solution.");
	}

	LnStcn<Sudoku> bfsResult = solver.Soduku_bfs_solve(instance).eval0();
	if (bfsResult.consq()) {
	    System.out.println("BFS solved board:");
	    System.out.println(bfsResult.hd());
	} else {
	    System.out.println("BFS could not find a solution.");
	}
	return /*void*/;
    }
//
}
