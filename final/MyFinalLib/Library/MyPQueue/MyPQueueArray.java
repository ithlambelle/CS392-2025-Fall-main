package Library.MyPQueue;

import java.util.Comparator;

public class MyPQueueArray<T> extends MyPQueueBase<T> {

    private final Object[] heap;
    private final int capacity;
    private int size;
    private final Comparator<? super T> cmp;

    public MyPQueueArray() {
	this(1024, null);
    }

    public MyPQueueArray(int capacity) {
	this(capacity, null);
    }

    public MyPQueueArray(int capacity, Comparator<? super T> cmp) {
	this.capacity = Math.max(1, capacity);
	this.heap = new Object[this.capacity];
	this.size = 0;
	this.cmp = cmp;
    }

    public int size() {
	return size;
    }

    public boolean isFull() {
	return size >= capacity;
    }

    public T top$raw() {
	return elementAt(0);
    }

    public T deque$raw() {
	T top = top$raw();
	heap[0] = heap[size - 1];
	heap[size - 1] = null;
	size -= 1;
	siftDown(0);
	return top;
    }

    public void enque$raw(T itm) {
	if (isFull()) throw new MyPQueueFullExn();
	heap[size] = itm;
	siftUp(size);
	size += 1;
    }

    private T elementAt(int i) {
	@SuppressWarnings("unchecked")
	T v = (T) heap[i];
	return v;
    }

    private void swap(int i, int j) {
	Object tmp = heap[i];
	heap[i] = heap[j];
	heap[j] = tmp;
    }

    private int compare(T a, T b) {
	if (cmp != null) return cmp.compare(a, b);
	@SuppressWarnings("unchecked")
	Comparable<? super T> ca = (Comparable<? super T>) a;
	return ca.compareTo(b);
    }

    private void siftUp(int idx) {
	int i = idx;
	while (i > 0) {
	    int p = (i - 1) / 2;
	    if (compare(elementAt(i), elementAt(p)) < 0) {
		swap(i, p);
		i = p;
	    } else {
		break;
	    }
	}
    }

    private void siftDown(int idx) {
	int i = idx;
	while (true) {
	    int left = i * 2 + 1;
	    int right = left + 1;
	    int smallest = i;
	    if (left < size && compare(elementAt(left), elementAt(smallest)) < 0) {
		smallest = left;
	    }
	    if (right < size && compare(elementAt(right), elementAt(smallest)) < 0) {
		smallest = right;
	    }
	    if (smallest == i) break;
	    swap(i, smallest);
	    i = smallest;
	}
    }
}
