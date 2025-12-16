import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class LnList<T> {
    Node root;
    
    private class Node {
	T head;
	Node tail;
	Node(T x0, Node xs) {
	    head = x0; tail = xs;
	}
    }

    public LnList() {
	root = null;
    }
    
    public LnList(T x0, LnList<T> xs) {
	root = new Node(x0, xs.root);
    }

    private LnList(Node xs) {
	root = xs;
    }

    public boolean nilq1() {
	return (root == null);
    }
    
    public T hd1() {
	return root.head;
    }
    
    public LnList<T> tl1() {
        Node tail = root.tail;
	return new LnList(tail);
    }

    public LnList<T> reverse0() {
	Node xs = root;
	Node ys = null;
	Node tl = null;
	while (xs != null) {
	    tl = xs.tail;
	    xs.tail = ys; ys = xs; xs = tl;
	}
	return new LnList<T>(ys);
    }

    public void foritm1(Consumer<? super T> work) {
	Node xs = root;
	while (xs != null) {
	    work.accept(xs.head); xs = xs.tail;
	}
	return;
    }

    public void iforitm1(BiConsumer<Integer, ? super T> work) {
	int i0 = 0;
	Node xs = root;
	while (xs != null) {
	    work.accept(i0, xs.head); i0 += 1; xs = xs.tail;
	}
	return;
    }
} // end of [public class LnList<T>{...}]

