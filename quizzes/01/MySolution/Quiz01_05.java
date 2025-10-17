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
	
	if (xs.nilq1()) {
	    return xs; // empty list is already sorted
	}
	
	// We need to rearrange existing nodes without creating new ones
	// This is a complex operation that requires careful manipulation
	// of the existing nodes using only public methods
	
	// For quicksort, we need to partition the list around a pivot
	// and recursively sort the partitions
	
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
	LnList<T> left = new LnList<T>();
	LnList<T> right = new LnList<T>();
	LnList<T> current = xs;
	
	while (!current.nilq1()) {
	    T value = current.hd1();
	    LnList<T> next = current.tl1();
	    
	    if (value.compareTo(pivotValue) < 0) {
		// Add to left partition
		if (left.nilq1()) {
		    left = current;
		} else {
		    left.append1(current);
		}
	    } else if (value.compareTo(pivotValue) > 0) {
		// Add to right partition
		if (right.nilq1()) {
		    right = current;
		} else {
		    right.append1(current);
		}
	    }
	    
	    current = next;
	}
	
	// Recursively sort partitions
	LnList<T> sortedLeft = LnListQuickSort(left);
	LnList<T> sortedRight = LnListQuickSort(right);
	
	// Combine results
	if (sortedLeft.nilq1()) {
	    if (sortedRight.nilq1()) {
		return pivot;
	    } else {
		pivot.link(sortedRight);
		return pivot;
	    }
	} else {
	    sortedLeft.append1(pivot);
	    if (!sortedRight.nilq1()) {
		pivot.link(sortedRight);
	    }
	    return sortedLeft;
	}
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
