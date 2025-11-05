import Library.LnStrm.*;
import Library.FnTuple.*;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;

public class Assign06_03 {
    public static <T> void arrayQuickSort(T[] A, ToIntBiFunction<T,T> cmp) {
	// implement standard array-based quicksort and make sure
	// that equal elements are properly handled. in particular, your
	// testing code should test your implementation on an array of 1m zeros!
	
	if (A == null || A.length <= 1) {
	    return;
	}
	
	quickSortHelper(A, 0, A.length - 1, cmp);
    }
    
    // helper method to perform quicksort on a subarray
    private static <T> void quickSortHelper(T[] A, int left, int right, ToIntBiFunction<T,T> cmp) {
	if (left >= right) {
	    return;
	}
	
	// choose pivot (use median of three for better performance)
	int pivotIdx = medianOfThree(A, left, right, cmp);
	T pivot = A[pivotIdx];
	
	// swap pivot to end
	swap(A, pivotIdx, right);
	
	// three-way partition: less, equal, greater
	int[] partition = threeWayPartition(A, left, right - 1, pivot, cmp);
	int lessEnd = partition[0];
	int equalStart = partition[1];
	
	// swap pivot back to its correct position
	swap(A, equalStart, right);
	
	// recursively sort less and greater parts
	quickSortHelper(A, left, lessEnd, cmp);
	quickSortHelper(A, equalStart + 1, right, cmp);
    }
    
    // three-way partition: returns [lessEnd, equalStart]
    // elements in [left, lessEnd] are < pivot
    // elements in [lessEnd+1, equalStart-1] are = pivot
    // elements in [equalStart, right] are > pivot
    private static <T> int[] threeWayPartition(T[] A, int left, int right, T pivot, ToIntBiFunction<T,T> cmp) {
	int lessEnd = left - 1;
	int equalStart = right + 1;
	int i = left;
	
	while (i < equalStart) {
	    int comparison = cmp.applyAsInt(A[i], pivot);
	    if (comparison < 0) {
		// A[i] < pivot
		lessEnd++;
		swap(A, lessEnd, i);
		i++;
	    } else if (comparison > 0) {
		// A[i] > pivot
		equalStart--;
		swap(A, equalStart, i);
		// don't increment i, need to check the swapped element
	    } else {
		// A[i] == pivot
		i++;
	    }
	}
	
	return new int[]{lessEnd, equalStart};
    }
    
    // helper method to swap two elements
    private static <T> void swap(T[] A, int i, int j) {
	T temp = A[i];
	A[i] = A[j];
	A[j] = temp;
    }
    
    // choose median of three as pivot
    private static <T> int medianOfThree(T[] A, int left, int right, ToIntBiFunction<T,T> cmp) {
	int mid = left + (right - left) / 2;
	
	T a = A[left];
	T b = A[mid];
	T c = A[right];
	
	// find median of three
	if (cmp.applyAsInt(a, b) < 0) {
	    if (cmp.applyAsInt(b, c) < 0) {
		return mid; // a < b < c
	    } else if (cmp.applyAsInt(a, c) < 0) {
		return right; // a < c <= b
	    } else {
		return left; // c <= a < b
	    }
	} else {
	    if (cmp.applyAsInt(a, c) < 0) {
		return left; // b <= a < c
	    } else if (cmp.applyAsInt(b, c) < 0) {
		return right; // b < c <= a
	    } else {
		return mid; // c <= b <= a
	    }
	}
    }
    
    public static void main(String[] args) {
	// test with array of 1m zeros
	System.out.println("testing with array of 1,000,000 zeros:");
	Integer[] zeros = new Integer[1000000];
	for (int i = 0; i < zeros.length; i++) {
	    zeros[i] = 0;
	}
	
	long startTime = System.currentTimeMillis();
	arrayQuickSort(zeros, (a, b) -> a.compareTo(b));
	long endTime = System.currentTimeMillis();
	
	System.out.println("sorted in " + (endTime - startTime) + " ms");
	
	// verify it's sorted
	boolean sorted = true;
	for (int i = 1; i < zeros.length; i++) {
	    if (zeros[i] < zeros[i-1]) {
		sorted = false;
		break;
	    }
	}
	System.out.println("array is sorted: " + sorted);
	
	// test with random integers
	System.out.println("\ntesting with random integers:");
	Integer[] random = new Integer[1000];
	Random rng = new Random();
	for (int i = 0; i < random.length; i++) {
	    random[i] = rng.nextInt(100);
	}
	
	arrayQuickSort(random, (a, b) -> a.compareTo(b));
	
	// verify sorted
	sorted = true;
	for (int i = 1; i < random.length; i++) {
	    if (random[i] < random[i-1]) {
		sorted = false;
		break;
	    }
	}
	System.out.println("random array is sorted: " + sorted);
	
	// test with strings
	System.out.println("\ntesting with strings:");
	String[] strings = {"banana", "apple", "cherry", "date", "apple", "banana"};
	arrayQuickSort(strings, (a, b) -> a.compareTo(b));
	
	System.out.print("sorted strings: ");
	for (String s : strings) {
	    System.out.print(s + " ");
	}
	System.out.println();
	
	return;
    }

} // end of [public class Assign06_03{...}]

