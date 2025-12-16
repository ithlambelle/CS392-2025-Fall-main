/*
// HX: 50 points for Final_02
// HX: This one tests your quicksort and mergesort
// In Final_01, pg2701_word$strmize() is implemented
// that lists all the words in pg2701.txt. Here, you
// are asked to generate FnList of pairs; each pair consists
// of a word (FnList<Character>) and a count (Integer) such that
// the count is the number of occurrences of the word in pg2701.txt.
// Note that a lower case letter is considered the same as its
// corresponding upper case. For instance, "Whale" and "whale"
// are considered the same word.
*/

import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

public class Final_02 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize2() {
	LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
	FnList<FnList<Character>> collected =
	    new FnList<FnList<Character>>();
	LnStcn<FnList<Character>> cell = ws.eval0();
	while (cell.consq()) {
	    collected = new FnList<FnList<Character>>(cell.hd(), collected);
	    ws = cell.tl();
	    cell = ws.eval0();
	}
	collected = collected.reverse();

	int n = collected.length();
	@SuppressWarnings("unchecked")
	FnList<Character>[] words = (FnList<Character>[]) new FnList[n];
	FnList<FnList<Character>> tmp = collected;
	for (int i = 0; i < n; i++) {
	    words[i] = tmp.hd();
	    tmp = tmp.tl();
	}

	Assign06_03.arrayQuickSort
	    (words, (w1, w2) -> FinalUtil.compareWords(w1, w2));

	FnList<FnTupl2<FnList<Character>, Integer>> pairs =
	    new FnList<FnTupl2<FnList<Character>, Integer>>();
	int idx = 0;
	while (idx < n) {
	    FnList<Character> w = words[idx];
	    int count = 1;
	    idx += 1;
	    while (idx < n && FinalUtil.sameWord(w, words[idx])) {
		count += 1;
		idx += 1;
	    }
	    pairs =
		new FnList<FnTupl2<FnList<Character>, Integer>>
		    (new FnTupl2<FnList<Character>, Integer>(w, count), pairs);
	}
	pairs = pairs.reverse();

	return Assign05_01.mergeSort
	    (pairs,
	     (p1, p2) -> {
		 int cmp = Integer.compare(p2.sub1, p1.sub1);
		 if (cmp != 0) return cmp;
		 return FinalUtil.compareWords(p1.sub0, p2.sub0);
	     });
    }
    public static void main (String[] args) {
	FnList<FnTupl2<FnList<Character>, Integer>> res =
	    pg2701_word$count$listize2();
	int shown = 0;
	while (shown < 100 && res.consq()) {
	    FnTupl2<FnList<Character>, Integer> pair = res.hd();
	    System.out.println(FinalUtil.wordToString(pair.sub0) + ": " + pair.sub1);
	    shown += 1;
	    res = res.tl();
	}
    }
}
