package Library.FnTree;


public class FnTreeSUtil {
//
    public static<T>
	FnTree<T> nil() {
	return new FnTree<T>();
    }
    public static<T>
	FnTree<T> sing(T x0) {
	return cons(x0, nil(), nil());
    }
    public static<T>
	FnTree<T> cons
	(T x0, FnTree<T> lxs, FnTree<T> rxs) {
	return new FnTree<T>(x0, lxs, rxs);
    }
//
} // end of [public class FnTreeSUtil{...}]
