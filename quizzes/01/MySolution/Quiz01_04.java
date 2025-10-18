//
// HX: 40 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_04 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListInsertSort(LnList<T> head) {
	// HX-2025-10-12:
	// Please implement (stable) insert sort on a
	// linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
	
	if (head.nilq1() || head.tl1().nilq1()) return head;

	// sorted = first node (detach its tail)
	LnList<T> sorted = head;
	LnList<T> unsorted = head.tl1();
	sorted.link(LnListSUtil.nil());     // make sorted a 1-node chain
	// note: feedback mentioned LnList.nil() but that method doesn't exist in this library
	// using LnListSUtil.nil() as the official empty sentinel method

	// take nodes from unsorted and splice into sorted at the first position with hd > x  (strict > to keep stability)
	while (!unsorted.nilq1()) {
	    LnList<T> node = unsorted;      // detach node from unsorted
	    unsorted = unsorted.tl1();
	    node.unlink();      // node now stands alone

	    if (node.hd1().compareTo(sorted.hd1()) < 0) {
		// insert at head of sorted
		node.link(sorted);
		sorted = node;
	    } else {
		// walk sorted with (prev, cur), stop at first cur.hd > node.hd
		LnList<T> prev = sorted;
		LnList<T> cur  = sorted.tl1();
		while (!cur.nilq1() && cur.hd1().compareTo(node.hd1()) <= 0) {
		    prev = cur;
		    cur  = cur.tl1();
		}
		// splice node between prev and cur
		prev.link(node);
		node.link(cur);
	    }
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
