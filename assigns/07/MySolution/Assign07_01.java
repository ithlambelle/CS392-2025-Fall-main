
import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnGtree.*;
import Library.MyDeque.*;

import java.util.function.Consumer;

public class Assign07_01 {
//
    public static<T> LnStrm<T>
	BFirstEnumerate(FnGtree<T> root) {
	// Please add your code here
	// You need to use your solution to Assign04_02
	// If you need to change your code for Assign04_02
	// Please detail what changes are made
	MyDequeList<FnGtree<T>>
	    deque = new MyDequeList<FnGtree<T>>();
	try {
	    deque.renque$exn(root);
	} catch (Exception e) {
	    throw new RuntimeException("Failed to add root to deque", e);
	}
	return BFirstEnumerate_helper(deque);
    }
    private static<T> LnStrm<T>
	BFirstEnumerate_helper(MyDequeList<FnGtree<T>> deque) {
	return new LnStrm<T>(
	  () -> {
	      // The deque should not be empty on first call since we added root
	      // Debug: check deque state when lambda is evaluated
	      // int dequeSize = deque.size();
	      // boolean dequeEmpty = deque.isEmpty();
	      // System.out.println("DEBUG: Lambda evaluated, deque size=" + dequeSize + ", empty=" + dequeEmpty);
	      
	      if (deque.isEmpty()) {
		  return new LnStcn<T>();
	      } else {
		  FnGtree<T> node = deque.fdeque$raw();
		  node.children().foritm((tx) -> deque.renque$exn(tx));
		  return new LnStcn<T>(node.value(), BFirstEnumerate_helper(deque));
	      }
	  }
        );
    }
//
    public static<T> LnStrm<T>
	DFirstEnumerate(FnGtree<T> root) {
	// Please add your code here
	// You need to use your solution to Assign04_02
	// If you need to change your code for Assign04_02
	// Please detail what changes are made
	MyDequeList<FnGtree<T>>
	    deque = new MyDequeList<FnGtree<T>>();
	deque.fenque$exn(root); return DFirstEnumerate_helper(deque);
    }
    private static<T> LnStrm<T>
	DFirstEnumerate_helper(MyDequeList<FnGtree<T>> deque) {
	return new LnStrm<T>(
	  () -> {
	      if (deque.isEmpty()) {
		  return new LnStcn<T>();
	      } else {
		  FnGtree<T> node = deque.fdeque$raw();
		  node.children().rforitm((tx) -> deque.fenque$exn(tx));
		  return new LnStcn<T>(node.value(), DFirstEnumerate_helper(deque));
	      }
	  }
        );
    }
//
} // end of [public class Assign07_01{...}]

