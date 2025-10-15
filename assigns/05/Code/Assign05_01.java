// import Library.FnList.*;
    
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
	// Loop-based implementation to avoid stack overflow with large lists
	
	if (xs.nilq() || xs.tl().nilq()) {
	    return xs; // base case: empty list or single element
	}
	
	// Convert to array for easier manipulation
	java.util.List<T> list = new java.util.ArrayList<T>();
	FnList<T> current = xs;
	while (!current.nilq()) {
	    list.add(current.hd());
	    current = current.tl();
	}
	
	// Perform bottom-up merge sort
	int n = list.size();
	java.util.List<T> aux = new java.util.ArrayList<T>(n);
	for (int i = 0; i < n; i++) {
	    aux.add(null);
	}
	
	for (int sz = 1; sz < n; sz = sz + sz) {
	    for (int lo = 0; lo < n - sz; lo += sz + sz) {
		merge(list, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1), cmp);
	    }
	}
	
	// Convert back to FnList
	FnList<T> result = new FnList<T>();
	for (int i = n - 1; i >= 0; i--) {
	    result = new FnList<T>(list.get(i), result);
	}
	
	return result;
    }
    
    // Helper method to merge two sorted subarrays
    private static<T> void merge(java.util.List<T> a, java.util.List<T> aux, 
				 int lo, int mid, int hi, ToIntBiFunction<T,T> cmp) {
	// Copy to aux array
	for (int k = lo; k <= hi; k++) {
	    aux.set(k, a.get(k));
	}
	
	// Merge back to a[]
	int i = lo, j = mid + 1;
	for (int k = lo; k <= hi; k++) {
	    if (i > mid) {
		a.set(k, aux.get(j++));
	    } else if (j > hi) {
		a.set(k, aux.get(i++));
	    } else if (cmp.applyAsInt(aux.get(j), aux.get(i)) < 0) {
		a.set(k, aux.get(j++));
	    } else {
		a.set(k, aux.get(i++));
	    }
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
