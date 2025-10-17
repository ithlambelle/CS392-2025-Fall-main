import Library.FnList.*;
import Library.MyRefer.*;
import java.util.function.Consumer;
//
// HX: 30 points
// This one tests your understanding of higher-order
// methods. Trying to construct a consumer of consumers
// (of the type Consumer<Consumer<Character>>) can help
// you understand the meaning of this one.
//
public class Quiz01_02 {
    public static
	FnList<Character>
	thirdOrderFun
	(Consumer<Consumer<Character>> ffcs) {
	// HX: Given a consumer of consumers of characters,
	// thirdOrderFun returns a string cs.
	// Given fcs = (ch) -> System.out.print(ch),
	// which is of the type Consumer<Character>,
	// ffcs.accept(fcs) and cs.foritm(fcs) should behave
	// the same.
	
	// We need to create a FnList<Character> such that when we call
	// cs.foritm(fcs), it behaves the same as ffcs.accept(fcs)
	// This means we need to capture what ffcs does when given fcs
	
	// We'll use a mutable reference to collect the characters
	// that ffcs would process when given a consumer
	MyRefer<FnList<Character>> result = new MyRefer<>(FnListSUtil.nil());
	
	// Create a consumer that captures characters into our result list
	Consumer<Character> captureConsumer = (Character ch) -> {
	    result.set$raw(FnListSUtil.cons(ch, result.get$raw()));
	};
	
	// Apply ffcs with our capture consumer
	ffcs.accept(captureConsumer);
	
	// Return the reversed list since we built it backwards
	return FnListSUtil.reverse(result.get$raw());
    }
    public static void main (String[] args) {
	// HX-2025-10-12:
	// Please write minimal testing code for thirdOrderFun.
	
	// Test case 1: ffcs that prints characters
	System.out.println("Test 1: ffcs that prints characters");
	Consumer<Consumer<Character>> ffcs1 = (Consumer<Character> fcs) -> {
	    fcs.accept('H');
	    fcs.accept('e');
	    fcs.accept('l');
	    fcs.accept('l');
	    fcs.accept('o');
	};
	
	FnList<Character> result1 = thirdOrderFun(ffcs1);
	System.out.print("Result: ");
	result1.System$out$print();
	System.out.println();
	
	// Test case 2: ffcs that processes characters in reverse
	System.out.println("\nTest 2: ffcs that processes characters in reverse");
	Consumer<Consumer<Character>> ffcs2 = (Consumer<Character> fcs) -> {
	    fcs.accept('W');
	    fcs.accept('o');
	    fcs.accept('r');
	    fcs.accept('l');
	    fcs.accept('d');
	};
	
	FnList<Character> result2 = thirdOrderFun(ffcs2);
	System.out.print("Result: ");
	result2.System$out$print();
	System.out.println();
	
	// Test case 3: ffcs that processes only even-indexed characters
	System.out.println("\nTest 3: ffcs that processes only some characters");
	Consumer<Consumer<Character>> ffcs3 = (Consumer<Character> fcs) -> {
	    fcs.accept('A');
	    fcs.accept('B');
	    fcs.accept('C');
	};
	
	FnList<Character> result3 = thirdOrderFun(ffcs3);
	System.out.print("Result: ");
	result3.System$out$print();
	System.out.println();
	
	// Verify that ffcs.accept(fcs) and cs.foritm(fcs) behave the same
	System.out.println("\nVerification: ffcs.accept(fcs) vs cs.foritm(fcs)");
	Consumer<Character> printConsumer = (Character ch) -> System.out.print(ch);
	
	System.out.print("ffcs.accept(fcs): ");
	ffcs1.accept(printConsumer);
	System.out.println();
	
	System.out.print("cs.foritm(fcs): ");
	result1.foritm(printConsumer);
	System.out.println();
	
	// Test with empty consumer
	System.out.println("\nTesting with empty consumer:");
	Consumer<Consumer<Character>> emptyFfcs = (Consumer<Character> fcs) -> {
	    // Do nothing - empty consumer
	};
	FnList<Character> emptyResult = thirdOrderFun(emptyFfcs);
	System.out.print("Empty consumer result: ");
	emptyResult.System$out$print();
	System.out.println();
	
	return /*void*/;
    }
}
