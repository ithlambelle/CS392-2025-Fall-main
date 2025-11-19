import Library.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_02 {

    public static
	<T extends Comparable<T>>
	FnList<T> insertSort(FnList<T> xs) {
	return insertSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T>
	FnList<T>
	insertSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-08: Please implement this method
	// Loop-based implementation to avoid stack overflow
	FnList<T> result = new FnList<T>();
	
	while (!xs.nilq()) {
	    T current = xs.hd();
	    xs = xs.tl();
	    
	    // Insert current element into the sorted result list
	    result = insertIntoSorted(result, current, cmp);
	}
	
	return result;
    }
    
    // Helper method to insert an element into a sorted list
    private static<T> FnList<T> insertIntoSorted(FnList<T> sorted, T element, ToIntBiFunction<T,T> cmp) {
	FnList<T> result = new FnList<T>();
	boolean inserted = false;
	
	// Process the sorted list from left to right
	while (!sorted.nilq()) {
	    T current = sorted.hd();
	    sorted = sorted.tl();
	    
	    // If we haven't inserted yet and current element is greater than element to insert
	    if (!inserted && cmp.applyAsInt(element, current) <= 0) {
		result = new FnList<T>(element, new FnList<T>(current, result));
		inserted = true;
	    } else {
		result = new FnList<T>(current, result);
	    }
	}
	
	// If we haven't inserted the element yet, add it at the end
	if (!inserted) {
	    result = new FnList<T>(element, result);
	}
	
	return result.reverse();
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// insertSort to the following list of 1M numbers:
	// 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998.
	
	System.out.println("Testing insertSort with small lists...");
	
	// Test with small list
	FnList<Integer> small = new FnList<Integer>(3, 
	    new FnList<Integer>(1, 
		new FnList<Integer>(4, 
		    new FnList<Integer>(1, 
			new FnList<Integer>(5, 
			    new FnList<Integer>(9, 
				new FnList<Integer>(2, 
				    new FnList<Integer>(6, 
					new FnList<Integer>(5, 
					    new FnList<Integer>())))))))));
	
	System.out.print("Original: ");
	small.System$out$print();
	System.out.println();
	
	FnList<Integer> sortedSmall = insertSort(small);
	System.out.print("Sorted: ");
	sortedSmall.System$out$print();
	System.out.println();
	
	// Test with the specific pattern: 1, 0, 3, 2, 5, 4, 7, 6, 9, 8, 11, 10, ..., 999999, 999998
	System.out.println("\nTesting insertSort with 1,000,000 numbers in pattern 1,0,3,2,5,4,7,6,9,8,11,10,...");
	
	FnList<Integer> pattern = new FnList<Integer>();
	for (int i = 999999; i >= 0; i--) {
	    if (i % 2 == 0) {
		pattern = new FnList<Integer>(i + 1, pattern);
	    } else {
		pattern = new FnList<Integer>(i - 1, pattern);
	    }
	}
	
	long startTime = System.currentTimeMillis();
	FnList<Integer> sortedPattern = insertSort(pattern);
	long endTime = System.currentTimeMillis();
	
	System.out.println("Sorting completed in " + (endTime - startTime) + " ms");
	
	// Verify the first few elements are sorted
	FnList<Integer> check = sortedPattern;
	boolean isSorted = true;
	Integer prev = null;
	int count = 0;
	while (!check.nilq() && count < 10) {
	    if (prev != null && prev > check.hd()) {
		isSorted = false;
		break;
	    }
	    prev = check.hd();
	    check = check.tl();
	    count++;
	}
	
	System.out.println("First 10 elements are " + (isSorted ? "sorted" : "not sorted"));
	if (count > 0) {
	    System.out.print("First 10 elements: ");
	    FnList<Integer> first10 = sortedPattern;
	    for (int i = 0; i < 10 && !first10.nilq(); i++) {
		System.out.print(first10.hd() + " ");
		first10 = first10.tl();
	    }
	    System.out.println();
	}
    }

} // end of [public class Assign05_02{...}]
