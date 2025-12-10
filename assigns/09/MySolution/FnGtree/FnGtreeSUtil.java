package Library.FnGtree;

import Library.FnList.*;
import Library.LnStrm.*;
import Library.MyPQueue.*;

public class FnGtreeSUtil {
//
    public interface PriorityNode {
	int priority();
    }
//
    public static<T> LnStrm<T>
	PFirstEnumerate(FnGtree<T> root) {
	MyPQueueArray<FnGtree<T>> queue =
	    new MyPQueueArray<FnGtree<T>>
		(1024, (a, b) -> Integer.compare(priorityOf(a), priorityOf(b)));
	queue.enque$exn(root);
	return helper(queue);
    }

    private static int priorityOf(FnGtree<?> node) {
	if (node instanceof PriorityNode) {
	    return ((PriorityNode) node).priority();
	}
	return 0;
    }

    private static<T> LnStrm<T>
	helper(MyPQueueArray<FnGtree<T>> queue) {
	return new LnStrm<T>(
	  () -> {
	      if (queue.isEmpty()) {
		  return new LnStcn<T>();
	      } else {
		  FnGtree<T> node = queue.deque$raw();
		  node.children().foritm((tx) -> queue.enque$exn(tx));
		  return new LnStcn<T>(node.value(), helper(queue));
	      }
	  }
        );
    }
//
} // end of [public class FnGtreeSUtil{...}]
