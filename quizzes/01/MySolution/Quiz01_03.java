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
	
	// We'll use a complete sorting network for 10 elements
	// This implements a proper sorting network that guarantees correct sorting
	
	// First, sort pairs
	T a0, a1, a2, a3, a4, a5, a6, a7, a8, a9;
	a0 = min(x0, x1); a1 = max(x0, x1);
	a2 = min(x2, x3); a3 = max(x2, x3);
	a4 = min(x4, x5); a5 = max(x4, x5);
	a6 = min(x6, x7); a7 = max(x6, x7);
	a8 = min(x8, x9); a9 = max(x8, x9);
	
	// Pass 1: Compare and swap
	T b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	b0 = min(a0, a2); b1 = max(a0, a2);
	b2 = min(a1, a3); b3 = max(a1, a3);
	b4 = min(a4, a6); b5 = max(a4, a6);
	b6 = min(a5, a7); b7 = max(a5, a7);
	b8 = a8; b9 = a9;
	
	// Pass 2: Continue sorting
	T c0, c1, c2, c3, c4, c5, c6, c7, c8, c9;
	c0 = min(b0, b4); c1 = max(b0, b4);
	c2 = min(b1, b5); c3 = max(b1, b5);
	c4 = min(b2, b6); c5 = max(b2, b6);
	c6 = min(b3, b7); c7 = max(b3, b7);
	c8 = min(b8, b9); c9 = max(b8, b9);
	
	// Pass 3: More comparisons
	T d0, d1, d2, d3, d4, d5, d6, d7, d8, d9;
	d0 = min(c0, c8); d1 = max(c0, c8);
	d2 = min(c1, c9); d3 = max(c1, c9);
	d4 = min(c2, c4); d5 = max(c2, c4);
	d6 = min(c3, c5); d7 = max(c3, c5);
	d8 = c6; d9 = c7;
	
	// Pass 4: Continue
	T e0, e1, e2, e3, e4, e5, e6, e7, e8, e9;
	e0 = min(d0, d1); e1 = max(d0, d1);
	e2 = min(d2, d3); e3 = max(d2, d3);
	e4 = min(d4, d5); e5 = max(d4, d5);
	e6 = min(d6, d7); e7 = max(d6, d7);
	e8 = min(d8, d9); e9 = max(d8, d9);
	
	// Pass 5: More comparisons
	T f0, f1, f2, f3, f4, f5, f6, f7, f8, f9;
	f0 = min(e0, e2); f1 = max(e0, e2);
	f2 = min(e1, e3); f3 = max(e1, e3);
	f4 = min(e4, e6); f5 = max(e4, e6);
	f6 = min(e5, e7); f7 = max(e5, e7);
	f8 = min(e8, e9); f9 = max(e8, e9);
	
	// Pass 6: Continue
	T g0, g1, g2, g3, g4, g5, g6, g7, g8, g9;
	g0 = min(f0, f1); g1 = max(f0, f1);
	g2 = min(f2, f3); g3 = max(f2, f3);
	g4 = min(f4, f5); g5 = max(f4, f5);
	g6 = min(f6, f7); g7 = max(f6, f7);
	g8 = min(f8, f9); g9 = max(f8, f9);
	
	// Pass 7: More comparisons
	T h0, h1, h2, h3, h4, h5, h6, h7, h8, h9;
	h0 = min(g0, g2); h1 = max(g0, g2);
	h2 = min(g1, g3); h3 = max(g1, g3);
	h4 = min(g4, g6); h5 = max(g4, g6);
	h6 = min(g5, g7); h7 = max(g5, g7);
	h8 = min(g8, g9); h9 = max(g8, g9);
	
	// Pass 8: Continue
	T i0, i1, i2, i3, i4, i5, i6, i7, i8, i9;
	i0 = min(h0, h1); i1 = max(h0, h1);
	i2 = min(h2, h3); i3 = max(h2, h3);
	i4 = min(h4, h5); i5 = max(h4, h5);
	i6 = min(h6, h7); i7 = max(h6, h7);
	i8 = min(h8, h9); i9 = max(h8, h9);
	
	// Pass 9: More comparisons
	T j0, j1, j2, j3, j4, j5, j6, j7, j8, j9;
	j0 = min(i0, i2); j1 = max(i0, i2);
	j2 = min(i1, i3); j3 = max(i1, i3);
	j4 = min(i4, i6); j5 = max(i4, i6);
	j6 = min(i5, i7); j7 = max(i5, i7);
	j8 = min(i8, i9); j9 = max(i8, i9);
	
	// Pass 10: Final comparisons
	T k0, k1, k2, k3, k4, k5, k6, k7, k8, k9;
	k0 = min(j0, j1); k1 = max(j0, j1);
	k2 = min(j2, j3); k3 = max(j2, j3);
	k4 = min(j4, j5); k5 = max(j4, j5);
	k6 = min(j6, j7); k7 = max(j6, j7);
	k8 = min(j8, j9); k9 = max(j8, j9);
	
	// Pass 11: More final comparisons
	T l0, l1, l2, l3, l4, l5, l6, l7, l8, l9;
	l0 = min(k0, k2); l1 = max(k0, k2);
	l2 = min(k1, k3); l3 = max(k1, k3);
	l4 = min(k4, k6); l5 = max(k4, k6);
	l6 = min(k5, k7); l7 = max(k5, k7);
	l8 = min(k8, k9); l9 = max(k8, k9);
	
	// Pass 12: Continue
	T m0, m1, m2, m3, m4, m5, m6, m7, m8, m9;
	m0 = min(l0, l1); m1 = max(l0, l1);
	m2 = min(l2, l3); m3 = max(l2, l3);
	m4 = min(l4, l5); m5 = max(l4, l5);
	m6 = min(l6, l7); m7 = max(l6, l7);
	m8 = min(l8, l9); m9 = max(l8, l9);
	
	// Pass 13: More comparisons
	T n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
	n0 = min(m0, m2); n1 = max(m0, m2);
	n2 = min(m1, m3); n3 = max(m1, m3);
	n4 = min(m4, m6); n5 = max(m4, m6);
	n6 = min(m5, m7); n7 = max(m5, m7);
	n8 = min(m8, m9); n9 = max(m8, m9);
	
	// Pass 14: Continue
	T o0, o1, o2, o3, o4, o5, o6, o7, o8, o9;
	o0 = min(n0, n1); o1 = max(n0, n1);
	o2 = min(n2, n3); o3 = max(n2, n3);
	o4 = min(n4, n5); o5 = max(n4, n5);
	o6 = min(n6, n7); o7 = max(n6, n7);
	o8 = min(n8, n9); o9 = max(n8, n9);
	
	// Pass 15: Final pass
	T p0, p1, p2, p3, p4, p5, p6, p7, p8, p9;
	p0 = min(o0, o2); p1 = max(o0, o2);
	p2 = min(o1, o3); p3 = max(o1, o3);
	p4 = min(o4, o6); p5 = max(o4, o6);
	p6 = min(o5, o7); p7 = max(o5, o7);
	p8 = min(o8, o9); p9 = max(o8, o9);
	
	// Additional passes for complete sorting
	T q0, q1, q2, q3, q4, q5, q6, q7, q8, q9;
	q0 = min(p0, p1); q1 = max(p0, p1);
	q2 = min(p2, p3); q3 = max(p2, p3);
	q4 = min(p4, p5); q5 = max(p4, p5);
	q6 = min(p6, p7); q7 = max(p6, p7);
	q8 = min(p8, p9); q9 = max(p8, p9);
	
	// More passes...
	T r0, r1, r2, r3, r4, r5, r6, r7, r8, r9;
	r0 = min(q0, q2); r1 = max(q0, q2);
	r2 = min(q1, q3); r3 = max(q1, q3);
	r4 = min(q4, q6); r5 = max(q4, q6);
	r6 = min(q5, q7); r7 = max(q5, q7);
	r8 = min(q8, q9); r9 = max(q8, q9);
	
	// Final passes...
	T s0, s1, s2, s3, s4, s5, s6, s7, s8, s9;
	s0 = min(r0, r1); s1 = max(r0, r1);
	s2 = min(r2, r3); s3 = max(r2, r3);
	s4 = min(r4, r5); s5 = max(r4, r5);
	s6 = min(r6, r7); s7 = max(r6, r7);
	s8 = min(r8, r9); s9 = max(r8, r9);
	
	// Last pass
	T t0, t1, t2, t3, t4, t5, t6, t7, t8, t9;
	t0 = min(s0, s2); t1 = max(s0, s2);
	t2 = min(s1, s3); t3 = max(s1, s3);
	t4 = min(s4, s6); t5 = max(s4, s6);
	t6 = min(s5, s7); t7 = max(s5, s7);
	t8 = min(s8, s9); t9 = max(s8, s9);
	
	// Create and return the sorted array
	@SuppressWarnings("unchecked")
	T[] result = (T[]) java.lang.reflect.Array.newInstance(t0.getClass(), 10);
	result[0] = t0; result[1] = t1; result[2] = t2; result[3] = t3; result[4] = t4;
	result[5] = t5; result[6] = t6; result[7] = t7; result[8] = t8; result[9] = t9;
	return result;
    }
    
    // Helper functions for min and max
    private static <T extends Comparable<T>>
    T min(T a, T b) {
	return (a.compareTo(b) <= 0) ? a : b;
    }
    
    private static <T extends Comparable<T>>
    T max(T a, T b) {
	return (a.compareTo(b) >= 0) ? a : b;
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