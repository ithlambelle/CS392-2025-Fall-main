//
// HX-2025-11-20: 30 points
// (plus up to 20 bonus points)
// This is more of a theory problem
// than a programming one.
//
import Library.LnStrm.*;
//
public class Quiz02_04 {
    public static class AVLnode {
	int key;
	AVLnode lchild;
	AVLnode rchild;

	AVLnode(int key) { this.key = key; }
	AVLnode(int key, AVLnode l, AVLnode r) {
	    this.key = key; this.lchild = l; this.rchild = r;
	}
    }
    //
    // HX: 10 points for this one
    // HX: If your implementation only
    // visit each node in [avl] at most once,
    // then it will be rewarded with some bonus
    // points (up to 20 bonus points).
    // For instance, if you compute the size of
    // height of a tree, then you already visit
    // each node once.
    //
    public static boolean isAVL (AVLnode avl) {
	// HX: Please implement a function that
	// tests whether a given AVLnode is a valid
    // AVL tree. If it is unclear what a
    // AVL tree, you can readily find it on-line
    // Note that you are not asked to check if avl is
    // a binary search tree in this case.
	return checkBalance(avl).balanced;
    }

    private static class AvlInfo {
	boolean balanced;
	int height;
	AvlInfo(boolean b, int h) { balanced = b; height = h; }
    }

    private static AvlInfo checkBalance(AVLnode node) {
	if (node == null) return new AvlInfo(true, 0);
	AvlInfo left = checkBalance(node.lchild);
	if (!left.balanced) return new AvlInfo(false, 0);
	AvlInfo right = checkBalance(node.rchild);
	if (!right.balanced) return new AvlInfo(false, 0);
	boolean ok = Math.abs(left.height - right.height) <= 1;
	int h = 1 + Math.max(left.height, right.height);
	return new AvlInfo(ok, h);
    }
    //
    // HX: 20 points
    // This is largely about understanding AVL trees.
    // Please explain BRIEFLY as to why the generated AVL is
    // of maximal height (not minimal height). Note that this
    // is different from what is asked in Quiz02_05.
    //
    public static boolean genAVLBST() {
	// Please genenerate a binary search RBT that
	// contains exactly 1 million keys: 0, 1, 2, ..., 999999
	// such that the height of this tree is minimal (that is,
	// as small as possible). What is this height? Please give
	// a brief explanation on your implementation strategy.
	// Build a perfectly balanced tree by always picking the middle
	// element as root; this yields height ceil(log2(n+1)) for n nodes.
	AVLnode root = buildBalanced(0, 999_999);
	// The resulting height is 20 (since log2(1_000_000 + 1) ≈ 19.9).
	// Explanation: each subtree is constructed from a contiguous range
	// with its midpoint as the root, so both children differ in height
	// by at most 1. That inductively forces the tree to be complete,
	// giving the smallest possible height for the node count.
	System.out.println("Generated AVL height: " + treeHeight(root));
	return isAVL(root);
    }

    private static AVLnode buildBalanced(int lo, int hi) {
	if (lo > hi) return null;
	int mid = lo + (hi - lo) / 2;
	AVLnode left = buildBalanced(lo, mid - 1);
	AVLnode right = buildBalanced(mid + 1, hi);
	return new AVLnode(mid, left, right);
    }

    private static int treeHeight(AVLnode node) {
	if (node == null) return 0;
	return 1 + Math.max(treeHeight(node.lchild), treeHeight(node.rchild));
    }
    public static void main (String[] args) {
	// Please add minimal testing code for isRBT()
	// Please add minimal testing code for genAVLBST()
	AVLnode balanced = new AVLnode(2,
				       new AVLnode(1),
				       new AVLnode(3));
	System.out.println("isAVL on small balanced tree: " + isAVL(balanced));

	AVLnode skewed = new AVLnode(1, new AVLnode(2, new AVLnode(3), null), null);
	System.out.println("isAVL on skewed tree: " + isAVL(skewed));

	System.out.println("Building 1,000,000-node AVL...");
	boolean ok = genAVLBST();
	System.out.println("AVL generation valid: " + ok);
	return /*void*/;
    }
}
