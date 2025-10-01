/*
  HX-2025-09-30: 25 points
*/
public class Assign04_04 {
    //
    // HX-2025-09-30:
    // implement a method that uses iforitm to find all elements
    // in a functional list that are greater than their index position
    //
    public static FnList<Integer> elementsGreaterThanIndex(FnList<Integer> xs) {
        FnList<Integer>[] result = new FnList[1];
        result[0] = new FnList<>();
        xs.iforitm((i, x) -> {
            if (x > i) {
                result[0] = new FnList<>(x, result[0]);
            }
        });
        return result[0].reverse(); // reverse to get original order
    }

    public static void main(String[] argv) {
        // test with empty list
        FnList<Integer> empty = new FnList<>();
        System.out.print("empty list result: ");
        elementsGreaterThanIndex(empty).System$out$print();
        System.out.println();
        
        // test with some numbers
        FnList<Integer> nums = new FnList<>(1, new FnList<>(3, new FnList<>(2, new FnList<>(5, new FnList<>(4, new FnList<>())))));
        System.out.print("nums list result: ");
        elementsGreaterThanIndex(nums).System$out$print();
        System.out.println();
        
        // test with all elements greater than index
        FnList<Integer> allGreater = new FnList<>(10, new FnList<>(20, new FnList<>(30, new FnList<>())));
        System.out.print("allGreater list result: ");
        elementsGreaterThanIndex(allGreater).System$out$print();
        System.out.println();
        
        // test with no elements greater than index
        FnList<Integer> noneGreater = new FnList<>(0, new FnList<>(1, new FnList<>(2, new FnList<>())));
        System.out.print("noneGreater list result: ");
        elementsGreaterThanIndex(noneGreater).System$out$print();
        System.out.println();
    }
}
