import java.util.Arrays;

public class Assign02_02 {
    /*
      HX-2025-02-13: 10 points
      Recursion is a fundamental concept in programming.
      However, the support for recursion in Java is very limited.
      Nontheless, we will be making extensive use of recursion in
      this class.
     */

    /*
    // This is a so-called iterative implementation:
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            final int mid = lo + (hi - lo) / 2;
	    final int sign = key.compareTo(a[mid]);
            if      (sign < 0) hi = mid - 1;
            else if (sign > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    */
    public static <T extends Comparable<T> > int indexOf(T[] a, T key) {
        // call a recursive helper method with the full array range
        return indexOfRecursive(a, key, 0, a.length - 1);
    }
    
    // recursive helper method that searches in the range [lo, hi]
    private static <T extends Comparable<T> > int indexOfRecursive(T[] a, T key, int lo, int hi) {
        // base case: if lo > hi, the key is not present
        if (lo > hi) {
            return -1;
        }
        
        // find the middle index
        int mid = lo + (hi - lo) / 2;
        int sign = key.compareTo(a[mid]);
        
        // if key is found at "mid", return mid
        if (sign == 0) {
            return mid;
        }
        // if the key is smaller than a[mid], search the left half
        else if (sign < 0) {
            return indexOfRecursive(a, key, lo, mid - 1);
        }
        // if key is larger than a[mid], search the right half
        else {
            return indexOfRecursive(a, key, mid + 1, hi);
        }
    }

    public static void main(String[] argv) {
        // test with Integer array
        Integer[] testArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        System.out.println("Test array: " + Arrays.toString(testArray));
        
        // below are test cases
        int[] testKeys = {1, 5, 9, 15, 19, 0, 2, 10, 20};
        String[] expectedResults = {"found", "found", "found", "found", "found", "not found", "not found", "not found", "not found"};
        
        System.out.println("\nTesting recursive binary search:");
        for (int i = 0; i < testKeys.length; i++) {
            int result = indexOf(testArray, testKeys[i]);
            String status = (result != -1) ? "found at index " + result : "not found";
            System.out.println("Searching for " + testKeys[i] + ": " + status + " (expected: " + expectedResults[i] + ")");
        }
        
        // string array test 
        String[] stringArray = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("\nString array: " + Arrays.toString(stringArray));
        System.out.println("Searching for 'cherry': " + (indexOf(stringArray, "cherry") != -1 ? "found" : "not found"));
        System.out.println("Searching for 'grape': " + (indexOf(stringArray, "grape") != -1 ? "found" : "not found"));
        
        // edge case test 
        Integer[] emptyArray = {};
        System.out.println("\nEmpty array test: " + (indexOf(emptyArray, 5) == -1 ? "correct" : "incorrect"));
        
        Integer[] singleElement = {42};
        System.out.println("Single element array - searching for 42: " + (indexOf(singleElement, 42) == 0 ? "correct" : "incorrect"));
        System.out.println("Single element array - searching for 43: " + (indexOf(singleElement, 43) == -1 ? "correct" : "incorrect"));
    }
}
