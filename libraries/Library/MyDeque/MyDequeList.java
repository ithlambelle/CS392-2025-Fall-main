package Library.MyDeque;

import java.util.function.Consumer;
import java.util.function.BiConsumer;


class NotImplementedExn extends RuntimeException {}

public class MyDequeList<T> extends MyDequeBase<T> {

    int nitm = -1;
    Node frnt = null;
    Node rear = null;

    private class Node {
        private T item;
	private Node prev;
        private Node next;
        
        private Node(T itm, Node prv, Node nxt) {
            item = itm;
	    prev = prv;
            next = nxt;
        }
    }

    public MyDequeList() {
	nitm = 0;
	frnt = null; rear = null;
    }

    // @Override
    public int size() { return nitm; }
    // @Override
    public boolean isFull() { return false; }

    // @Override
    public T fpeek$raw() {
	if (frnt == null) {
	    throw new MyDequeEmptyExn();
	}
	return frnt.item;
    }
    // @Override
    public T rpeek$raw() {
	if (rear == null) {
	    throw new MyDequeEmptyExn();
	}
	return rear.item;
    }
//
    public T fdeque$raw()
    {
	if (frnt == null) {
	    throw new MyDequeEmptyExn();
	}
	T itm = frnt.item;
	frnt = frnt.next;
	if (frnt == null) {
	    rear = null;
	} else {
	    frnt.prev = null;
	}
	nitm -= 1;
	return itm;
    }
    // @Override
    public T rdeque$raw()
    {
	if (rear == null) {
	    throw new MyDequeEmptyExn();
	}
	T itm = rear.item;
	rear = rear.prev;
	if (rear == null) {
	    frnt = null;
	} else {
	    rear.next = null;
	}
	nitm -= 1;
	return itm;
    }
//
    // @Override
    public void fenque$raw(T itm)
    {
	Node newNode = new Node(itm, null, frnt);
	if (frnt == null) {
	    frnt = newNode;
	    rear = newNode;
	} else {
	    frnt.prev = newNode;
	    frnt = newNode;
	}
	nitm += 1;
	return;
    }
    // @Override
    public void renque$raw(T itm)
    {
	Node newNode = new Node(itm, rear, null);
	if (rear == null) {
	    frnt = newNode;
	    rear = newNode;
	} else {
	    rear.next = newNode;
	    rear = newNode;
	}
	nitm += 1;
	return;
    }
    // @Override
//
    public void foritm(Consumer<? super T> work) {
	Node xs = frnt;
	while (xs != null) {
	    work.accept(xs.item); xs = xs.next;
	}
    }

    public void rforitm(Consumer<? super T> work) {
	Node xs = rear;
	while (xs != null) {
	    work.accept(xs.item); xs = xs.prev;
	}
    }

    public void
	iforitm(BiConsumer<Integer, ? super T> work) {
	int i = 0;
	Node xs = frnt;
	while (xs != null) {
	    work.accept(i, xs.item); i += 1; xs = xs.next;
	}
    }

    public void
	irforitm(BiConsumer<Integer, ? super T> work) {
	int i = 0;
	Node xs = rear;
	while (xs != null) {
	    work.accept(i, xs.item); i += 1; xs = xs.prev;
	}
    }
}
