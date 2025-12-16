import Library.FnList.*;
import Library.LnStrm.*;

public class FinalUtil {
    static boolean wordChar(char c) {
	return (c >= 'a' && c <= 'z') ||
	       (c >= 'A' && c <= 'Z') ||
	       c == '\'';
    }

    static char lower(char c) {
	if (c >= 'A' && c <= 'Z') {
	    return (char) (c - 'A' + 'a');
	}
	return c;
    }

    static int compareWords(FnList<Character> w1, FnList<Character> w2) {
	FnList<Character> xs = w1;
	FnList<Character> ys = w2;
	while (!xs.nilq() && !ys.nilq()) {
	    char c1 = xs.hd();
	    char c2 = ys.hd();
	    if (c1 != c2) {
		return (c1 < c2) ? -1 : 1;
	    }
	    xs = xs.tl();
	    ys = ys.tl();
	}
	if (xs.nilq() && ys.nilq()) return 0;
	return xs.nilq() ? -1 : 1;
    }

    static boolean sameWord(FnList<Character> w1, FnList<Character> w2) {
	return compareWords(w1, w2) == 0;
    }

    static String wordToString(FnList<Character> w) {
	int n = w.length();
	char[] buf = new char[n];
	FnList<Character> xs = w;
	for (int i = 0; i < n; i++) {
	    buf[i] = xs.hd();
	    xs = xs.tl();
	}
	return new String(buf);
    }

    static FnList<Character> stringToWord(String s) {
	FnList<Character> res = new FnList<Character>();
	for (int i = s.length() - 1; i >= 0; i--) {
	    res = new FnList<Character>(s.charAt(i), res);
	}
	return res;
    }

    static LnStrm<FnList<Character>> wordStream(LnStrm<Character> chars) {
	return new LnStrm<FnList<Character>>(
	  () -> {
	      LnStrm<Character> cs = chars;
	      LnStcn<Character> cell = cs.eval0();
	      while (cell.consq() && !wordChar(cell.hd())) {
		  cs = cell.tl();
		  cell = cs.eval0();
	      }
	      if (!cell.consq()) {
		  return new LnStcn<FnList<Character>>();
	      }
	      FnList<Character> acc = new FnList<Character>();
	      while (cell.consq() && wordChar(cell.hd())) {
		  acc = new FnList<Character>(lower(cell.hd()), acc);
		  cs = cell.tl();
		  cell = cs.eval0();
	      }
	      FnList<Character> word = acc.reverse();
	      final LnStcn<Character> cellNext = cell;
	      LnStrm<Character> rest = new LnStrm<Character>(() -> cellNext);
	      return new LnStcn<FnList<Character>>(word, wordStream(rest));
	  }
	);
    }
}
