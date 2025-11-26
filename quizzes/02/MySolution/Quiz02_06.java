//
// HX-2025-11-20: 50 points
// A partial implementation of
// randomized doubly linked binary search tree
// 30 points for reroot and 20 points for insert
//
import java.util.Random;

public class Quiz02_06 {
    Node root = null;
    public class Node {
	int key; // key stored in the node
	int size; // size of the tree rooted as the node
	Node parent; // parent of the node
	Node lchild; // left-child of the node
	Node rchild; // right-child of the node
    }
    public void reroot() {
	// HX-2025-11-20: 30 points
	// [reroot] picks a node RANDOMLY and
	// uses rotations to turn this picked node
	// into the root of a new binary search tree
	// (containing the same set of keys)
	if (root == null) return;
	int total = root.size;
	Random rand = new Random();
	int targetRank = rand.nextInt(total); // 0-based rank
	Node target = selectByRank(root, targetRank);
	if (target == null) return;

	// Rotate the chosen node up to become the root.
	while (target.parent != null) {
	    if (target.parent.lchild == target) {
		rotateRight(target.parent);
	    } else {
		rotateLeft(target.parent);
	    }
	}
	root = target;
    }
    public boolean insert(int key) {
	// HX-2025-11-20: 20 points
	// If key is in the tree stored at [root],
	// [insert] does no nothing and just returns false
	// If key is not in the tree stored at [root],
	// the key is inserted as a leaf node and the new
	// tree is still a binary search tree and [insert]
	// returns true (to indicate insertion is done).
	if (root == null) {
	    root = new Node();
	    root.key = key;
	    root.size = 1;
	    return true;
	}

	Node cur = root;
	Node parent = null;
	boolean wentLeft = false;

	while (cur != null) {
	    parent = cur;
	    if (key == cur.key) return false; // already present
	    if (key < cur.key) {
		cur = cur.lchild;
		wentLeft = true;
	    } else {
		cur = cur.rchild;
		wentLeft = false;
	    }
	}

	Node node = new Node();
	node.key = key;
	node.parent = parent;
	node.size = 1;
	if (wentLeft) parent.lchild = node;
	else parent.rchild = node;

	// Update subtree sizes up the path.
	while (parent != null) {
	    parent.size = 1 + size(parent.lchild) + size(parent.rchild);
	    parent = parent.parent;
	}
	return true;
    }
    public static void main (String[] args) {
	// Please add minimal testing code for reroot()
	// Please add minimal testing code for insert()
	Quiz02_06 tree = new Quiz02_06();
	int[] vals = {5, 2, 8, 1, 3, 7, 9};
	for (int v : vals) {
	    tree.insert(v);
	}
	System.out.println("Initial root: " + tree.root.key + " size=" + tree.root.size);
	tree.reroot();
	System.out.println("Root after reroot: " + tree.root.key + " size=" + tree.root.size);
	return /*void*/;
    }

    private int size(Node n) { return n == null ? 0 : n.size; }

    private Node selectByRank(Node node, int rank) {
	if (node == null) return null;
	int left = size(node.lchild);
	if (rank < left) return selectByRank(node.lchild, rank);
	if (rank == left) return node;
	return selectByRank(node.rchild, rank - left - 1);
    }

    private void rotateLeft(Node x) {
	Node y = x.rchild;
	if (y == null) return;
	x.rchild = y.lchild;
	if (y.lchild != null) y.lchild.parent = x;
	y.parent = x.parent;
	if (x.parent == null) {
	    root = y;
	} else if (x.parent.lchild == x) {
	    x.parent.lchild = y;
	} else {
	    x.parent.rchild = y;
	}
	y.lchild = x;
	x.parent = y;
	updateSize(x);
	updateSize(y);
    }

    private void rotateRight(Node x) {
	Node y = x.lchild;
	if (y == null) return;
	x.lchild = y.rchild;
	if (y.rchild != null) y.rchild.parent = x;
	y.parent = x.parent;
	if (x.parent == null) {
	    root = y;
	} else if (x.parent.lchild == x) {
	    x.parent.lchild = y;
	} else {
	    x.parent.rchild = y;
	}
	y.rchild = x;
	x.parent = y;
	updateSize(x);
	updateSize(y);
    }

    private void updateSize(Node node) {
	if (node != null) {
	    node.size = 1 + size(node.lchild) + size(node.rchild);
	}
    }
}
