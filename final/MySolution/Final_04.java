/*
// HX: 50 points for Final_04
// HX: This one tests your RBST implementation done in Quiz02_06.
// In Final_02, pg2701_word$count$listize1() is implemented
// to list words in pg2701.txt according their frequencies.
// In Final_04, you are asked to implement the same functionality
// with a different approach.
*/

import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

public class Final_04 {
    static FnList<FnTupl2<FnList<Character>, Integer>> pg2701_word$count$listize4() {
	LnStrm<FnList<Character>> ws = Final_01.pg2701_word$strmize();
	Quiz02_06<String, Integer> map =
	    new Quiz02_06<String, Integer>((s1, s2) -> s1.compareTo(s2));

	LnStcn<FnList<Character>> cell = ws.eval0();
	while (cell.consq()) {
	    String key = FinalUtil.wordToString(cell.hd());
	    Integer cur = map.get(key);
	    if (cur == null) {
		map.put(key, 1);
	    } else {
		map.put(key, cur + 1);
	    }
	    ws = cell.tl();
	    cell = ws.eval0();
	}

	FnList<FnTupl2<FnList<Character>, Integer>> pairs =
	    new FnList<FnTupl2<FnList<Character>, Integer>>();
	FnList<FnTupl2<String, Integer>> kvs = map.toList();
	while (kvs.consq()) {
	    FnTupl2<String, Integer> kv = kvs.hd();
	    FnList<Character> word = FinalUtil.stringToWord(kv.sub0);
	    pairs = new FnList<FnTupl2<FnList<Character>, Integer>>
		(new FnTupl2<FnList<Character>, Integer>(word, kv.sub1), pairs);
	    kvs = kvs.tl();
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
	    pg2701_word$count$listize4();
	int shown = 0;
	while (shown < 100 && res.consq()) {
	    FnTupl2<FnList<Character>, Integer> pair = res.hd();
	    System.out.println(FinalUtil.wordToString(pair.sub0) + ": " + pair.sub1);
	    shown += 1;
	    res = res.tl();
	}
    }
}
