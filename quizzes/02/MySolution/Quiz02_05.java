//
// HX-2025-11-20: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
public class Quiz02_05 {
    public static class RBTnode {
	int key;
	int color; // Red = 0; Black = 1
	RBTnode lchild;
	RBTnode rchild;

	RBTnode(int key, int color) {
	    this.key = key; this.color = color;
	}
	RBTnode(int key, int color, RBTnode l, RBTnode r) {
	    this.key = key; this.color = color; this.lchild = l; this.rchild = r;
	}
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [rbt] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you already visit
    // each node once.
    //
    public static boolean isRBT (RBTnode rbt) {
	// HX: Please implement a function that
	// tests whether a given RBTnode is a valid
	// red-black tree. If it is unclear what a
	// red-black tree, you can readily find it on-line
	// Note that you are not asked to check if rbt is
    // a binary search tree in this case.
	if (rbt == null) return true; // empty tree is vacuously a RBT
	if (rbt.color != 1) return false; // root must be black
	return checkRB(rbt).valid;
    }

    private static class RbInfo {
	boolean valid;
	int blackHeight;
	RbInfo(boolean v, int bh) { valid = v; blackHeight = bh; }
    }

    private static RbInfo checkRB(RBTnode node) {
	if (node == null) return new RbInfo(true, 1); // NIL leaves are black

	RbInfo left = checkRB(node.lchild);
	if (!left.valid) return new RbInfo(false, 0);
	RbInfo right = checkRB(node.rchild);
	if (!right.valid) return new RbInfo(false, 0);

	// Property: equal black-height along every path.
	if (left.blackHeight != right.blackHeight) return new RbInfo(false, 0);

	// Property: a red node cannot have red children.
	if (node.color == 0) {
	    if ((node.lchild != null && node.lchild.color == 0) ||
		(node.rchild != null && node.rchild.color == 0)) {
		return new RbInfo(false, 0);
	    }
	}

	int bh = left.blackHeight + (node.color == 1 ? 1 : 0);
	return new RbInfo(true, bh);
    }
    //
    // HX: 20 points
    // This is largely about understanding red-black trees.
    // Please explain BRIEFLY as to why the generated RBT is
    // of minimal black height (not height).
    //
    public static boolean genRedBLackBST() {
	// Please genenerate a binary search RBT that
	// contains exactly 1 million keys: 0, 1, 2, ..., 999999
	// such that the black height (not height) of this tree is
	// minimal (that is, as small as possible). What is this black
	// height? Please give a brief explanation on your implementation
	// strategy.
	// Strategy: build a perfectly balanced BST and color levels
	// alternately (black root, red children, black grandchildren, ...).
	// This maximizes red levels and therefore minimizes black height
	// while keeping the standard red-black invariants.
	RBTnode root = buildColored(0, 999_999, true);
	RbInfo info = checkRB(root);
	System.out.println("Generated black height: " + info.blackHeight);
	return info.valid;
    }

    private static RBTnode buildColored(int lo, int hi, boolean blackLevel) {
	if (lo > hi) return null;
	int mid = lo + (hi - lo) / 2;
	int color = blackLevel ? 1 : 0;
	RBTnode left = buildColored(lo, mid - 1, !blackLevel);
	RBTnode right = buildColored(mid + 1, hi, !blackLevel);
	return new RBTnode(mid, color, left, right);
    }
    public static void main (String[] args) {
	// Please add minimal testing code for isRBT()
	// Please add minimal testing code for genRedBlackBST()
	RBTnode root = new RBTnode(10, 1,
				   new RBTnode(5, 0, new RBTnode(2, 1), new RBTnode(7, 1)),
				   new RBTnode(15, 0, new RBTnode(12, 1), new RBTnode(20, 1)));
	System.out.println("Sample RBT valid: " + isRBT(root));

	RBTnode bad = new RBTnode(1, 0, new RBTnode(2, 0), null);
	System.out.println("Invalid RBT (red violation): " + isRBT(bad));

	System.out.println("Building 1,000,000-node red-black BST...");
	boolean ok = genRedBLackBST();
	System.out.println("Generation valid: " + ok);
	return /*void*/;
    }
}
