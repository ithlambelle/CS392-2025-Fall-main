import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;
import Library.FnGtree.*;

public class Assign09_02 {
    // HX-2025-12-02:
    // Please use Warnsdorf's rule to
    // search for knight's tours on a chess board
    // of dimension (chessBoardSize x chessBoardSize)
    // Your search should be based on the PFirstEnumerate
    // (See Code/FnGtree/FnGtreeSUtil.java)
    public static
	LnStrm<FnList<FnTupl2<Integer,Integer>>>
	genKnightsTours(int chessBoardSize) {
	if (chessBoardSize <= 0) {
	    return new LnStrm<FnList<FnTupl2<Integer,Integer>>>(() -> new LnStcn<FnList<FnTupl2<Integer,Integer>>>());
	}
	final int total = chessBoardSize * chessBoardSize;
	boolean[][] visited = new boolean[chessBoardSize][chessBoardSize];
	visited[0][0] = true;
	FnList<FnTupl2<Integer,Integer>> startPath =
	    new FnList<FnTupl2<Integer,Integer>>(new FnTupl2<Integer,Integer>(0, 0), new FnList<FnTupl2<Integer,Integer>>());
	TourNode root = new TourNode(chessBoardSize, total, 1, startPath, visited);

	LnStrm<FnList<FnTupl2<Integer,Integer>>>
	    paths = FnGtreeSUtil.PFirstEnumerate(root);
	return paths.filter0(path -> path.length() == total);
    }
    // Please write minimal testing code for [genKnightsTours]
    private static class TourNode
	implements FnGtree<FnList<FnTupl2<Integer,Integer>>>, FnGtreeSUtil.PriorityNode {
	private final int boardSize;
	private final int total;
	private final int len;
	private final FnList<FnTupl2<Integer,Integer>> path;
	private final boolean[][] visited;
	private final FnList<FnTupl2<Integer,Integer>> moves;
	private final int priority;
	private static final int[] DR = {2, 1, -1, -2, -2, -1, 1, 2};
	private static final int[] DC = {1, 2, 2, 1, -1, -2, -2, -1};

	TourNode(int boardSize,
		 int total,
		 int len,
		 FnList<FnTupl2<Integer,Integer>> path,
		 boolean[][] visited) {
	    this.boardSize = boardSize;
	    this.total = total;
	    this.len = len;
	    this.path = path;
	    this.visited = visited;
	    this.moves = computeMoves(path.hd(), visited, boardSize);
	    int deg = listLength(this.moves);
	    this.priority = deg * 1000 + (total - len);
	}

	public FnList<FnTupl2<Integer,Integer>> value() {
	    return path.reverse();
	}

	public int priority() {
	    return priority;
	}

	public FnList<FnGtree<FnList<FnTupl2<Integer,Integer>>>> children() {
	    if (len >= total) return FnListSUtil.nil();
	    FnList<FnGtree<FnList<FnTupl2<Integer,Integer>>>> res = FnListSUtil.nil();
	    FnList<FnTupl2<Integer,Integer>> mv = moves;
	    while (!mv.nilq()) {
		FnTupl2<Integer,Integer> step = mv.hd();
		boolean[][] nextVisited = copyVisited();
		nextVisited[step.sub0][step.sub1] = true;
        FnList<FnTupl2<Integer,Integer>> newPath =
		    new FnList<FnTupl2<Integer,Integer>>(step, path);
		res =
		    FnListSUtil.cons(
			new TourNode(boardSize, total, len + 1, newPath, nextVisited),
			res);
		mv = mv.tl();
	    }
	    return res;
	}

	private boolean[][] copyVisited() {
	    boolean[][] cp = new boolean[boardSize][boardSize];
	    for (int r = 0; r < boardSize; r += 1) {
		System.arraycopy(visited[r], 0, cp[r], 0, boardSize);
	    }
	    return cp;
	}

	private static FnList<FnTupl2<Integer,Integer>>
	computeMoves(FnTupl2<Integer,Integer> pos, boolean[][] visited, int size) {
	    FnList<FnTupl2<Integer,Integer>> res = FnListSUtil.nil();
	    int r0 = pos.sub0;
	    int c0 = pos.sub1;
	    for (int i = 0; i < DR.length; i += 1) {
		int r = r0 + DR[i];
		int c = c0 + DC[i];
		if (r >= 0 && r < size && c >= 0 && c < size && !visited[r][c]) {
		    res = FnListSUtil.cons(new FnTupl2<Integer,Integer>(r, c), res);
		}
	    }
	    // Warnsdorf: sort by onward degree (ascending)
	    return sortByDegree(res, visited, size);
	}

	private static FnList<FnTupl2<Integer,Integer>>
	sortByDegree(FnList<FnTupl2<Integer,Integer>> moves, boolean[][] visited, int size) {
	    // simple insertion sort on small move list
	    FnList<FnTupl2<Integer,Integer>> sorted = FnListSUtil.nil();
	    FnList<FnTupl2<Integer,Integer>> xs = moves;
	    while (!xs.nilq()) {
		FnTupl2<Integer,Integer> mv = xs.hd();
		int deg = onwardMoves(mv, visited, size);
		sorted = insertByDeg(sorted, mv, deg, visited, size);
		xs = xs.tl();
	    }
	    return sorted.reverse();
	}

	private static FnList<FnTupl2<Integer,Integer>>
	insertByDeg(FnList<FnTupl2<Integer,Integer>> list,
		    FnTupl2<Integer,Integer> mv,
		    int deg,
		    boolean[][] visited,
		    int size) {
	    if (list.nilq()) {
		return FnListSUtil.sing(mv);
	    }
	    int headDeg = onwardMoves(list.hd(), visited, size);
	    if (deg <= headDeg) {
		return FnListSUtil.cons(mv, list);
	    } else {
		return FnListSUtil.cons(list.hd(),
					insertByDeg(list.tl(), mv, deg, visited, size));
	    }
	}

	private static int onwardMoves(FnTupl2<Integer,Integer> pos, boolean[][] visited, int size) {
	    int count = 0;
	    int r0 = pos.sub0;
	    int c0 = pos.sub1;
	    for (int i = 0; i < DR.length; i += 1) {
		int r = r0 + DR[i];
		int c = c0 + DC[i];
		if (r >= 0 && r < size && c >= 0 && c < size && !visited[r][c]) {
		    count += 1;
		}
	    }
	    return count;
	}

	private static int listLength(FnList<?> xs) {
	    int n = 0;
	    FnList<?> ys = xs;
	    while (!ys.nilq()) { n += 1; ys = ys.tl(); }
	    return n;
	}
    }

    public static void main(String[] argv) {
	LnStrm<FnList<FnTupl2<Integer,Integer>>> tours = genKnightsTours(5);
	LnStcn<FnList<FnTupl2<Integer,Integer>>> cell = tours.eval0();
	int shown = 0;
	while (cell.consq() && shown < 1) {
	    System.out.print("Found tour: ");
	    cell.hd().System$out$print();
	    System.out.println();
	    shown += 1;
	    cell = cell.tl().eval0();
	}
	if (shown == 0) {
	    System.out.println("No tour found.");
	}
    }
}
