import Library.FnList.*;
    
import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign05_01 {

    public static
	<T extends Comparable<T>>
	FnList<T> mergeSort(FnList<T> xs) {
	return mergeSort(xs, (x1, x2) -> x1.compareTo(x2));
    }
//
    public static<T>
	FnList<T>
	mergeSort(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-08: Please implement this method
	if (xs.nilq() || xs.tl().nilq()) {
	    return xs; // base case: empty list or single element
	}
	
	// Split the list into two halves
	FnList<T>[] halves = splitList(xs);
	FnList<T> left = halves[0];
	FnList<T> right = halves[1];
	
	// Recursively sort both halves
	FnList<T> sortedLeft = mergeSort(left, cmp);
	FnList<T> sortedRight = mergeSort(right, cmp);
	
	// Merge the sorted halves
	return merge(sortedLeft, sortedRight, cmp);
    }
    
    // Helper method to split a list into two halves
    private static<T> FnList<T>[] splitList(FnList<T> xs) {
	@SuppressWarnings("unchecked")
	FnList<T>[] result = new FnList[2];
	
	FnList<T> left = new FnList<T>();
	FnList<T> right = new FnList<T>();
	
	boolean toLeft = true;
	while (!xs.nilq()) {
	    if (toLeft) {
		left = new FnList<T>(xs.hd(), left);
	    } else {
		right = new FnList<T>(xs.hd(), right);
	    }
	    toLeft = !toLeft;
	    xs = xs.tl();
	}
	
	result[0] = left.reverse();
	result[1] = right.reverse();
	return result;
    }
    
    // Helper method to merge two sorted lists
    private static<T> FnList<T> merge(FnList<T> left, FnList<T> right, ToIntBiFunction<T,T> cmp) {
	if (left.nilq()) return right;
	if (right.nilq()) return left;
	
	if (cmp.applyAsInt(left.hd(), right.hd()) <= 0) {
	    return new FnList<T>(left.hd(), merge(left.tl(), right, cmp));
	} else {
	    return new FnList<T>(right.hd(), merge(left, right.tl(), cmp));
	}
    }

    public static void main(String[] args) {
	// Please write some testing code that applies
	// mergeSort to a randomly generated list of 1000,000 integers.
	
	System.out.println("Testing mergeSort with small lists...");
	
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
	
	FnList<Integer> sortedSmall = mergeSort(small);
	System.out.print("Sorted: ");
	sortedSmall.System$out$print();
	System.out.println();
	
	// Test with 1M random integers
	System.out.println("\nTesting mergeSort with 1,000,000 random integers...");
	Random rand = new Random(42); // fixed seed for reproducibility
	
	FnList<Integer> large = new FnList<Integer>();
	for (int i = 0; i < 1000000; i++) {
	    large = new FnList<Integer>(rand.nextInt(), large);
	}
	
	long startTime = System.currentTimeMillis();
	FnList<Integer> sortedLarge = mergeSort(large);
	long endTime = System.currentTimeMillis();
	
	System.out.println("Sorting completed in " + (endTime - startTime) + " ms");
	
	// Verify the first few elements are sorted
	FnList<Integer> check = sortedLarge;
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
	    FnList<Integer> first10 = sortedLarge;
	    for (int i = 0; i < 10 && !first10.nilq(); i++) {
		System.out.print(first10.hd() + " ");
		first10 = first10.tl();
	    }
	    System.out.println();
	}
    }

} // end of [public class Assign05_01{...}]
