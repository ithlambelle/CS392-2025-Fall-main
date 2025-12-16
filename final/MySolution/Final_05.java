/*
// HX: 50 points for Final_05
// HX: This one tests your priority queue implementation
*/

import Library.LnList.*;
import java.util.function.ToIntBiFunction;
import Library.MyPQueue.*;
import Library.FnList.*;
import Library.FnTuple.*;

public class Final_05 {

    public static<T> LnList<T>
	LnList_n$way$merge(LnList<T> xss[], ToIntBiFunction<T,T> cmp) {
	// n-way merge using min-heap: each list's current head is in the pq.
	// pop smallest head, append to result, then push the remainder of that list.
	int n = xss.length;
	MyPQueueArray<LItem<T>> pq =
	    new MyPQueueArray<LItem<T>>(Math.max(1, n),
		(a, b) -> {
		    int c = cmp.applyAsInt(a.list.hd1(), b.list.hd1());
		    if (c != 0) return c;
		    return Integer.compare(a.idx, b.idx);
		});

	for (int i = 0; i < n; i++) {
	    if (xss[i] != null && xss[i].consq1()) {
		pq.enque$raw(new LItem<T>(xss[i], i));
	    }
	}

	LnList<T> head = null;
	LnList<T> tail = null;

	while (!pq.isEmpty()) {
	    LItem<T> itm = pq.deque$raw();
	    LnList<T> node = itm.list;
	    LnList<T> rest = node.unlink1();

	    if (head == null) {
		head = node;
		tail = node;
	    } else {
		tail.link1(node);
		tail = node;
	    }

	    if (rest.consq1()) {
		pq.enque$raw(new LItem<T>(rest, itm.idx));
	    }
	}
	return (head == null ? new LnList<T>() : head);
    }

    public static<T>
	FnList<T>
	LnList_mergeSort$5way(LnList<T> xs, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq1()) return new FnList<T>();
	if (xs.tl1().nilq1()) {
	    return new FnList<T>(xs.hd1(), new FnList<T>());
	}

	int len = xs.length1();
	int base = len / 5;
	int rem = len % 5;

	LnList<T>[] parts = new LnList[5];
	LnList<T> cur = xs;
	for (int i = 0; i < 5; i++) {
	    int size = base + (i < rem ? 1 : 0);
	    FnTupl2<LnList<T>, LnList<T>> split = splitPrefix(cur, size);
	    parts[i] = split.sub0;
	    cur = split.sub1;
	}

	FnList<T>[] sorted = new FnList[5];
	for (int i = 0; i < 5; i++) {
	    sorted[i] = LnList_mergeSort$5way(parts[i], cmp);
	}

	@SuppressWarnings("unchecked")
	LnList<T>[] sortedLn = new LnList[5];
	for (int i = 0; i < 5; i++) {
	    sortedLn[i] = new LnList<T>(sorted[i]);
	}

	LnList<T> merged = LnList_n$way$merge(sortedLn, cmp);

	FnList<T> res = new FnList<T>();
	LnList<T> walk = merged;
	while (walk.consq1()) {
	    res = new FnList<T>(walk.hd1(), res);
	    walk = walk.tl1();
	}
	return res.reverse();
    }

    public static void main(String[] args) {
	int n = 1000000;
	LnList<Integer> xs = new LnList<Integer>();
	for (int i = n - 1; i >= 0; i--) {
	    xs = new LnList<Integer>(i, xs);
	}

	ToIntBiFunction<Integer, Integer> parityCmp =
	    (a, b) -> {
		int pa = a & 1;
		int pb = b & 1;
		if (pa != pb) return pa - pb;
		return Integer.compare(a, b);
	    };

	FnList<Integer> sorted = LnList_mergeSort$5way(xs, parityCmp);

	int count = 0;
	FnList<Integer> check = sorted;
	boolean ok = true;
	int prev = Integer.MIN_VALUE;
	while (check.consq() && count < 50) {
	    int v = check.hd();
	    if ((prev & 1) == (v & 1)) {
		if (v < prev) ok = false;
	    } else if ((prev & 1) == 1 && (v & 1) == 0) {
		ok = false;
	    }
	    prev = v;
	    check = check.tl();
	    count += 1;
	}
	System.out.println("sample sorted ok: " + ok);
    }

    private static class LItem<T> {
	LnList<T> list;
	int idx;
	LItem(LnList<T> l, int i) { list = l; idx = i; }
    }

    private static<T> FnTupl2<LnList<T>, LnList<T>> splitPrefix(LnList<T> xs, int k) {
	if (!xs.consq1() || k <= 0) {
	    return new FnTupl2<LnList<T>, LnList<T>>(new LnList<T>(), xs);
	}
	LnList<T> prefix = xs;
	LnList<T> last = xs;
	for (int i = 1; i < k && last.consq1(); i++) {
	    LnList<T> nxt = last.tl1();
	    if (!nxt.consq1()) break;
	    last = nxt;
	}
	LnList<T> rest = last.unlink1();
	return new FnTupl2<LnList<T>, LnList<T>>(prefix, rest);
    }

}
