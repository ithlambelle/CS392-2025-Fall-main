/*
  HX-2025-09-15: 10 points
*/
public class Assign03_01 {
    //
    // HX-2025-09-15:
    // This implementation of f91
    // is not tail-recursive. Please
    // translate it into a version that
    // is tail-recursive
    //
    /*
    static int f91(int n) {
	if (n > 100)
	    return n-10;
	else
	    return f91(f91(n+11);
    }
    */

    // tail-recursive version of f91 using continuation passing style
    static int f91(int n) {
        return f91Tail(n, x -> x);
    }
    
    // tail-recursive implementation with continuation
    static int f91Tail(int n, java.util.function.Function<Integer, Integer> cont) {
        if (n > 100) {
            return cont.apply(n - 10);
        } else {
            // f91(n) = f91(f91(n+11)) when n <= 100
            // we need to call f91(n+11) first, then call f91 on that result
            return f91Tail(n + 11, result -> f91Tail(result, cont));
        }
    }

    public static void main(String[] argv) {
        // test cases for f91
        int[] testValues = {50, 75, 100, 101, 110, 200};
        
        System.out.println("testing mccarthy 91 function:");
        for (int n : testValues) {
            int result = f91(n);
            System.out.println("f91(" + n + ") = " + result);
        }
        
        // verify some known results
        System.out.println("\nverification:");
        System.out.println("f91(50) should be 91, got: " + f91(50));
        System.out.println("f91(100) should be 91, got: " + f91(100));
        System.out.println("f91(101) should be 91, got: " + f91(101));
    }
}
