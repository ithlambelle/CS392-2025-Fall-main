//
// HX: 50 points
//
import Library.LnList.*;
// Please see Library/LnList for LnList.java
public class Quiz01_05 {
    public static
	<T extends Comparable<T>>
	LnList<T> LnListQuickSort(LnList<T> head) {
	// HX-2025-10-12:
	// Please implement quicksort on a linked list (LnList).
	// Note that you are not allowed to modify the definition
	// of the LnList class. You can only use the public methods
	// provided by the LnList class
	
	if (head.nilq1() || head.tl1().nilq1()) return head;

	// count nodes to choose a random pivot index in [0, n)
	int n = 0;
	for (LnList<T> t = head; !t.nilq1(); t = t.tl1()) n++;
	int pivotIdx = (int)(Math.random() * n);

	// walk to pivot node and read pivot value
	LnList<T> t = head;
	for (int i = 0; i < pivotIdx; i++) t = t.tl1();
	T pivot = t.hd1();

	// partition by detaching nodes from head into three chains (<, ==, >)
	Part<T> p = partitionDetach(head, pivot);

	// recurse on less and greater
	LnList<T> lessSorted  = LnListQuickSort(p.less);
	LnList<T> greatSorted = LnListQuickSort(p.greater);

	// concatenate: lessSorted -> equal -> greatSorted
	return concat3(lessSorted, p.equal, greatSorted);
    }

    // hold the three chains for partition
    private static final class Part<U> {
	LnList<U> less, equal, greater;
	Part(LnList<U> l, LnList<U> e, LnList<U> g) { less = l; equal = e; greater = g; }
    }

    // detach every node from src and append to one of {less, equal, greater} by relinking tails.
    private static <T extends Comparable<T>> Part<T> partitionDetach(LnList<T> src, T pivot) {
	LnList<T> lessH = null, lessT = null;
	LnList<T> eqH   = null, eqT   = null;
	LnList<T> gtH   = null, gtT   = null;
	// using null instead of LnListSUtil.nil() to avoid constructors

	while (!src.nilq1()) {
	    LnList<T> node = src;      // take head node
	    src = src.tl1();
	    node.unlink();             // node becomes a 1-node chain

	    int c = node.hd1().compareTo(pivot);
	    if (c < 0) {
		if (lessH == null) { lessH = lessT = node; }
		else { lessT.link(node); lessT = node; }
	    } else if (c == 0) {
		if (eqH == null) { eqH = eqT = node; }
		else { eqT.link(node); eqT = node; }
	    } else {
		if (gtH == null) { gtH = gtT = node; }
		else { gtT.link(node); gtT = node; }
	    }
	}
	return new Part<>(lessH, eqH, gtH);
    }

    // concatenate a -> b -> c by relinking tails only (no new nodes)
    private static <T> LnList<T> concat3(LnList<T> a, LnList<T> b, LnList<T> c) {
	if (a != null) {
	    LnList<T> tail = last(a);
	    if (b != null) { tail.link(b); tail = last(b); }
	    if (c != null) { tail.link(c); }
	    return a;
	}
	if (b != null) {
	    if (c != null) last(b).link(c);
	    return b;
	}
	return c; // maybe null
    }

    private static <T> LnList<T> last(LnList<T> h) {
	LnList<T> t = h;
	while (!t.tl1().nilq1()) t = t.tl1();
	return t;
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
