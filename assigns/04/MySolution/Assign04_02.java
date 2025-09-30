/*
  HX-2025-09-30: 15 points
*/
public class Assign04_02 {
    //
    // HX-2025-09-30:
    // implement a method that uses iforitm to find the index
    // of the first occurrence of a given element in a functional list
    //
    public static <T> int findFirstIndex(FnList<T> xs, T target) {
        int[] result = {-1}; // use array to make it effectively final
        xs.iforitm((i, x) -> {
            if (result[0] == -1 && x.equals(target)) {
                result[0] = i;
            }
        });
        return result[0];
    }

    public static void main(String[] argv) {
        // test with empty list
        FnList<String> empty = new FnList<>();
        System.out.println("empty list index: " + findFirstIndex(empty, "test"));
        
        // test with some strings
        FnList<String> strs = new FnList<>("hello", new FnList<>("world", new FnList<>("test", new FnList<>("java", new FnList<>()))));
        System.out.println("strs list index of 'test': " + findFirstIndex(strs, "test"));
        System.out.println("strs list index of 'hello': " + findFirstIndex(strs, "hello"));
        System.out.println("strs list index of 'notfound': " + findFirstIndex(strs, "notfound"));
        
        // test with integers
        FnList<Integer> nums = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>(2, new FnList<>(4, new FnList<>())))));
        System.out.println("nums list index of 2: " + findFirstIndex(nums, 2));
        System.out.println("nums list index of 4: " + findFirstIndex(nums, 4));
        System.out.println("nums list index of 5: " + findFirstIndex(nums, 5));
    }
}
