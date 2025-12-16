/*
// HX: 20 points for Final_01
// A word consists of a sequence of
// letters ([a-z]+[A-Z]) plus aprostrophe (')
// And words are separated by non-letters-aprostrophe
// (such as blanks, punctuations, etc.) in pg2701.txt.
*/

import Library.FnList.*;
import Library.LnStrm.*;

public class Final_01 {
    static LnStrm<FnList<Character>> pg2701_word$strmize() {
	return FinalUtil.wordStream(Final_00.pg2701_char$strmize());
    }
    public static void main (String[] args) {
	LnStrm<FnList<Character>> ws = pg2701_word$strmize();
	for (int i = 0; i < 20; i++) {
	    LnStcn<FnList<Character>> cell = ws.eval0();
	    if (!cell.consq()) break;
	    System.out.println(FinalUtil.wordToString(cell.hd()));
	    ws = cell.tl();
	}
    }
}
