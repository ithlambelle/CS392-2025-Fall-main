/*
  HX-2025-09-30: 15 points
*/
public class Assign04_01 {
    //
    // HX-2025-09-30:
    // implement a method that uses foritm to find the sum
    // of all even numbers in a functional list
    //
    public static int sumEvenNumbers(FnList<Integer> xs) {
        int[] sum = {0}; // use array to make it effectively final
        xs.foritm(x -> {
            if (x % 2 == 0) {
                sum[0] += x;
            }
        });
        return sum[0];
    }

    public static void main(String[] argv) {
        // test with empty list
        FnList<Integer> empty = new FnList<>();
        System.out.println("empty list sum: " + sumEvenNumbers(empty));
        
        // test with some numbers
        FnList<Integer> nums = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>(4, new FnList<>(5, new FnList<>())))));
        System.out.println("nums list sum: " + sumEvenNumbers(nums));
        
        // test with all even numbers
        FnList<Integer> evens = new FnList<>(2, new FnList<>(4, new FnList<>(6, new FnList<>(8, new FnList<>()))));
        System.out.println("evens list sum: " + sumEvenNumbers(evens));
        
        // test with all odd numbers
        FnList<Integer> odds = new FnList<>(1, new FnList<>(3, new FnList<>(5, new FnList<>(7, new FnList<>()))));
        System.out.println("odds list sum: " + sumEvenNumbers(odds));
    }
}
