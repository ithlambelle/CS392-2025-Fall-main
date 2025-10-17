//
// HX: 50 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_05 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListQuickSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement quicksort on a linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
	
	if (xs.nilq1() || xs.tl1().nilq1()) return xs;
	
	// Choose random pivot
	int count = 0;
	LnList<T> temp = xs;
	while (!temp.nilq1()) {
	    count++;
	    temp = temp.tl1();
	}
	
	int pivotIndex = (int)(Math.random() * count);
	LnList<T> pivot = xs;
	for (int i = 0; i < pivotIndex; i++) {
	    pivot = pivot.tl1();
	}
	
	T pivotValue = pivot.hd1();
	
	// Partition the list around the pivot
	LnList<T> left = partition(xs, pivotValue, true);  // < pivot
	LnList<T> right = partition(xs, pivotValue, false); // > pivot
	
	// Recursively sort partitions
	left = LnListQuickSort(left);
	right = LnListQuickSort(right);
	
	// Combine results: left + pivot + right
	return concat(left, new LnList<T>(pivotValue, right));
    }
    
    private static <T extends Comparable<T>> LnList<T> partition(LnList<T> xs, T pivot, boolean lessThan) {
	if (xs.nilq1()) return new LnList<T>();
	
	T head = xs.hd1();
	LnList<T> tail = xs.tl1();
	
	boolean shouldInclude = lessThan ? head.compareTo(pivot) < 0 : head.compareTo(pivot) > 0;
	
	if (shouldInclude) {
	    return new LnList<T>(head, partition(tail, pivot, lessThan));
	} else {
	    return partition(tail, pivot, lessThan);
	}
    }
    
    private static <T extends Comparable<T>> LnList<T> concat(LnList<T> a, LnList<T> b) {
	if (a.nilq1()) return b;
	return new LnList<T>(a.hd1(), concat(a.tl1(), b));
    }
    
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for LnListQuickSort
	
	// Test with integers
	System.out.println("Testing LnListQuickSort with integers:");
	LnList<Integer> intList = new LnList<Integer>();
	intList.append1(new LnList<Integer>(5, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(2, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(8, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(1, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(9, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(3, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(7, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(4, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(6, new LnList<Integer>()));
	intList.append1(new LnList<Integer>(0, new LnList<Integer>()));
	
	System.out.print("Input: ");
	intList.System$out$print1();
	System.out.println();
	
	LnList<Integer> sortedIntList = LnListQuickSort(intList);
	System.out.print("Output: ");
	sortedIntList.System$out$print1();
	System.out.println();
	
	// Test with strings
	System.out.println("\nTesting LnListQuickSort with strings:");
	LnList<String> strList = new LnList<String>();
	strList.append1(new LnList<String>("zebra", new LnList<String>()));
	strList.append1(new LnList<String>("apple", new LnList<String>()));
	strList.append1(new LnList<String>("banana", new LnList<String>()));
	strList.append1(new LnList<String>("cherry", new LnList<String>()));
	strList.append1(new LnList<String>("date", new LnList<String>()));
	
	System.out.print("Input: ");
	strList.System$out$print1();
	System.out.println();
	
	LnList<String> sortedStrList = LnListQuickSort(strList);
	System.out.print("Output: ");
	sortedStrList.System$out$print1();
	System.out.println();
	
	// Test with empty list
	System.out.println("\nTesting LnListQuickSort with empty list:");
	LnList<Integer> emptyList = new LnList<Integer>();
	LnList<Integer> sortedEmptyList = LnListQuickSort(emptyList);
	System.out.print("Input: ");
	emptyList.System$out$print1();
	System.out.println();
	System.out.print("Output: ");
	sortedEmptyList.System$out$print1();
	System.out.println();
	
	// Test with single element
	System.out.println("\nTesting LnListQuickSort with single element:");
	LnList<Integer> singleList = new LnList<Integer>(42, new LnList<Integer>());
	LnList<Integer> sortedSingleList = LnListQuickSort(singleList);
	System.out.print("Input: ");
	singleList.System$out$print1();
	System.out.println();
	System.out.print("Output: ");
	sortedSingleList.System$out$print1();
	System.out.println();
	
	// Test with already sorted list
	System.out.println("\nTesting LnListQuickSort with already sorted list:");
	LnList<Integer> sortedList = new LnList<Integer>();
	sortedList.append1(new LnList<Integer>(1, new LnList<Integer>()));
	sortedList.append1(new LnList<Integer>(2, new LnList<Integer>()));
	sortedList.append1(new LnList<Integer>(3, new LnList<Integer>()));
	sortedList.append1(new LnList<Integer>(4, new LnList<Integer>()));
	sortedList.append1(new LnList<Integer>(5, new LnList<Integer>()));
	
	System.out.print("Input: ");
	sortedList.System$out$print1();
	System.out.println();
	
	LnList<Integer> sortedSortedList = LnListQuickSort(sortedList);
	System.out.print("Output: ");
	sortedSortedList.System$out$print1();
	System.out.println();
    }
}
