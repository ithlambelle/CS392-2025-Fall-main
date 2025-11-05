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
	// Compare first stream with second stream, then recurse
	
	return new LnStrm<T>(
	    () -> {
		// evaluate the stream of streams once
		LnStcn<LnStrm<T>> cxs = fxss.eval0();
		if (cxs.nilq()) {
		    // no streams to merge
		    return new LnStcn<T>();
		}
		
		// Get first stream
		final LnStrm<T> firstStream = cxs.head;
		final LnStrm<LnStrm<T>> remainingStreams = cxs.tail;
		
		// Evaluate first stream
		LnStcn<T> firstCn = firstStream.eval0();
		if (firstCn.nilq()) {
		    // first stream is empty, merge remaining streams
		    return mergeLnStrm(remainingStreams, cmpr).eval0();
		}
		
		final T firstHead = firstCn.head;
		final LnStrm<T> firstTail = firstCn.tail;
		
		// Check if there are more streams
		LnStcn<LnStrm<T>> nextCn = remainingStreams.eval0();
		if (nextCn.nilq()) {
		    // only one stream, return its head
		    return new LnStcn<T>(firstHead, firstTail);
		}
		
		// Compare with next stream
		final LnStrm<T> nextStream = nextCn.head;
		final LnStrm<LnStrm<T>> restStreams = nextCn.tail;
		
		LnStcn<T> nextCn2 = nextStream.eval0();
		if (nextCn2.nilq()) {
		    // next stream is empty, skip it and continue
		    return mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(
			    new LnStrm<T>(() -> new LnStcn<T>(firstHead, firstTail)),
			    restStreams
			)),
			cmpr
		    ).eval0();
		}
		
		final T nextHead = nextCn2.head;
		final LnStrm<T> nextTail = nextCn2.tail;
		
		// Compare and return the smaller one
		int cmp = cmpr.applyAsInt(firstHead, nextHead);
		if (cmp <= 0) {
		    // firstHead is smaller or equal
		    return new LnStcn<T>(firstHead, mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(
			    firstTail,
			    new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(
				new LnStrm<T>(() -> new LnStcn<T>(nextHead, nextTail)),
				restStreams
			    ))
			)),
			cmpr
		    ));
		} else {
		    // nextHead is smaller
		    return new LnStcn<T>(nextHead, mergeLnStrm(
			new LnStrm<LnStrm<T>>(() -> new LnStcn<LnStrm<T>>(
			    new LnStrm<T>(() -> new LnStcn<T>(firstHead, firstTail)),
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

