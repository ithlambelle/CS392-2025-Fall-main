import Library.FnList.*;
import Library.FnTuple.*;
import Library.LnStrm.*;

public class TestAssign08 {
    public static void main(String[] args) {
	System.out.println("=== Testing Assign08_01 (Separate Chaining) ===");
	testSeparateChaining();
	
	System.out.println("\n=== Testing Assign08_02 (Quadratic Probing) ===");
	testQuadraticProbing();
	
	System.out.println("\n=== All tests completed ===");
    }
    
    static void testSeparateChaining() {
	Assign08_01<Integer> map = new Assign08_01<Integer>(10);
	
	// test empty map
	System.out.println("empty map - size: " + map.size() + ", isEmpty: " + map.isEmpty());
	
	// test insert
	map.insert$raw("key1", 100);
	map.insert$raw("key2", 200);
	map.insert$raw("key1", 101); // duplicate key
	System.out.println("after inserts - size: " + map.size());
	
	// test search
	FnList<Integer> vals1 = map.search$raw("key1");
	System.out.println("key1 values: " + vals1.length());
	
	// test strmize
	LnStrm<FnTupl2<String, FnList<Integer>>> stream = map.strmize();
	int count = 0;
	LnStcn<FnTupl2<String, FnList<Integer>>> stcn = stream.eval0();
	while (stcn.consq()) {
	    count++;
	    FnTupl2<String, FnList<Integer>> pair = stcn.hd();
	    System.out.println("stream pair " + count + ": key=" + pair.sub0 + ", vals=" + pair.sub1.length());
	    stcn = stcn.tl().eval0();
	}
	System.out.println("total pairs in stream: " + count);
	
	// test remove
	FnList<Integer> removed = map.remove$raw("key1");
	System.out.println("removed key1, size now: " + map.size());
    }
    
    static void testQuadraticProbing() {
	Assign08_02<Integer> map = new Assign08_02<Integer>(10);
	
	// test empty map
	System.out.println("empty map - size: " + map.size() + ", isEmpty: " + map.isEmpty());
	
	// test insert
	map.insert$raw("key1", 100);
	map.insert$raw("key2", 200);
	map.insert$raw("key1", 101); // duplicate key
	System.out.println("after inserts - size: " + map.size());
	
	// test search
	FnList<Integer> vals1 = map.search$raw("key1");
	System.out.println("key1 values: " + vals1.length());
	
	// test strmize
	LnStrm<FnTupl2<String, FnList<Integer>>> stream = map.strmize();
	int count = 0;
	LnStcn<FnTupl2<String, FnList<Integer>>> stcn = stream.eval0();
	while (stcn.consq()) {
	    count++;
	    FnTupl2<String, FnList<Integer>> pair = stcn.hd();
	    System.out.println("stream pair " + count + ": key=" + pair.sub0 + ", vals=" + pair.sub1.length());
	    stcn = stcn.tl().eval0();
	}
	System.out.println("total pairs in stream: " + count);
	
	// test remove
	FnList<Integer> removed = map.remove$raw("key1");
	System.out.println("removed key1, size now: " + map.size());
    }
}

