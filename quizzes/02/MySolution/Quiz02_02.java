//
// HX: 50 points
// Here we revisit a question on quiz01 (Quiz01_03).
// Instead of sorting 10 elements without recursion,
// you are asked to sort up to 1000 elements without
// recursion.
// Hint: Think about building a tree of commands for
// swapping array elements.
//
public class Quiz02_02 {
    public static
	<T extends Comparable<T>>
	void sort1000WithNoRecursion(T[] A) {
	// HX-2025-11-20:
	// A is an array of size at most 1000.
	// Please implement a sorting algorithm
	// WITHOUT recursion that can effectively
	// sort A.
	int n = A.length;
	if (n <= 1) return;

	// Bottom-up iterative merge sort.
	T[] buffer = A.clone();
	T[] src = A;
	T[] dest = buffer;

	int width = 1;
	while (width < n) {
	    int i = 0;
	    while (i < n) {
		int mid = Math.min(i + width, n);
		int end = Math.min(i + (width << 1), n);
		merge(src, dest, i, mid, end);
		i += (width << 1);
	    }
	    // Swap roles of src/dest for next pass.
	    T[] tmp = src;
	    src = dest;
	    dest = tmp;
	    width <<= 1;
	}

	// If the final sorted data is in the buffer, copy it back.
	if (src != A) {
	    System.arraycopy(src, 0, A, 0, n);
	}
    }

    private static
	<T extends Comparable<T>>
	void merge(T[] src, T[] dest, int start, int mid, int end) {
	int i = start;
	int j = mid;
	int k = start;
	while (k < end) {
	    if (i < mid && (j >= end || src[i].compareTo(src[j]) <= 0)) {
		dest[k] = src[i];
		i += 1;
	    } else {
		dest[k] = src[j];
		j += 1;
	    }
	    k += 1;
	}
    }
    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for sort1000WithNoRecursion
	Integer[] nums = {5, 1, 8, 3, 2, 7, 4, 6};
	sort1000WithNoRecursion(nums);
	System.out.println("Sorted integers:");
	for (int v : nums) System.out.print(v + " ");
	System.out.println();

	String[] words = {"banana", "apple", "cherry", "date", "fig", "elderberry"};
	sort1000WithNoRecursion(words);
	System.out.println("Sorted strings:");
	for (String w : words) System.out.print(w + " ");
	System.out.println();
	return /*void*/;
    }
}
