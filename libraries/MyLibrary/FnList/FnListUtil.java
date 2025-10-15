import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

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
	if (xs.nilq()) return xs;
	FnList<T> original = xs;
	FnList<T> res = new FnList<T>();
	while (!xs.nilq()) {
	    res = new FnList<T>(xs.hd(), res);
	    xs = xs.tl();
	}
	return FnListUtil.append(res.reverse(), cycle(original));
    }
//
} // end of [public class FnListUtil{...}]
