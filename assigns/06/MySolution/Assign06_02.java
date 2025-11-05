import Library.LnStrm.*;
import Library.FnTuple.*;
import Library.FnTuple.FnTupl2Helper;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_02 {
    // helper method to calculate cube sum
    private static int cubeSum(int x, int y) {
	return x*x*x + y*y*y;
    }
    
    // helper method to generate stream of pairs for a given x
    private static LnStrm<FnTupl2<Integer,Integer>> pairsForX(int x) {
	return new LnStrm<FnTupl2<Integer,Integer>>(
	    () -> {
		// generate pairs (x, y) where y >= x
		return new LnStcn<FnTupl2<Integer,Integer>>(
		    new FnTupl2<Integer,Integer>(x, x),
		    pairsForX(x, x + 1)
		);
	    }
	);
    }
    
    // helper method to generate stream starting from (x, y)
    private static LnStrm<FnTupl2<Integer,Integer>> pairsForX(int x, int y) {
	return new LnStrm<FnTupl2<Integer,Integer>>(
	    () -> {
		return new LnStcn<FnTupl2<Integer,Integer>>(
		    new FnTupl2<Integer,Integer>(x, y),
		    pairsForX(x, y + 1)
		);
	    }
	);
    }
    
    // helper method to generate stream of streams for each x starting from x0
    private static LnStrm<LnStrm<FnTupl2<Integer,Integer>>> allPairsStreams(int x0) {
	return new LnStrm<LnStrm<FnTupl2<Integer,Integer>>>(
	    () -> {
		return new LnStcn<LnStrm<FnTupl2<Integer,Integer>>>(
		    pairsForX(x0),
		    allPairsStreams(x0 + 1)
		);
	    }
	);
    }
    
    public static
	LnStrm<
	  FnTupl2<Integer,Integer>>
	cubeSumOrderedIntegerPairs() {
	// return a stream of all positive integer pairs (x, y) where 0 < x <= y
	// ordered according to the sum of the cubes of the two integer components
	
	// create stream of streams, one for each x
	LnStrm<LnStrm<FnTupl2<Integer,Integer>>> streamsOfPairs = allPairsStreams(1);
	
	// merge using comparison based on cube sum
	return Assign06_01.mergeLnStrm(streamsOfPairs, 
	    (FnTupl2<Integer,Integer> p1, FnTupl2<Integer,Integer> p2) -> {
		int sum1 = cubeSum(FnTupl2Helper.getSub0(p1), FnTupl2Helper.getSub1(p1));
		int sum2 = cubeSum(FnTupl2Helper.getSub0(p2), FnTupl2Helper.getSub1(p2));
		if (sum1 < sum2) return -1;
		if (sum1 > sum2) return 1;
		return 0;
	    }
	);
    }
    
    public static
	LnStrm<Integer>
	ramanujanNumbers() {
	// return a stream of all the ramanujan numbers
	// ramanujan numbers are numbers that can be expressed as the sum of two cubes
	// in two different ways
	
	// get the stream of pairs (this creates a new stream each time)
	return ramanujanNumbersFrom(cubeSumOrderedIntegerPairs());
    }
    
    // helper method to continue searching for ramanujan numbers from a given stream
    // Lazy approach: check only two consecutive pairs at a time
    private static LnStrm<Integer> ramanujanNumbersFrom(LnStrm<FnTupl2<Integer,Integer>> pairs) {
	return new LnStrm<Integer>(
	    () -> {
		// Get first pair
		LnStcn<FnTupl2<Integer,Integer>> cn1 = pairs.eval0();
		if (!cn1.consq()) {
		    return new LnStcn<Integer>();
		}
		
		FnTupl2<Integer,Integer> p1 = cn1.head;
		LnStrm<FnTupl2<Integer,Integer>> tail1 = cn1.tail;
		int sum1 = cubeSum(FnTupl2Helper.getSub0(p1), FnTupl2Helper.getSub1(p1));
		
		// Get second pair
		LnStcn<FnTupl2<Integer,Integer>> cn2 = tail1.eval0();
		if (!cn2.consq()) {
		    return new LnStcn<Integer>();
		}
		
		FnTupl2<Integer,Integer> p2 = cn2.head;
		LnStrm<FnTupl2<Integer,Integer>> tail2 = cn2.tail;
		int sum2 = cubeSum(FnTupl2Helper.getSub0(p2), FnTupl2Helper.getSub1(p2));
		
		if (sum1 == sum2) {
		    // found a ramanujan number
		    return new LnStcn<Integer>(
			sum1,
			// continue with remaining pairs, skipping the first pair
			ramanujanNumbersFrom(tail1)
		    );
		} else {
		    // not a match, continue from second pair
		    return ramanujanNumbersFrom(tail1).eval0();
		}
	    }
	);
    }

    // helper to print limited elements from a stream
    private static <T> void printLimited(LnStrm<T> stream, int limit, String label) {
	System.out.println(label);
	int count = 0;
	LnStcn<T> cn = stream.eval0();
	while (cn.consq() && count < limit) {
	    System.out.println(cn.head);
	    count++;
	    cn = cn.tail.eval0();
	}
    }
    
    public static void main(String[] args) {
	// test cube sum ordered pairs - limit to first 20 to verify ordering
	System.out.println("testing cube sum ordered integer pairs:");
	System.out.println("first 20 pairs:");
	
	LnStrm<FnTupl2<Integer,Integer>> pairs = cubeSumOrderedIntegerPairs();
	int count = 0;
	LnStcn<FnTupl2<Integer,Integer>> cn = pairs.eval0();
	while (cn.consq() && count < 20) {
	    FnTupl2<Integer,Integer> p = cn.head;
	    int sum = cubeSum(FnTupl2Helper.getSub0(p), FnTupl2Helper.getSub1(p));
	    System.out.println("(" + FnTupl2Helper.getSub0(p) + "," + FnTupl2Helper.getSub1(p) + ") -> " + sum);
	    count++;
	    cn = cn.tail.eval0();
	}
	
	// test ramanujan numbers - limit to first 3
	// Should find 1729 after checking ~61 pairs, which should take seconds, not minutes
	System.out.println("\nfirst 3 ramanujan numbers:");
	System.out.println("Expected: 1729, 4104, 13832...");
	System.out.println("(This should complete in a few seconds)");
	
	// test ramanujan numbers
	long startTime = System.currentTimeMillis();
	
	LnStrm<Integer> ramanujan = ramanujanNumbers();
	int ramCount = 0;
	LnStcn<Integer> ramCn = ramanujan.eval0();
	while (ramCn.consq() && ramCount < 3) {
	    long elapsed = System.currentTimeMillis() - startTime;
	    System.out.println("Found Ramanujan number #" + (ramCount + 1) + ": " + ramCn.head + " (took " + elapsed + " ms)");
	    ramCount++;
	    ramCn = ramCn.tail.eval0();
	}
	
	long totalTime = System.currentTimeMillis() - startTime;
	System.out.println("\ntest completed in " + totalTime + " ms");
	return;
    }

} // end of [public class Assign06_02{...}]

