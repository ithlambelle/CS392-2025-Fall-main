/*
  HX-2025-09-30: 20 points
*/
public class Assign04_03 {
    //
    // HX-2025-09-30:
    // implement a method that uses rforitm to check if a
    // functional list is a palindrome
    //
    public static <T> boolean isPalindrome(FnList<T> xs) {
        FnList<T>[] forward = new FnList[1];
        FnList<T>[] backward = new FnList[1];
        forward[0] = new FnList<>();
        backward[0] = new FnList<>();
        
        // collect forward order
        xs.foritm(x -> {
            forward[0] = new FnList<>(x, forward[0]);
        });
        
        // collect reverse order using rforitm
        xs.rforitm(x -> {
            backward[0] = new FnList<>(x, backward[0]);
        });
        
        // compare the two lists
        return listsEqual(forward[0], backward[0]);
    }
    
    private static <T> boolean listsEqual(FnList<T> xs, FnList<T> ys) {
        if (xs.nilq() && ys.nilq()) return true;
        if (xs.nilq() || ys.nilq()) return false;
        return xs.hd().equals(ys.hd()) && listsEqual(xs.tl(), ys.tl());
    }

    public static void main(String[] argv) {
        // test with empty list
        FnList<String> empty = new FnList<>();
        System.out.println("empty list palindrome: " + isPalindrome(empty));
        
        // test with single element
        FnList<String> single = new FnList<>("a", new FnList<>());
        System.out.println("single element palindrome: " + isPalindrome(single));
        
        // test with palindrome
        FnList<String> pal1 = new FnList<>("a", new FnList<>("b", new FnList<>("a", new FnList<>())));
        System.out.println("pal1 palindrome: " + isPalindrome(pal1));
        
        // test with non-palindrome
        FnList<String> nonpal = new FnList<>("a", new FnList<>("b", new FnList<>("c", new FnList<>())));
        System.out.println("nonpal palindrome: " + isPalindrome(nonpal));
        
        // test with longer palindrome
        FnList<String> pal2 = new FnList<>("r", new FnList<>("a", new FnList<>("c", new FnList<>("e", new FnList<>("c", new FnList<>("a", new FnList<>("r", new FnList<>())))))));
        System.out.println("pal2 palindrome: " + isPalindrome(pal2));
    }
}
