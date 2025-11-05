import Library.LnStrm.*;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_01 {
//
    public static<T>
	LnStrm<T> mergeLnStrm(LnStrm<LnStrm<T>> fxss, ToIntBiFunction<T,T> cmpr) {
	// merge multiple ordered streams into one ordered stream
	// the streams are already ordered, and the first elements of each stream are also ordered
	
	return new LnStrm<T>(
	    () -> {
		// evaluate the stream of streams
		LnStcn<LnStrm<T>> cxs = fxss.eval0();
		if (cxs.nilq()) {
		    // no streams to merge
		    return new LnStcn<T>();
		}
		
		// get the first stream and evaluate it
		LnStrm<T> firstStream = cxs.head;
		LnStrm<LnStrm<T>> remainingStreams = cxs.tail;
		
		LnStcn<T> firstCn = firstStream.eval0();
		if (firstCn.nilq()) {
		    // first stream is empty, skip it and recurse
		    return mergeLnStrm(remainingStreams, cmpr).eval0();
		}
		
		// get the first element from the first stream
		final T firstHead = firstCn.head;
		final LnStrm<T> firstTail = firstCn.tail;
		
		// check if there are more streams to consider
		LnStcn<LnStrm<T>> nextCn = remainingStreams.eval0();
		if (nextCn.nilq()) {
		    // no more streams, just return the first element and continue with first stream
		    return new LnStcn<T>(firstHead, mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(firstTail, remainingStreams)),
			cmpr
		    ));
		}
		
		// there are more streams, compare first elements
		LnStrm<T> nextStream = nextCn.head;
		LnStrm<LnStrm<T>> restStreams = nextCn.tail;
		
		// evaluate next stream to get its first element
		LnStcn<T> nextStreamCn = nextStream.eval0();
		if (nextStreamCn.nilq()) {
		    // next stream is empty, skip it and recurse
		    return mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(firstStream, restStreams)),
			cmpr
		    ).eval0();
		}
		
		final T nextHead = nextStreamCn.head;
		final LnStrm<T> nextTail = nextStreamCn.tail;
		
		// compare first elements
		int cmp = cmpr.applyAsInt(firstHead, nextHead);
		if (cmp <= 0) {
		    // firstHead <= nextHead, so firstHead is the smallest
		    return new LnStcn<T>(firstHead, mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(
			    firstTail,
			    new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(nextStream, restStreams))
			)),
			cmpr
		    ));
		} else {
		    // nextHead < firstHead, so nextHead is the smallest
		    return new LnStcn<T>(nextHead, mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(
			    firstStream,
			    new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(nextTail, restStreams))
			)),
			cmpr
		    ));
		}
	    }
	);
    }
//

} // end of [public class Assign06_01{...}]

