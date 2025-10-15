import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;

public class FnListTest {
    
    public static void main(String[] args) {
        System.out.println("=== FnList Test Suite ===");
        
        // Test basic operations
        testBasicOperations();
        
        // Test utility functions
        testUtilityFunctions();
        
        // Test higher-order functions
        testHigherOrderFunctions();
        
        // Test edge cases
        testEdgeCases();
        
        System.out.println("\n=== All tests completed ===");
    }
    
    private static void testBasicOperations() {
        System.out.println("\n--- Testing Basic Operations ---");
        
        // Test empty list
        FnList<Integer> empty = new FnList<>();
        System.out.println("Empty list nilq: " + empty.nilq());
        System.out.println("Empty list consq: " + empty.consq());
        
        // Test single element
        FnList<Integer> single = new FnList<>(42, new FnList<>());
        System.out.println("Single element nilq: " + single.nilq());
        System.out.println("Single element hd: " + single.hd());
        
        // Test multiple elements
        FnList<Integer> nums = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>())));
        System.out.print("Multiple elements: ");
        nums.System$out$print();
        System.out.println();
        System.out.println("Length: " + nums.length());
    }
    
    private static void testUtilityFunctions() {
        System.out.println("\n--- Testing Utility Functions ---");
        
        FnList<Integer> nums = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>(4, new FnList<>(5, new FnList<>())))));
        
        // Test reverse
        FnList<Integer> reversed = nums.reverse();
        System.out.print("Original: ");
        nums.System$out$print();
        System.out.print(" Reversed: ");
        reversed.System$out$print();
        System.out.println();
        
        // Test take
        FnList<Integer> first3 = FnListUtil.take(nums, 3);
        System.out.print("First 3: ");
        first3.System$out$print();
        System.out.println();
        
        // Test drop
        FnList<Integer> after2 = FnListUtil.drop(nums, 2);
        System.out.print("After dropping 2: ");
        after2.System$out$print();
        System.out.println();
        
        // Test nth
        try {
            System.out.println("Element at index 2: " + FnListUtil.nth(nums, 2));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: " + e.getMessage());
        }
        
        // Test filter
        FnList<Integer> evens = FnListUtil.filter(nums, x -> x % 2 == 0);
        System.out.print("Even numbers: ");
        evens.System$out$print();
        System.out.println();
        
        // Test replicate
        FnList<String> repeated = FnListUtil.replicate(3, "hello");
        System.out.print("Replicated 'hello' 3 times: ");
        repeated.System$out$print();
        System.out.println();
    }
    
    private static void testHigherOrderFunctions() {
        System.out.println("\n--- Testing Higher-Order Functions ---");
        
        FnList<Integer> nums = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>(4, new FnList<>(5, new FnList<>())))));
        
        // Test map
        FnList<Integer> doubled = FnListUtil.map_list(nums, x -> x * 2);
        System.out.print("Original: ");
        nums.System$out$print();
        System.out.print(" Doubled: ");
        doubled.System$out$print();
        System.out.println();
        
        // Test imap (with index)
        FnList<String> indexed = FnListUtil.imap_list(nums, (i, x) -> "[" + i + "]=" + x);
        System.out.print("Indexed: ");
        indexed.System$out$print();
        System.out.println();
        
        // Test fold
        Integer sum = FnListUtil.folditm(nums, 0, (acc, x) -> acc + x);
        System.out.println("Sum: " + sum);
        
        // Test forall
        boolean allPositive = FnListUtil.forall(nums, x -> x > 0);
        System.out.println("All positive: " + allPositive);
        
        // Test zipWith
        FnList<Integer> other = new FnList<>(10, new FnList<>(20, new FnList<>(30, new FnList<>())));
        FnList<Integer> zipped = FnListUtil.zipWith(nums, other, (x, y) -> x + y);
        System.out.print("Zipped sum: ");
        zipped.System$out$print();
        System.out.println();
    }
    
    private static void testEdgeCases() {
        System.out.println("\n--- Testing Edge Cases ---");
        
        // Test empty list operations
        FnList<Integer> empty = new FnList<>();
        System.out.println("Empty list length: " + empty.length());
        
        FnList<Integer> emptyReversed = empty.reverse();
        System.out.println("Empty list reversed is empty: " + emptyReversed.nilq());
        
        FnList<Integer> emptyMapped = FnListUtil.map_list(empty, x -> x * 2);
        System.out.println("Empty list mapped is empty: " + emptyMapped.nilq());
        
        // Test single element
        FnList<Integer> single = new FnList<>(42, new FnList<>());
        FnList<Integer> singleReversed = single.reverse();
        System.out.println("Single element reversed equals original: " + 
            (singleReversed.hd().equals(single.hd())));
        
        // Test large list
        FnList<Integer> large = FnListUtil.list_make_int1(1000);
        System.out.println("Large list (1000 elements) length: " + large.length());
        
        // Test take more than available
        FnList<Integer> small = new FnList<>(1, new FnList<>(2, new FnList<>()));
        FnList<Integer> taken = FnListUtil.take(small, 10);
        System.out.print("Taking 10 from list of 2: ");
        taken.System$out$print();
        System.out.println();
    }
}
