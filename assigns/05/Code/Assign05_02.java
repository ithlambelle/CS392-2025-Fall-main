// import Library.FnList.*;
    
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
	
	if (xs.nilq() || xs.tl().nilq()) {
	    return xs; // base case: empty list or single element
	}
	
	// Convert to array for more efficient manipulation
	java.util.List<T> list = new java.util.ArrayList<T>();
	FnList<T> current = xs;
	while (!current.nilq()) {
	    list.add(current.hd());
	    current = current.tl();
	}
	
	// Insertion sort on array
	int n = list.size();
	for (int i = 1; i < n; i++) {
	    T key = list.get(i);
	    int j = i - 1;
	    
	    // Move elements that are greater than key one position ahead
	    while (j >= 0 && cmp.applyAsInt(list.get(j), key) > 0) {
		list.set(j + 1, list.get(j));
		j = j - 1;
	    }
	    list.set(j + 1, key);
	}
	
	// Convert back to FnList
	FnList<T> result = new FnList<T>();
	for (int i = n - 1; i >= 0; i--) {
	    result = new FnList<T>(list.get(i), result);
	}
	
	return result;
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
	
	// Note: Random 1M integers would take too long with insertion sort (O(n²))
	// The assignment specifically asks for the pattern test, which works efficiently
    }

} // end of [public class Assign05_02{...}]
