import Library.LnStrm.*;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {
//
    private static final class StreamNode<T> {
	T head;
	LnStrm<T> tail;
	StreamNode(T hd, LnStrm<T> tl) { head = hd; tail = tl; }
    }

    private static<T>
	LnStrm<LnStrm<T>> preloadNext
	  (LnStrm<LnStrm<T>> remaining, PriorityQueue<StreamNode<T>> pq) {
	LnStcn<LnStrm<T>> outerCn = remaining.eval0();
	if (outerCn.nilq()) return remaining;

	LnStcn<T> innerCn = outerCn.hd().eval0();
	LnStrm<LnStrm<T>> restOuter = outerCn.tl();
	if (innerCn.consq()) {
	    pq.add(new StreamNode<T>(innerCn.hd(), innerCn.tl()));
	}
	return restOuter;
    }

    private static<T>
	LnStcn<T> mergeStep
	  (LnStrm<LnStrm<T>> remaining,
	   PriorityQueue<StreamNode<T>> pq,
	   ToIntBiFunction<T,T> cmpr) {
	// ensure we have at least one candidate
	LnStrm<LnStrm<T>> restOuter = remaining;
	while (pq.isEmpty()) {
	    LnStcn<LnStrm<T>> outerCn = restOuter.eval0();
	    if (outerCn.nilq()) {
		return new LnStcn<T>(); // no more data
	    }
	    LnStcn<T> innerCn = outerCn.hd().eval0();
	    restOuter = outerCn.tl();
	    if (innerCn.consq()) {
		pq.add(new StreamNode<T>(innerCn.hd(), innerCn.tl()));
	    }
	}

	// opportunistically pull one more outer stream so we don't miss smaller heads
	restOuter = preloadNext(restOuter, pq);

	StreamNode<T> min = pq.poll();
	LnStcn<T> minTailCn = min.tail.eval0();
	if (minTailCn.consq()) {
	    pq.add(new StreamNode<T>(minTailCn.hd(), minTailCn.tl()));
	}

	final LnStrm<LnStrm<T>> nextOuter = restOuter;
	final PriorityQueue<StreamNode<T>> nextPQ = pq;
	return new LnStcn<T>(min.head,
	  new LnStrm<T>(() -> mergeStep(nextOuter, nextPQ, cmpr)));
    }

    public static<T>
	LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
	// merge multiple ordered streams into one ordered stream.
	// Streams in fxss and each inner stream are assumed to be ordered.
	return new LnStrm<T>(
	  () -> {
	      PriorityQueue<StreamNode<T>> pq =
		new PriorityQueue<StreamNode<T>>(
		  (a, b) -> cmpr.applyAsInt(a.head, b.head));
	      return mergeStep(fxss, pq, cmpr);
	  }
	);
    }
//

} // end of [public class Assign06_01{...}]
