import Library.LnStrm.*;
import Library.FnTuple.*;

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
		int sum1 = cubeSum(p1.sub0, p1.sub1);
		int sum2 = cubeSum(p2.sub0, p2.sub1);
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
	
	LnStrm<FnTupl2<Integer,Integer>> pairs = cubeSumOrderedIntegerPairs();
	
	return new LnStrm<Integer>(
	    () -> {
		// find consecutive pairs with the same cube sum
		LnStcn<FnTupl2<Integer,Integer>> cn1 = pairs.eval0();
		if (cn1.nilq()) {
		    return new LnStcn<Integer>();
		}
		
		FnTupl2<Integer,Integer> p1 = cn1.head;
		LnStrm<FnTupl2<Integer,Integer>> tail1 = cn1.tail;
		
		LnStcn<FnTupl2<Integer,Integer>> cn2 = tail1.eval0();
		if (cn2.nilq()) {
		    // only one pair, not a ramanujan number
		    return new LnStcn<Integer>();
		}
		
		FnTupl2<Integer,Integer> p2 = cn2.head;
		LnStrm<FnTupl2<Integer,Integer>> tail2 = cn2.tail;
		
		int sum1 = cubeSum(p1.sub0, p1.sub1);
		int sum2 = cubeSum(p2.sub0, p2.sub1);
		
		if (sum1 == sum2) {
		    // found a ramanujan number
		    return new LnStcn<Integer>(
			sum1,
			// continue with remaining pairs, skipping the first two
			ramanujanNumbersFrom(tail2)
		    );
		} else {
		    // not a ramanujan number, continue searching
		    return ramanujanNumbersFrom(tail1).eval0();
		}
	    }
	);
    }
    
    // helper method to continue searching for ramanujan numbers from a given stream
    private static LnStrm<Integer> ramanujanNumbersFrom(LnStrm<FnTupl2<Integer,Integer>> pairs) {
	return new LnStrm<Integer>(
	    () -> {
		LnStcn<FnTupl2<Integer,Integer>> cn1 = pairs.eval0();
		if (cn1.nilq()) {
		    return new LnStcn<Integer>();
		}
		
		FnTupl2<Integer,Integer> p1 = cn1.head;
		LnStrm<FnTupl2<Integer,Integer>> tail1 = cn1.tail;
		
		LnStcn<FnTupl2<Integer,Integer>> cn2 = tail1.eval0();
		if (cn2.nilq()) {
		    return new LnStcn<Integer>();
		}
		
		FnTupl2<Integer,Integer> p2 = cn2.head;
		LnStrm<FnTupl2<Integer,Integer>> tail2 = cn2.tail;
		
		int sum1 = cubeSum(p1.sub0, p1.sub1);
		int sum2 = cubeSum(p2.sub0, p2.sub1);
		
		if (sum1 == sum2) {
		    return new LnStcn<Integer>(
			sum1,
			ramanujanNumbersFrom(tail2)
		    );
		} else {
		    return ramanujanNumbersFrom(tail1).eval0();
		}
	    }
	);
    }

    public static void main(String[] args) {
	// test cube sum ordered pairs
	System.out.println("testing cube sum ordered integer pairs:");
	System.out.println("first 10 pairs:");
	
	LnStrm<FnTupl2<Integer,Integer>> pairs = cubeSumOrderedIntegerPairs();
	int[] pairCount = {0};
	LnStrmSUtil.foritm0(pairs, (FnTupl2<Integer,Integer> p) -> {
	    if (pairCount[0] < 10) {
		int sum = cubeSum(p.sub0, p.sub1);
		System.out.println("(" + p.sub0 + "," + p.sub1 + ") -> " + sum);
		pairCount[0]++;
	    }
	});
	
	// test ramanujan numbers
	System.out.println("\nfirst 10 ramanujan numbers:");
	LnStrm<Integer> ramanujan = ramanujanNumbers();
	int[] ramanujanCount = {0};
	LnStrmSUtil.foritm0(ramanujan, (Integer n) -> {
	    if (ramanujanCount[0] < 10) {
		System.out.println(n);
		ramanujanCount[0]++;
	    }
	});
	
	return; // minimal testing code
    }

} // end of [public class Assign06_02{...}]

