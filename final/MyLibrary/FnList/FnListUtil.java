import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;

public class FnListUtil {
//
    public static<T>
	boolean nilq(FnList<T> xs) {
	return xs.nilq();
    }
    public static<T>
	boolean consq(FnList<T> xs) {
	return xs.consq();
    }
//
    public static<T>
	void System$out$print(FnList<T> xs) {
    	System.out.print("FnList(");
	FnListUtil.iforitm
        ( xs,
          (i, itm) ->
	  {
	      if (i > 0) {
		  System.out.print(",");
	      }
	      System.out.print(itm.toString());
	  }
	);
	System.out.print(")");
    }
//
    public static
	FnList<Integer>
	list_make_int1(int n0) {
	FnList<Integer> xs =
	    new FnList<Integer>();
	for (int i0 = n0-1; i0 >= 0; i0 -= 1) {
	    xs = new FnList<Integer>(i0, xs);
	}
	return xs;
    }
//
    public static<T,R>
	FnList<R> map_list
	  (FnList<T> xs, Function<? super T, R> fopr) {
	FnList<R> res = new FnList<R>();
	while (true) {
	    if (xs.nilq()) break;
	    res = new FnList<R>(fopr.apply(xs.hd()), res);
	    xs = xs.tl();
	}
	return res.reverse();
    }
    public static<T,R>
	FnList<R> rmap_list
	  (FnList<T> xs, Function<? super T, R> fopr) {
	FnList<R> res = new FnList<R>();
	xs = xs.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    res = new FnList<R>(fopr.apply(xs.hd()), res);
	    xs = xs.tl();
	}
	return res.reverse();
    }
    public static<T,R>
	FnList<R> imap_list
	  (FnList<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int i0 = 0;
	FnList<R> res = new FnList<R>();
	while (true) {
	    if (xs.nilq()) break;
	    res = new FnList<R>(fopr.apply(i0, xs.hd()), res);
	    i0 += 1; xs = xs.tl();
	}
	return res.reverse();
    }
    public static<T,R>
	FnList<R> irmap_list
	  (FnList<T> xs, BiFunction<Integer, ? super T, R> fopr) {
	int i0 = 0;
	FnList<R> res = new FnList<R>();
	xs = xs.reverse();
	while (true) {
	    if (xs.nilq()) break;
	    res = new FnList<R>(fopr.apply(i0, xs.hd()), res);
	    i0 += 1; xs = xs.tl();
	}
	return res.reverse();
    }
//
    public static<T>
	void foritm(FnList<T> xs, Consumer<? super T> action) {
	xs.foritm(action); return;
    }
    public static<T>
	void rforitm(FnList<T> xs, Consumer<? super T> action) {
	xs.reverse().foritm(action); return;
    }
    public static<T>
	void iforitm(FnList<T> xs, BiConsumer<Integer, ? super T> action) {
	xs.iforitm(action); return;
    }
    public static<T>
	void irforitm(FnList<T> xs, BiConsumer<Integer, ? super T> action) {
	xs.reverse().iforitm(action); return;
    }
//
    public static<T>
	boolean forall(FnList<T> xs, Predicate<? super T> pred) {
	return xs.forall(pred);
    }
    public static<T>
	boolean rforall(FnList<T> xs, Predicate<? super T> pred) {
	return xs.reverse().forall(pred);
    }
    public static<T>
	boolean iforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	return xs.iforall(pred);
    }
    public static<T>
	boolean irforall(FnList<T> xs, BiPredicate<Integer, ? super T> pred) {
	return xs.reverse().iforall(pred);
    }
//
    public static<T>
	FnList<T> reverse(FnList<T> xs) {
	FnList<T> r0 = new FnList<T>();
	return FnListUtil.folditm
	    (xs, r0, (r1, x1) -> new FnList<T>(x1, r1));
    }
//
    public static<T>
	FnList<T> rappend(FnList<T> xs, FnList<T> ys) {
	return FnListUtil.folditm
	    (xs, ys, (r1, x1) -> new FnList<T>(x1, r1));
    }
//
    public static<T,R>
	R folditm
	(FnList<T> xs, R r0, BiFunction<R, ? super T, R> fopr) {
	R res = r0;
	while (true) {
	    if (xs.nilq()) break;
	    res = fopr.apply(res, xs.hd());
	    xs = xs.tl();
	}
	return res;
    }
//
    public static<T,R>
	R rfolditm
	(FnList<T> xs, R r0, BiFunction<? super T, R, R> fopr) {
	return FnListUtil.folditm(xs.reverse(), r0, (x1, r1) -> fopr.apply(r1, x1));
    }
//
    // Additional utility methods for exams and advanced usage
    public static<T>
	FnList<T> filter(FnList<T> xs, Predicate<? super T> pred) {
	FnList<T> res = new FnList<T>();
	while (true) {
	    if (xs.nilq()) break;
	    if (pred.test(xs.hd())) {
		res = new FnList<T>(xs.hd(), res);
	    }
	    xs = xs.tl();
	}
	return res.reverse();
    }
    
    public static<T>
	FnList<T> take(FnList<T> xs, int n) {
	FnList<T> res = new FnList<T>();
	int count = 0;
	while (!xs.nilq() && count < n) {
	    res = new FnList<T>(xs.hd(), res);
	    xs = xs.tl();
	    count++;
	}
	return res.reverse();
    }
    
    public static<T>
	FnList<T> drop(FnList<T> xs, int n) {
	int count = 0;
	while (!xs.nilq() && count < n) {
	    xs = xs.tl();
	    count++;
	}
	return xs;
    }
    
    public static<T>
	T nth(FnList<T> xs, int n) {
	int count = 0;
	while (!xs.nilq()) {
	    if (count == n) return xs.hd();
	    xs = xs.tl();
	    count++;
	}
	throw new IndexOutOfBoundsException("Index " + n + " out of bounds");
    }
    
    public static<T>
	FnList<T> append(FnList<T> xs, FnList<T> ys) {
	return FnListUtil.folditm(xs.reverse(), ys, (r1, x1) -> new FnList<T>(x1, r1));
    }
    
    public static<T>
	FnList<T> concat(FnList<FnList<T>> xss) {
	FnList<T> res = new FnList<T>();
	while (!xss.nilq()) {
	    res = FnListUtil.append(res, xss.hd());
	    xss = xss.tl();
	}
	return res;
    }
    
    public static<T>
	FnList<T> flatten(FnList<FnList<T>> xss) {
	return FnListUtil.concat(xss);
    }
    
    public static<T>
	FnList<T> zipWith(FnList<T> xs, FnList<T> ys, BiFunction<T, T, T> f) {
	FnList<T> res = new FnList<T>();
	while (!xs.nilq() && !ys.nilq()) {
	    res = new FnList<T>(f.apply(xs.hd(), ys.hd()), res);
	    xs = xs.tl();
	    ys = ys.tl();
	}
	return res.reverse();
    }
    
    public static<T>
	FnList<T> replicate(int n, T x) {
	FnList<T> res = new FnList<T>();
	for (int i = 0; i < n; i++) {
	    res = new FnList<T>(x, res);
	}
	return res;
    }
    
    public static<T>
	FnList<T> cycle(FnList<T> xs) {
	throw new UnsupportedOperationException(
          "cycle would create an infinite list; prefer LnStrm for lazy repetition");
    }
//
    public static<T extends Comparable<T>>
	boolean orderedq(FnList<T> xs) {
	return orderedq(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
	boolean orderedq(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq()) return true;
	T prev = xs.hd();
	xs = xs.tl();
	while (!xs.nilq()) {
	    if (cmp.applyAsInt(prev, xs.hd()) > 0) return false;
	    prev = xs.hd();
	    xs = xs.tl();
	}
	return true;
    }
//
    public static<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
	FnList<T> insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	FnList<T> sorted = new FnList<T>();
	FnList<T> rest = xs;
	while (!rest.nilq()) {
	    sorted = insertSort_insert(sorted, rest.hd(), cmp);
	    rest = rest.tl();
	}
	return sorted;
    }
    private static<T>
	FnList<T> insertSort_insert(FnList<T> xs, T x0, ToIntBiFunction<T,T> cmp) {
	FnList<T> prefix = new FnList<T>();
	FnList<T> curr = xs;
	while (!curr.nilq() && cmp.applyAsInt(curr.hd(), x0) <= 0) {
	    prefix = new FnList<T>(curr.hd(), prefix);
	    curr = curr.tl();
	}
	FnList<T> merged = new FnList<T>(x0, curr);
	return FnListUtil.append(prefix.reverse(), merged);
    }
//
    public static<T extends Comparable<T>>
	FnList<T> mergeSort(FnList<T> xs) {
	return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
	FnList<T> mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	int n = xs.length();
	if (n <= 1) return xs;
	int mid = n / 2;
	FnList<T> left = new FnList<T>();
	FnList<T> right = xs;
	for (int i = 0; i < mid; i++) {
	    left = new FnList<T>(right.hd(), left);
	    right = right.tl();
	}
	FnList<T> sortedLeft = mergeSort(left.reverse(), cmp);
	FnList<T> sortedRight = mergeSort(right, cmp);
	return merge(sortedLeft, sortedRight, cmp);
    }
    private static<T>
	FnList<T> merge(FnList<T> xs, FnList<T> ys, ToIntBiFunction<T,T> cmp) {
	FnList<T> res = new FnList<T>();
	FnList<T> as = xs;
	FnList<T> bs = ys;
	while (!as.nilq() && !bs.nilq()) {
	    if (cmp.applyAsInt(as.hd(), bs.hd()) <= 0) {
		res = new FnList<T>(as.hd(), res);
		as = as.tl();
	    } else {
		res = new FnList<T>(bs.hd(), res);
		bs = bs.tl();
	    }
	}
	while (!as.nilq()) { res = new FnList<T>(as.hd(), res); as = as.tl(); }
	while (!bs.nilq()) { res = new FnList<T>(bs.hd(), res); bs = bs.tl(); }
	return res.reverse();
    }
//
    public static<T extends Comparable<T>>
	FnList<T> quickSort(FnList<T> xs) {
	return quickSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
    public static<T>
	FnList<T> quickSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	if (xs.nilq() || xs.tl().nilq()) return xs;
	T pivot = xs.hd();
	FnList<T> smaller = new FnList<T>();
	FnList<T> bigger = new FnList<T>();
	FnList<T> rest = xs.tl();
	while (!rest.nilq()) {
	    T itm = rest.hd();
	    if (cmp.applyAsInt(itm, pivot) <= 0) {
		smaller = new FnList<T>(itm, smaller);
	    } else {
		bigger = new FnList<T>(itm, bigger);
	    }
	    rest = rest.tl();
	}
	FnList<T> sortedSmall = quickSort(smaller, cmp);
	FnList<T> sortedBig = quickSort(bigger, cmp);
	return append(sortedSmall, new FnList<T>(pivot, quickSort(sortedBig, cmp)));
    }
//
} // end of [public class FnListUtil{...}]
