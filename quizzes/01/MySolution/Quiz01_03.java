//
// HX: 30 points
// This one may seem easy but can be time-consuming
// if you use a brute-force approach.
//
public class Quiz01_03 {
    public static
	<T extends Comparable<T>>
	T[] sort10WithNoRecursion
	(T x0, T x1, T x2, T x3, T x4, T x5, T x6, T x7, T x8, T x9) {
	// HX-2025-10-12:
	// Given 10 arguments,
	// please return an array of size 10 containing the
	// 10 arguments sorted according to the order implemented by
	// compareTo on T.
	// HX: No arrays, lists, etc.
	// HX: No recursion is allowed for this one
	// HX: No loops (either while-loop or for-loop) is allowed.
	// HX: Yes, you can use functions (but not recursive functions)
	// HX: Please do not try to write a HUGH if-then-else mumble jumble!
	
	// We'll use a simple approach: find each position by comparing all elements
	// and excluding the ones we've already placed
	
	// Position 0: Find the minimum element
	T pos0 = min(x0, min(x1, min(x2, min(x3, min(x4, min(x5, min(x6, min(x7, min(x8, x9)))))))));
	
	// Position 1: Find the second minimum element
	T pos1 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 1),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 2),
			 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 3),
			     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 4),
				 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 5),
				     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 6),
					 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
					     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
						 select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9)))))))));
	
	// Position 2: Find the third minimum element
	T pos2 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 2),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 3),
			 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 4),
			     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 5),
				 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 6),
				     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
					 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
					     select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9))))))));
	
	// Position 3: Find the fourth minimum element
	T pos3 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 3),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 4),
			 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 5),
			     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 6),
				 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
				     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
					 select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9)))))));
	
	// Position 4: Find the fifth minimum element
	T pos4 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 4),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 5),
			 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 6),
			     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
				 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
				     select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9))))));
	
	// Position 5: Find the sixth minimum element
	T pos5 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 5),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 6),
			 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
			     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
				 select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9)))));
	
	// Position 6: Find the seventh minimum element
	T pos6 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 6),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
			 min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
			     select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9))));
	
	// Position 7: Find the eighth minimum element
	T pos7 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 7),
		     min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
			 select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9)));
	
	// Position 8: Find the ninth minimum element
	T pos8 = min(select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 8),
		     select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9));
	
	// Position 9: Find the tenth minimum element (maximum)
	T pos9 = select(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, 9);
	
	// Create and return the sorted array
	@SuppressWarnings("unchecked")
	T[] result = (T[]) java.lang.reflect.Array.newInstance(pos0.getClass(), 10);
	result[0] = pos0; result[1] = pos1; result[2] = pos2; result[3] = pos3; result[4] = pos4;
	result[5] = pos5; result[6] = pos6; result[7] = pos7; result[8] = pos8; result[9] = pos9;
	return result;
    }
    
    // Helper function to select the nth element (0-indexed)
    private static <T extends Comparable<T>>
    T select(T x0, T x1, T x2, T x3, T x4, T x5, T x6, T x7, T x8, T x9, int n) {
	switch (n) {
	case 0: return x0;
	case 1: return x1;
	case 2: return x2;
	case 3: return x3;
	case 4: return x4;
	case 5: return x5;
	case 6: return x6;
	case 7: return x7;
	case 8: return x8;
	case 9: return x9;
	default: return x0;
	}
    }
    
    // Helper functions for min and max
    private static <T extends Comparable<T>>
    T min(T a, T b) {
	return (a.compareTo(b) <= 0) ? a : b;
    }
    
    private static <T extends Comparable<T>>
    T max(T a, T b) {
	return (a.compareTo(b) >= 0) ? a : b;
    }
    
    public static void main(String[] args) {
	// Test with integers
	System.out.println("Testing sort10WithNoRecursion with integers:");
	Integer[] result = sort10WithNoRecursion(5, 2, 8, 1, 9, 3, 7, 4, 6, 0);
	System.out.print("Input: 5, 2, 8, 1, 9, 3, 7, 4, 6, 0\n");
	System.out.print("Output: ");
	for (int i = 0; i < result.length; i++) {
	    System.out.print(result[i]);
	    if (i < result.length - 1) System.out.print(", ");
	}
	System.out.println();
	
	// Test with strings
	System.out.println("\nTesting sort10WithNoRecursion with strings:");
	String[] result2 = sort10WithNoRecursion("zebra", "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "kiwi");
	System.out.print("Input: zebra, apple, banana, cherry, date, elderberry, fig, grape, honeydew, kiwi\n");
	System.out.print("Output: ");
	for (int i = 0; i < result2.length; i++) {
	    System.out.print(result2[i]);
	    if (i < result2.length - 1) System.out.print(", ");
	}
	System.out.println();
    }
}