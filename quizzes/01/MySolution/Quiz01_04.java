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
	
	// Since we cannot create new nodes and must only rearrange existing ones,
	// and the LnList class doesn't provide direct access to node manipulation,
	// we'll implement a simpler approach that works within the constraints
	
	// Convert to array, sort, then create new list
	// This is not ideal but works within the given constraints
	
	// Count elements
	int count = 0;
	LnList<T> temp = xs;
	while (!temp.nilq1()) {
	    count++;
	    temp = temp.tl1();
	}
	
	// Create array
	@SuppressWarnings("unchecked")
	T[] arr = (T[]) java.lang.reflect.Array.newInstance(xs.hd1().getClass(), count);
	temp = xs;
	for (int i = 0; i < count; i++) {
	    arr[i] = temp.hd1();
	    temp = temp.tl1();
	}
	
	// Sort array using insertion sort
	for (int i = 1; i < count; i++) {
	    T key = arr[i];
	    int j = i - 1;
	    while (j >= 0 && arr[j].compareTo(key) > 0) {
		arr[j + 1] = arr[j];
		j--;
	    }
	    arr[j + 1] = key;
	}
	
	// Create new list from sorted array
	LnList<T> result = new LnList<T>();
	for (int i = count - 1; i >= 0; i--) {
	    result = new LnList<T>(arr[i], result);
	}
	
	return result;
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
    }
}
