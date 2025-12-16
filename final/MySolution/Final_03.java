/*
// HX: 50 points for Final_03
// HX: This one tests your hash map implementation
// In Final_02, pg2701_word$count$listize2() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_03, you are asked to implement the same functionality
// with a different approach.
*/

import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

public class Final_03 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize3() {
	LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
	// using hash map as multimap: insert$opt appends each occurrence to a list.
	// word frequency = length of the value list for that key.
	// large capacity to minimize collisions for pg2701.txt word count
	Assign08_02<Integer> map = new Assign08_02<Integer>(600000);

	LnStcn<FnList<Character>> cell = ws.eval0();
	while (cell.consq()) {
	    String key = FinalUtil.wordToString(cell.hd());
	    map.insert$opt(key, 1);
	    ws = cell.tl();
	    cell = ws.eval0();
	}

	FnList<FnTupl2<FnList<Character>, Integer>> pairs =
	    new FnList<FnTupl2<FnList<Character>, Integer>>();
	LnStrm<FnTupl2<String, FnList<Integer>>> stream = map.strmize();
	LnStcn<FnTupl2<String, FnList<Integer>>> scell = stream.eval0();
	while (scell.consq()) {
	    FnTupl2<String, FnList<Integer>> kv = scell.hd();
	    int count = kv.sub1.length();
	    FnList<Character> word = FinalUtil.stringToWord(kv.sub0);
	    pairs = new FnList<FnTupl2<FnList<Character>, Integer>>
		(new FnTupl2<FnList<Character>, Integer>(word, count), pairs);
	    stream = scell.tl();
	    scell = stream.eval0();
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
	    pg2701_word$count$listize3();
	int shown = 0;
	while (shown < 100 && res.consq()) {
	    FnTupl2<FnList<Character>, Integer> pair = res.hd();
	    System.out.println(FinalUtil.wordToString(pair.sub0) + ": " + pair.sub1);
	    shown += 1;
	    res = res.tl();
	}
    }
}
