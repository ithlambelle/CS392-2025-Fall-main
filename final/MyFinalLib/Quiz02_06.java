import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.ToIntBiFunction;

import Library.FnList.*;
import Library.FnTuple.*;

// randomized bst map based on quiz02_06
public class Quiz02_06<K, V> {
    private final ToIntBiFunction<K, K> cmp;
    private final Random rand = new Random();
    private Node root;

    private class Node {
	K key;
	V val;
	int size;
	Node left;
	Node right;

	Node(K k, V v) {
	    key = k;
	    val = v;
	    size = 1;
	}
    }

    public Quiz02_06(ToIntBiFunction<K, K> cmp) {
	this.cmp = cmp;
    }

    @SuppressWarnings("unchecked")
    public Quiz02_06() {
	this((k1, k2) -> ((Comparable<K>) k1).compareTo(k2));
    }

    public int size() {
	return size(root);
    }

    public V get(K key) {
	Node cur = root;
	while (cur != null) {
	    int sgn = compare(key, cur.key);
	    if (sgn == 0) return cur.val;
	    cur = (sgn < 0) ? cur.left : cur.right;
	}
	return null;
    }

    public void put(K key, V val) {
	root = insert(root, key, val);
    }

    public void forEach(BiConsumer<K, V> work) {
	traverse(root, work);
    }

    public FnList<FnTupl2<K, V>> toList() {
	return toList(root, new FnList<FnTupl2<K, V>>());
    }

    private int size(Node n) {
	return (n == null) ? 0 : n.size;
    }

    private int compare(K k1, K k2) {
	return cmp.applyAsInt(k1, k2);
    }

    private void updateSize(Node n) {
	if (n != null) {
	    n.size = 1 + size(n.left) + size(n.right);
	}
    }

    private Node rotateLeft(Node x) {
	if (x == null || x.right == null) return x;
	Node y = x.right;
	x.right = y.left;
	y.left = x;
	updateSize(x);
	updateSize(y);
	return y;
    }

    private Node rotateRight(Node x) {
	if (x == null || x.left == null) return x;
	Node y = x.left;
	x.left = y.right;
	y.right = x;
	updateSize(x);
	updateSize(y);
	return y;
    }

    private Node insertAtRoot(Node n, K key, V val) {
	if (n == null) return new Node(key, val);
	int sgn = compare(key, n.key);
	if (sgn == 0) {
	    n.val = val;
	    return n;
	}
	if (sgn < 0) {
	    n.left = insertAtRoot(n.left, key, val);
	    return rotateRight(n);
	} else {
	    n.right = insertAtRoot(n.right, key, val);
	    return rotateLeft(n);
	}
    }

    private Node insert(Node n, K key, V val) {
	if (n == null) return new Node(key, val);
	int sgn = compare(key, n.key);
	if (sgn == 0) {
	    n.val = val;
	    return n;
	}

	int roll = rand.nextInt(n.size + 1);
	if (roll == 0) {
	    n = insertAtRoot(n, key, val);
	} else if (sgn < 0) {
	    n.left = insert(n.left, key, val);
	} else {
	    n.right = insert(n.right, key, val);
	}
	updateSize(n);
	return n;
    }

    private void traverse(Node n, BiConsumer<K, V> work) {
	if (n == null) return;
	traverse(n.left, work);
	work.accept(n.key, n.val);
	traverse(n.right, work);
    }

    private FnList<FnTupl2<K, V>> toList(Node n, FnList<FnTupl2<K, V>> acc) {
	if (n == null) return acc;
	acc = toList(n.right, acc);
	acc = new FnList<FnTupl2<K, V>>(new FnTupl2<K, V>(n.key, n.val), acc);
	return toList(n.left, acc);
    }

    public static void main(String[] args) {
	Quiz02_06<Integer, Integer> bst =
	    new Quiz02_06<Integer, Integer>();
	for (int i = 0; i < 5; i++) {
	    bst.put(i, i * 10);
	}
	bst.put(2, 99);
	bst.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
