//
// HX-2025-11-19: 50 points
//
// This question tests your understanding
// of recursion and time analysis involving
// recursion.
// Given a sequence xs, a subsequence of xs
// can be represented as a list of integers
// (representing indices). For instance, given
// xs = "Hello", (0, 2, 4) refers to the subeqence
// "Hlo" (since xs[0] = 'H', xs[2] = 'l', and
// xs[4] = 'o'); (0, 3, 4) also refers to "Hlo".
// The subsequece (0, 2, 4) is to the left of
// the subsequece (0, 3, 4) as (0, 2, 4) is less
// than (0, 3, 4) according to the lexicographic
// ordering.
//
// Here you are asked to implement a function that
// finds the longest leftmost ascending subsequence
// of a given sequence.
// For instance, suppose xs = [1,2,1,2,3,1,2,3,4],
// the longest leftmost ascending subsequence of xs
// is represented by (0, 1, 3, 4, 7, 8) (which refers
// to [1,2,2,3,3,4] in xs).
//
// In order to receive 50 points, your implementation
// should be quadratic time, that is, O(n^2) time and
// you MUST give a brief explanation as to why it is so.
// Otherwise, a working solution receives at most 60%, that
// is, 30 points out of 50 points.
//
import Library.FnList.*;
// Please see Library/FnList for FnList.java
import Library.FnA1sz.*;
// Please see Library/FnA1sz for FnA1sz.java
public class Quiz02_01 {
    public static
	<T extends Comparable<T>>
	FnList<Integer> FnA1szLongestMonoSubsequence(FnA1sz<T> xs) {
	// HX-2025-11-19:
	// This method finds the leftmost longest ascending subsequence
	// of xs. Note that the returned list consists of the indices of
	// the elements of the subsequence.
	int n = xs.length();
	if (n == 0) return FnListSUtil.nil();

	int[] lens = new int[n];   // length of best subsequence ending at i
	int[] prev = new int[n];   // predecessor index for reconstruction
	for (int i = 0; i < n; i++) {
	    lens[i] = 1;
	    prev[i] = -1;
	}

	// Quadratic DP: for each pair (j, i) with j < i, try to extend
	// the best sequence ending at j if xs[j] <= xs[i].
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < i; j++) {
		if (xs.getAt(j).compareTo(xs.getAt(i)) <= 0) {
		    int candidate = lens[j] + 1;
		    if (candidate > lens[i]) {
			lens[i] = candidate;
			prev[i] = j;
		    } else if (candidate == lens[i]) {
			// Tie-break: prefer the leftmost predecessor chain.
			if (prev[i] == -1 || j < prev[i]) {
			    prev[i] = j;
			}
		    }
		}
	    }
	}

	// Pick the end index of a longest subsequence; choose the smallest
	// index in case of ties to keep the result leftmost.
	int bestLen = 0;
	int bestIdx = 0;
	for (int i = 0; i < n; i++) {
	    if (lens[i] > bestLen || (lens[i] == bestLen && i < bestIdx)) {
		bestLen = lens[i];
		bestIdx = i;
	    }
	}

	// Reconstruct the indices by following the prev chain.
	FnList<Integer> result = FnListSUtil.nil();
	int cur = bestIdx;
	while (cur != -1) {
	    result = FnListSUtil.cons(cur, result);
	    cur = prev[cur];
	}
	return result;
    }
    public static void main (String[] args) {
	// HX-2025-11-19:
	// Please write minimal testing code for FnA1szLongestMonoSubsequence
	Integer[] sample = {1, 2, 1, 2, 3, 1, 2, 3, 4};
	FnA1sz<Integer> arr = new FnA1sz<>(sample);
	System.out.println("Input array:");
	arr.System$out$print();
	System.out.println();

	FnList<Integer> indices = FnA1szLongestMonoSubsequence(arr);
	System.out.print("Longest leftmost ascending subsequence indices: ");
	indices.System$out$print();
	System.out.println();

	// Edge case: empty input
	FnA1sz<Integer> empty = new FnA1sz<>(new Integer[]{});
	FnList<Integer> emptyRes = FnA1szLongestMonoSubsequence(empty);
	System.out.print("Empty input result: ");
	emptyRes.System$out$print();
	System.out.println();
	return /*void*/;
    }
}
