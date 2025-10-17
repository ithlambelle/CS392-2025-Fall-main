//
// HX: 40 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> xs) {
	// HX-2025-10-12:
	// Please implement (stable) insert sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
	
	if (xs.nilq1()) {
	    return xs; // empty list is already sorted
	}
	
	// We need to rearrange existing nodes without creating new ones
	// This is a complex operation that requires careful manipulation
	// of the existing nodes using only public methods
	
	// Start with the first element as the sorted portion
	LnList<T> sorted = xs;
	LnList<T> current = xs.tl1();
	
	// Process each remaining element
	while (!current.nilq1()) {
	    T currentValue = current.hd1();
	    LnList<T> next = current.tl1();
	    
	    // Find the correct position in the sorted portion
	    LnList<T> prev = null;
	    LnList<T> temp = sorted;
	    
	    while (!temp.nilq1() && temp.hd1().compareTo(currentValue) <= 0) {
		prev = temp;
		temp = temp.tl1();
	    }
	    
	    // Remove current node from its current position
	    if (prev == null) {
		// Current is already at the beginning, no need to move
		sorted = current;
	    } else {
		// Unlink current from its current position
		prev.unlink();
		// Link current to the next element
		current.link(next);
		
		// Insert current at the correct position
		if (temp.nilq1()) {
		    // Insert at the end
		    prev.link(current);
		} else {
		    // Insert in the middle
		    current.link(temp);
		    if (prev.nilq1()) {
			sorted = current;
		    } else {
			prev.link(current);
		    }
		}
	    }
	    
	    current = next;
	}
	
	return sorted;
    }
    
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for LnListInsertSort
	
	// Test with integers
	System.out.println("Testing LnListInsertSort with integers:");
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
	
	LnList<Integer> sortedIntList = LnListInsertSort(intList);
	System.out.print("Output: ");
	sortedIntList.System$out$print1();
	System.out.println();
	
	// Test with strings
	System.out.println("\nTesting LnListInsertSort with strings:");
	LnList<String> strList = new LnList<String>();
	strList.append1(new LnList<String>("zebra", new LnList<String>()));
	strList.append1(new LnList<String>("apple", new LnList<String>()));
	strList.append1(new LnList<String>("banana", new LnList<String>()));
	strList.append1(new LnList<String>("cherry", new LnList<String>()));
	strList.append1(new LnList<String>("date", new LnList<String>()));
	
	System.out.print("Input: ");
	strList.System$out$print1();
	System.out.println();
	
	LnList<String> sortedStrList = LnListInsertSort(strList);
	System.out.print("Output: ");
	sortedStrList.System$out$print1();
	System.out.println();
	
	// Test with empty list
	System.out.println("\nTesting LnListInsertSort with empty list:");
	LnList<Integer> emptyList = new LnList<Integer>();
	LnList<Integer> sortedEmptyList = LnListInsertSort(emptyList);
	System.out.print("Input: ");
	emptyList.System$out$print1();
	System.out.println();
	System.out.print("Output: ");
	sortedEmptyList.System$out$print1();
	System.out.println();
	
	// Test with single element
	System.out.println("\nTesting LnListInsertSort with single element:");
	LnList<Integer> singleList = new LnList<Integer>(42, new LnList<Integer>());
	LnList<Integer> sortedSingleList = LnListInsertSort(singleList);
	System.out.print("Input: ");
	singleList.System$out$print1();
	System.out.println();
	System.out.print("Output: ");
	sortedSingleList.System$out$print1();
	System.out.println();
	
	// Test with duplicate elements
	System.out.println("\nTesting LnListInsertSort with duplicate elements:");
	LnList<Integer> dupList = new LnList<Integer>();
	dupList.append1(new LnList<Integer>(3, new LnList<Integer>()));
	dupList.append1(new LnList<Integer>(1, new LnList<Integer>()));
	dupList.append1(new LnList<Integer>(3, new LnList<Integer>()));
	dupList.append1(new LnList<Integer>(2, new LnList<Integer>()));
	dupList.append1(new LnList<Integer>(1, new LnList<Integer>()));
	
	System.out.print("Input: ");
	dupList.System$out$print1();
	System.out.println();
	
	LnList<Integer> sortedDupList = LnListInsertSort(dupList);
	System.out.print("Output: ");
	sortedDupList.System$out$print1();
	System.out.println();
    }
}
