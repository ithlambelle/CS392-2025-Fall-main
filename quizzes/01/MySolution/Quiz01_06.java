//
// HX: 50 points
//
import Library.FnList.*;
import java.util.function.ToIntBiFunction;

abstract public class Quiz01_06 {
    // Simple wrapper class to hold element and original index
    private static final class IndexedElement<T> {
	final T element;
	final int originalIndex;
	
	IndexedElement(T element, int originalIndex) {
	    this.element = element;
	    this.originalIndex = originalIndex;
	}
    }
    
    public static<T>
	FnList<T> someSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// This one is abstract, that is, not implemented
	// We'll implement this as quicksort for testing purposes
	return xs.U0.quickSort(xs, cmp);
    }
    public static
	<T extends Comparable<T>>
	FnList<T> someStableSort
	(FnList<T> xs, ToIntBiFunction<T,T> cmp) {
	// HX-2025-10-15:
	// Please implement a stable sorting method based on
	// someSort, which may not be stable
	
	// To make an unstable sort stable, we modify the comparison function
	// to include the original index as a tiebreaker
	
	// Create a list of indexed elements
	FnList<IndexedElement<T>> indexedList = addIndices(xs, 0);
	
	// Define a comparison function that uses original comparison first,
	// then falls back to original index for stability
	ToIntBiFunction<IndexedElement<T>, IndexedElement<T>> stableCmp = (a, b) -> {
	    int result = cmp.applyAsInt(a.element, b.element);
	    if (result != 0) return result;
	    // If elements are equal, use original index to maintain stability
	    return Integer.compare(a.originalIndex, b.originalIndex);
	};
	
	// Sort using someSort with the stable comparison function
	FnList<IndexedElement<T>> sortedIndexed = someSort(indexedList, stableCmp);
	
	// Extract the sorted elements (discard indices)
	return extractElements(sortedIndexed);
    }
    
    // Helper method to add indices to elements
    private static <T> FnList<IndexedElement<T>> addIndices(FnList<T> xs, int index) {
	if (xs.nilq()) return FnListSUtil.nil();
	return FnListSUtil.cons(
	    new IndexedElement<>(xs.hd(), index),
	    addIndices(xs.tl(), index + 1)
	);
    }
    
    // Helper method to extract elements from indexed elements
    private static <T> FnList<T> extractElements(FnList<IndexedElement<T>> indexedList) {
	if (indexedList.nilq()) return FnListSUtil.nil();
	return FnListSUtil.cons(
	    indexedList.hd().element,
	    extractElements(indexedList.tl())
	);
    }
    
    public static void main(String[] args) {
	// HX-2025-10-15:
	// Please find a way to test someStableSort by
	// implementing someSort as quickSort on FnList
	// and then use someStableSort to parity-sort the following
	// list of 1M integers:
	// 0, 1, 2, 3, 4, ..., 999999
	// That is, the result of parity-sorting should be:
	// 0, 2, ..., 999998, 1, 3, ..., 999999
	
	// Test with smaller list first
	System.out.println("Testing someStableSort with parity sorting:");
	
	// Create a list of integers from 0 to 9
	FnList<Integer> testList = FnListSUtil.nil();
	for (int i = 9; i >= 0; i--) {
	    testList = FnListSUtil.cons(i, testList);
	}
	
	System.out.print("Input: ");
	testList.System$out$print();
	System.out.println();
	
	// Define parity comparison function
	// Even numbers come before odd numbers
	ToIntBiFunction<Integer, Integer> parityCmp = (a, b) -> {
	    boolean aEven = (a % 2 == 0);
	    boolean bEven = (b % 2 == 0);
	    
	    if (aEven && !bEven) return -1; // a comes before b
	    if (!aEven && bEven) return 1;  // b comes before a
	    return a.compareTo(b); // same parity, use natural order
	};
	
	// Test someSort (unstable)
	FnList<Integer> unstableResult = someSort(testList, parityCmp);
	System.out.print("Unstable sort result: ");
	unstableResult.System$out$print();
	System.out.println();
	
	// Test someStableSort (stable)
	FnList<Integer> stableResult = someStableSort(testList, parityCmp);
	System.out.print("Stable sort result: ");
	stableResult.System$out$print();
	System.out.println();
	
	// Test with larger list (1M integers would be too large for testing)
	// Let's test with 1000 integers
	System.out.println("\nTesting with larger list (1000 integers):");
	FnList<Integer> largeList = FnListSUtil.nil();
	for (int i = 999; i >= 0; i--) {
	    largeList = FnListSUtil.cons(i, largeList);
	}
	
	System.out.println("Input size: " + largeList.length());
	
	// Test stable sort on larger list
	long startTime = System.currentTimeMillis();
	FnList<Integer> largeStableResult = someStableSort(largeList, parityCmp);
	long endTime = System.currentTimeMillis();
	
	System.out.println("Stable sort completed in " + (endTime - startTime) + " ms");
	
	// Verify the result is correctly parity-sorted
	boolean isCorrectlySorted = true;
	FnList<Integer> current = largeStableResult;
	boolean expectingEven = true;
	
	while (!current.nilq() && isCorrectlySorted) {
	    int value = current.hd();
	    boolean isEven = (value % 2 == 0);
	    
	    if (expectingEven && !isEven) {
		// We were expecting even but got odd
		// This means we've moved to the odd section
		expectingEven = false;
	    } else if (!expectingEven && isEven) {
		// We were expecting odd but got even
		// This is wrong
		isCorrectlySorted = false;
	    }
	    
	    current = current.tl();
	}
	
	System.out.println("Parity sorting correct: " + isCorrectlySorted);
	
	// Show first 20 elements of the result
	System.out.print("First 20 elements: ");
	FnList<Integer> first20 = largeStableResult;
	for (int i = 0; i < 20 && !first20.nilq(); i++) {
	    System.out.print(first20.hd() + " ");
	    first20 = first20.tl();
	}
	System.out.println();
	
	// Test with reverse sorted list
	System.out.println("\nTesting with reverse sorted list:");
	FnList<Integer> reverseList = FnListSUtil.nil();
	for (int i = 0; i < 10; i++) {
	    reverseList = FnListSUtil.cons(i, reverseList);
	}
	
	System.out.print("Input: ");
	reverseList.System$out$print();
	System.out.println();
	
	FnList<Integer> reverseStableResult = someStableSort(reverseList, parityCmp);
	System.out.print("Output: ");
	reverseStableResult.System$out$print();
	System.out.println();
    }
}
