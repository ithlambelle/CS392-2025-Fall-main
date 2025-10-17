//
// HX: 20 points
//
import Library.FnA1sz.*;
// Please see Library/FnA1sz for FnA1sz.java
public class Quiz01_01 {
    public static
	<T extends Comparable<T>>
	int FnA1szBinarySearch(FnA1sz<T> A, T key) {
	// HX-2025-10-12:
	// Please implement binary search on a sorted functional array (FnA1sz)
	// that returns the largest index i such that key >= A[i] if such i exists,
	// or the method returns -1. The comparison function should be the compareTo
	// method implemented by the class T.
	
	int left = 0;
	int right = A.length() - 1;
	int result = -1;
	
	while (left <= right) {
	    int mid = left + (right - left) / 2;
	    T midValue = A.getAt(mid);
	    
	    if (key.compareTo(midValue) >= 0) {
		// key >= A[mid], so this is a valid candidate
		result = mid;
		left = mid + 1; // search for larger index
	    } else {
		// key < A[mid], search left half
		right = mid - 1;
	    }
	}
	
	return result;
    }
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for FnA1szBinarySearch
	
	// Test with Integer array
	Integer[] intArray = {1, 3, 5, 7, 9, 11, 13, 15};
	FnA1sz<Integer> intFnArray = new FnA1sz<>(intArray);
	
	System.out.println("Testing FnA1szBinarySearch with Integer array:");
	intFnArray.System$out$print();
	System.out.println();
	
	// Test cases
	int[] testKeys = {0, 1, 3, 5, 7, 9, 11, 13, 15, 16};
	for (int key : testKeys) {
	    int result = FnA1szBinarySearch(intFnArray, key);
	    System.out.println("Key " + key + " -> Index " + result);
	}
	
	// Test with String array
	String[] strArray = {"apple", "banana", "cherry", "date", "elderberry"};
	FnA1sz<String> strFnArray = new FnA1sz<>(strArray);
	
	System.out.println("\nTesting FnA1szBinarySearch with String array:");
	strFnArray.System$out$print();
	System.out.println();
	
	String[] testStrKeys = {"a", "apple", "banana", "cherry", "date", "elderberry", "z"};
	for (String key : testStrKeys) {
	    int result = FnA1szBinarySearch(strFnArray, key);
	    System.out.println("Key \"" + key + "\" -> Index " + result);
	}
	
	// Test with empty array
	Integer[] emptyArray = {};
	FnA1sz<Integer> emptyFnArray = new FnA1sz<>(emptyArray);
	System.out.println("\nTesting with empty array:");
	int emptyResult = FnA1szBinarySearch(emptyFnArray, 5);
	System.out.println("Key 5 in empty array -> Index " + emptyResult);
	
	return /*void*/;
    }
}
