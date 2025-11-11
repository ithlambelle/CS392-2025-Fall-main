import Library.LnStrm.*;
import Library.FnList.*;
import Library.FnGtree.*;

class UnsupportedOpr
    extends RuntimeException {
    String opr;
    public UnsupportedOpr(String opr) {
	this.opr = opr;
    }
}

abstract class Term {
    public String tag = "Term";
    public abstract double eval();
    // eval() returns the value of the term
}

class TermInt extends Term {
    public int val;
    public TermInt(int val) {
	this.tag = "TermInt"; this.val = val;
    }
    public double eval() { return val; }
    public boolean equals(Object obj) {
	if (obj instanceof TermInt) {
	    return this.val == ((TermInt)obj).val;
	}
	return false;
    }
}

class TermOpr extends Term {
    public String opr;
    public Term arg1, arg2;
    public TermOpr(String opr0, Term arg1, Term arg2) {
	this.tag = "TermOpr";
	this.opr = opr0; this.arg1 = arg1; this.arg2 = arg2;
    }
    public double eval() {
	switch (opr) {
	  case "+":
	      return arg1.eval() + arg2.eval();
	  case "-":
	      return arg1.eval() - arg2.eval();
	  case "*":
	      return arg1.eval() * arg2.eval();
	  case "/":
	      return arg1.eval() / arg2.eval();
	}
	throw new UnsupportedOpr(     opr     );
    }
}

// Tree node representing a state in Game-of-24
class GameState implements FnGtree<Term> {
    FnList<Term> terms;
    
    public GameState(FnList<Term> terms) {
	this.terms = terms;
    }
    
    public Term value() {
	// If we have one term, return it; otherwise return null (not a solution yet)
	if (terms.length() == 1) {
	    return terms.hd();
	}
	return null;
    }
    
    public FnList<FnGtree<Term>> children() {
	// If we have 1 term, no children (leaf node)
	if (terms.length() <= 1) {
	    return FnListSUtil.nil();
	}
	
	// Generate all possible ways to combine two terms
	FnList<FnGtree<Term>> children = FnListSUtil.nil();
	
	// Try all pairs of terms
	FnList<Term> rest1 = terms;
	while (!rest1.nilq()) {
	    Term t1 = rest1.hd();
	    FnList<Term> rest2 = rest1.tl();
	    
	    while (!rest2.nilq()) {
		Term t2 = rest2.hd();
		
		// Get remaining terms (all except t1 and t2)
		// Since t1 comes before t2 in the list, we can build remaining more efficiently
		FnList<Term> remaining = FnListSUtil.nil();
		FnList<Term> temp = terms;
		boolean skip1 = false, skip2 = false;
		while (!temp.nilq()) {
		    Term current = temp.hd();
		    // Skip first occurrence of t1 and t2
		    if (!skip1 && current == t1) {
			skip1 = true;
		    } else if (!skip2 && current == t2) {
			skip2 = true;
		    } else {
			remaining = FnListSUtil.cons(current, remaining);
		    }
		    temp = temp.tl();
		}
		remaining = remaining.reverse();
		
		// Try all 4 operators
		String[] operators = {"+", "-", "*", "/"};
		for (String op : operators) {
		    // Skip division by zero
		    if (op.equals("/") && Math.abs(t2.eval()) < 1e-10) {
			continue;
		    }
		    
		    Term newTerm = new TermOpr(op, t1, t2);
		    FnList<Term> newTerms = FnListSUtil.cons(newTerm, remaining);
		    children = FnListSUtil.cons(new GameState(newTerms), children);
		    
		    // Also try reverse order for non-commutative operators
		    if (!op.equals("+") && !op.equals("*")) {
			if (op.equals("/") && Math.abs(t1.eval()) < 1e-10) {
			    continue;
			}
			Term newTerm2 = new TermOpr(op, t2, t1);
			FnList<Term> newTerms2 = FnListSUtil.cons(newTerm2, remaining);
			children = FnListSUtil.cons(new GameState(newTerms2), children);
		    }
		}
		
		rest2 = rest2.tl();
	    }
	    
	    rest1 = rest1.tl();
	}
	
	return children.reverse();
    }
}

public class Assign07_02 {
//
    public LnStrm<Term> GameOf24_bfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Each solution is represented as a Term
	// that evaluates to 24
	// Note that your solution should be based on
	// BFirstEnumerate implemented in Assign07_01
	
	// Create initial state with 4 numbers
	FnList<Term> initial = FnListSUtil.nil();
	initial = FnListSUtil.cons(new TermInt(n4), initial);
	initial = FnListSUtil.cons(new TermInt(n3), initial);
	initial = FnListSUtil.cons(new TermInt(n2), initial);
	initial = FnListSUtil.cons(new TermInt(n1), initial);
	
	GameState root = new GameState(initial);
	
	// Use BFirstEnumerate to traverse the tree
	LnStrm<Term> allTerms = Assign07_01.BFirstEnumerate(root);
	
	// Filter for terms that evaluate to 24
	// Note: value() returns null for non-leaf nodes, so we need to skip them
	// filter0 stops at null values (consq() returns false), so we need custom filtering
	return new LnStrm<Term>(
	    () -> {
		LnStcn<Term> cxs = allTerms.eval0();
		while (cxs != null) {
		    if (cxs.consq()) {
			// Non-null head
			Term hd = cxs.head;
			LnStrm<Term> tl = cxs.tail;
			try {
			    double val = hd.eval();
			    if (Math.abs(val - 24.0) < 1e-10) {
				return new LnStcn<Term>(hd, GameOf24_bfs_solve_filter_helper(tl));
			    }
			} catch (Exception e) {
			    // Skip this term
			}
			cxs = tl.eval0();
		    } else {
			// Null head (non-leaf node), continue to tail
			if (cxs.tail != null) {
			    cxs = cxs.tail.eval0();
			} else {
			    break;
			}
		    }
		}
		return new LnStcn<Term>(); // no solutions found
	    }
	);
    }
    private static LnStrm<Term> GameOf24_bfs_solve_filter_helper(LnStrm<Term> tl) {
	return new LnStrm<Term>(
	    () -> {
		LnStcn<Term> cxs = tl.eval0();
		while (cxs != null) {
		    if (cxs.consq()) {
			Term hd = cxs.head;
			LnStrm<Term> nextTl = cxs.tail;
			try {
			    double val = hd.eval();
			    if (Math.abs(val - 24.0) < 1e-10) {
				return new LnStcn<Term>(hd, GameOf24_bfs_solve_filter_helper(nextTl));
			    }
			} catch (Exception e) {
			    // Skip
			}
			cxs = nextTl.eval0();
		    } else {
			if (cxs.tail != null) {
			    cxs = cxs.tail.eval0();
			} else {
			    break;
			}
		    }
		}
		return new LnStcn<Term>();
	    }
	);
    }

    public LnStrm<Term> GameOf24_dfs_solve
	(int n1, int n2, int n3, int n4) {
	// Please find ALL the solutions of GameOf24
	// for the input n1, n2, n3, and n4
	// Note that your solution should be based on
	// DFirstEnumerate implemented in Assign07_01
	
	// Create initial state with 4 numbers
	FnList<Term> initial = FnListSUtil.nil();
	initial = FnListSUtil.cons(new TermInt(n4), initial);
	initial = FnListSUtil.cons(new TermInt(n3), initial);
	initial = FnListSUtil.cons(new TermInt(n2), initial);
	initial = FnListSUtil.cons(new TermInt(n1), initial);
	
	GameState root = new GameState(initial);
	
	// Use DFirstEnumerate to traverse the tree
	LnStrm<Term> allTerms = Assign07_01.DFirstEnumerate(root);
	
	// Filter for terms that evaluate to 24
	// Note: value() returns null for non-leaf nodes, so we need to skip them
	return new LnStrm<Term>(
	    () -> {
		LnStcn<Term> cxs = allTerms.eval0();
		while (cxs != null) {
		    if (cxs.consq()) {
			// Non-null head
			Term hd = cxs.head;
			LnStrm<Term> tl = cxs.tail;
			try {
			    double val = hd.eval();
			    if (Math.abs(val - 24.0) < 1e-10) {
				return new LnStcn<Term>(hd, GameOf24_dfs_solve_filter_helper(tl));
			    }
			} catch (Exception e) {
			    // Skip this term
			}
			cxs = tl.eval0();
		    } else {
			// Null head (non-leaf node), continue to tail
			if (cxs.tail != null) {
			    cxs = cxs.tail.eval0();
			} else {
			    break;
			}
		    }
		}
		return new LnStcn<Term>(); // no solutions found
	    }
	);
    }
    private static LnStrm<Term> GameOf24_dfs_solve_filter_helper(LnStrm<Term> tl) {
	return new LnStrm<Term>(
	    () -> {
		LnStcn<Term> cxs = tl.eval0();
		while (cxs != null) {
		    if (cxs.consq()) {
			Term hd = cxs.head;
			LnStrm<Term> nextTl = cxs.tail;
			try {
			    double val = hd.eval();
			    if (Math.abs(val - 24.0) < 1e-10) {
				return new LnStcn<Term>(hd, GameOf24_dfs_solve_filter_helper(nextTl));
			    }
			} catch (Exception e) {
			    // Skip
			}
			cxs = nextTl.eval0();
		    } else {
			if (cxs.tail != null) {
			    cxs = cxs.tail.eval0();
			} else {
			    break;
			}
		    }
		}
		return new LnStcn<Term>();
	    }
	);
    }
//
    // Please add minimal testing code for GameOf24_bfs_solve
    // Please add minimal testing code for GameOf24_dfs_solve
    public static void main(String[] args) {
	Assign07_02 solver = new Assign07_02();
	
	// Test with numbers that have known solutions (e.g., 1, 2, 3, 4)
	System.out.println("Testing Game-of-24 with BFS:");
	LnStrm<Term> bfsSolutions = solver.GameOf24_bfs_solve(1, 2, 3, 4);
	int bfsCount = 0;
	LnStcn<Term> bfsStcn = bfsSolutions.eval0();
	while (!bfsStcn.nilq()) {
	    Term sol = bfsStcn.head;
	    bfsCount++;
	    System.out.println("Solution " + bfsCount + ": evaluates to " + sol.eval());
	    bfsStcn = bfsStcn.tail.eval0();
	}
	System.out.println("Found " + bfsCount + " BFS solutions");
	
	System.out.println("\nTesting Game-of-24 with DFS:");
	LnStrm<Term> dfsSolutions = solver.GameOf24_dfs_solve(1, 2, 3, 4);
	int dfsCount = 0;
	LnStcn<Term> dfsStcn = dfsSolutions.eval0();
	while (!dfsStcn.nilq()) {
	    Term sol = dfsStcn.head;
	    dfsCount++;
	    System.out.println("Solution " + dfsCount + ": evaluates to " + sol.eval());
	    dfsStcn = dfsStcn.tail.eval0();
	}
	System.out.println("Found " + dfsCount + " DFS solutions");
    }
//
} // end of [public class Assign07_02{...}]

