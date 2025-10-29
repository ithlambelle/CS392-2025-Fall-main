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
	
	// ref<t> is a simple mutable wrapper needed for sorting without arrays/lists/loops
	// this allows swapping values using only functions and if-then-else statements
	final class Ref<U> { U v; Ref(U v){ this.v = v; } }
	Ref<T> r0=new Ref<>(x0), r1=new Ref<>(x1), r2=new Ref<>(x2), r3=new Ref<>(x3), r4=new Ref<>(x4),
	       r5=new Ref<>(x5), r6=new Ref<>(x6), r7=new Ref<>(x7), r8=new Ref<>(x8), r9=new Ref<>(x9);

	java.util.function.BiConsumer<Ref<T>,Ref<T>> cs = (a,b) -> {
	    if (a.v.compareTo(b.v) > 0) { T t=a.v; a.v=b.v; b.v=t; }
	};

	// 9 bubble passes, fully unrolled (no arrays/lists/loops)
	bubblePass1(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass2(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass3(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass4(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass5(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass6(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass7(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass8(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);
	bubblePass9(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, cs);

	@SuppressWarnings("unchecked")
	T[] out = (T[]) java.lang.reflect.Array.newInstance(r0.v.getClass(), 10);
	out[0] = r0.v; out[1] = r1.v; out[2] = r2.v; out[3] = r3.v; out[4] = r4.v;
	out[5] = r5.v; out[6] = r6.v; out[7] = r7.v; out[8] = r8.v; out[9] = r9.v;
	return out;
    }
    
    // Private methods for each bubble pass
    private static <T> void bubblePass1(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass2(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass3(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass4(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass5(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass6(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass7(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass8(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
    }
    
    private static <T> void bubblePass9(Ref<T> r0, Ref<T> r1, Ref<T> r2, Ref<T> r3, Ref<T> r4, Ref<T> r5, Ref<T> r6, Ref<T> r7, Ref<T> r8, Ref<T> r9, java.util.function.BiConsumer<Ref<T>,Ref<T>> cs) {
	cs.accept(r0,r1); cs.accept(r1,r2); cs.accept(r2,r3); cs.accept(r3,r4); cs.accept(r4,r5); cs.accept(r5,r6); cs.accept(r6,r7); cs.accept(r7,r8); cs.accept(r8,r9);
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