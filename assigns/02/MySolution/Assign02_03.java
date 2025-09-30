public class Assign02_03 {
    public static boolean solve_3sum(Integer[] A) {
        // for each possible k, use two pointers to find i and j such that A[i] + A[j] = A[k]
        // since array is sorted, we can use two-pointer technique for each k
        // time complexity: O(n^2) - for each k (n choices), we do O(n) two-pointer search
        
        for (int k = 0; k < A.length; k++) {
            int target = A[k];
            int i = 0, j = A.length - 1;
            
            // two-pointer approach to find i and j s.t. A[i] + A[j] = target
            while (i < j) {
                int sum = A[i] + A[j];
                
                if (sum == target) {
                    // found a solution: A[i] + A[j] = A[k]
                    // making sure i, j, k are all different indices
                    if (i != k && j != k) {
                        return true;
                    }
                    // if one of i or j equals k, continue searching
                    if (i == k) i++;
                    if (j == k) j--;
                } else if (sum < target) {
                    i++; // need larger sum
                } else {
                    j--; // need smaller sum
                }
            }
        }
        
        return false; // no solution found
    }
    public static void main(String[] argv) {
        // array with solution
        Integer[] test1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Test 1 - Array: " + java.util.Arrays.toString(test1));
        System.out.println("Has 3-sum solution: " + solve_3sum(test1));
        System.out.println("Expected: true (e.g., 1+2=3, 1+3=4, etc.)");
        
        // array w/o solution
        Integer[] test2 = {1, 2, 4, 8, 16, 32};
        System.out.println("\nTest 2 - Array: " + java.util.Arrays.toString(test2));
        System.out.println("Has 3-sum solution: " + solve_3sum(test2));
        System.out.println("Expected: false (no two numbers sum to another)");
        
        // array with duplicates
        Integer[] test3 = {1, 1, 2, 3, 4, 5};
        System.out.println("\nTest 3 - Array: " + java.util.Arrays.toString(test3));
        System.out.println("Has 3-sum solution: " + solve_3sum(test3));
        System.out.println("Expected: true (1+1=2)");
        
        // small array
        Integer[] test4 = {1, 2, 3};
        System.out.println("\nTest 4 - Array: " + java.util.Arrays.toString(test4));
        System.out.println("Has 3-sum solution: " + solve_3sum(test4));
        System.out.println("Expected: true (1+2=3)");
        
        // array with negative numbers
        Integer[] test5 = {-5, -3, -1, 0, 1, 3, 5};
        System.out.println("\nTest 5 - Array: " + java.util.Arrays.toString(test5));
        System.out.println("Has 3-sum solution: " + solve_3sum(test5));
        System.out.println("Expected: true (e.g., -3+3=0, -1+1=0)");
        
        //single element array
        Integer[] test6 = {5};
        System.out.println("\nTest 6 - Array: " + java.util.Arrays.toString(test6));
        System.out.println("Has 3-sum solution: " + solve_3sum(test6));
        System.out.println("Expected: false (need at least 3 elements)");
        
        //two element array
        Integer[] test7 = {1, 2};
        System.out.println("\nTest 7 - Array: " + java.util.Arrays.toString(test7));
        System.out.println("Has 3-sum solution: " + solve_3sum(test7));
        System.out.println("Expected: false (need at least 3 elements)");
        
        System.out.println("\nAlgorithm Analysis:");
        System.out.println("Time Complexity: O(n^2)");
        System.out.println("- Outer loop: n iterations (for each possible k)");
        System.out.println("- Inner two-pointer search: O(n) for each k");
        System.out.println("- Total: O(n) * O(n) = O(n^2)");
        System.out.println("Space Complexity: O(1) - only using constant extra space");
    }
}
