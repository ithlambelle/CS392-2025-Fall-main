import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;
import Library.MyMap00.*;
import Library.LnStrm.*;

import java.util.function.BiConsumer;

public class Assign08_01<V>
    implements MyMap00<String, V> {
    // HX-2025-11-12:
    // Please give an implementation of hash table
    // that uses separate chaining for handling collisions.
    private LnList<FnTupl2<String, FnList<V>>> table[];
    private int capacity;
    private int keyCount; // number of distinct keys
    
    // constructor: initialize hash table with given capacity
    public Assign08_01(int cap) {
	capacity = cap;
	table = new LnList[capacity];
	for (int i = 0; i < capacity; i++) {
	    table[i] = new LnList<FnTupl2<String, FnList<V>>>();
	}
	keyCount = 0;
    }
    
    // hash function for string keys
    private int hash(String key) {
	int h = 0;
	for (int i = 0; i < key.length(); i++) {
	    h = 31 * h + key.charAt(i);
	}
	return Math.floorMod(h, capacity);
    }

    // locate entry in a bucket; return null if missing
    private FnTupl2<String, FnList<V>>
	findEntry(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	while (!bucket.nilq1()) {
	    FnTupl2<String, FnList<V>> pair = bucket.hd1();
	    if (pair.sub0.equals(key)) {
		return pair;
	    }
	    bucket = bucket.tl1();
	}
	return null;
    }
    
    // size: return number of distinct keys
    public int size() {
	return keyCount;
    }
    
    // isFull: separate chaining can always accommodate new entries
    public boolean isFull() {
	return false;
    }
    
    // isEmpty: check if table has no keys
    public boolean isEmpty() {
	return keyCount == 0;
    }
    
    // search$raw: search for key, assume it exists
    public FnList<V> search$raw(String key) {
	return findEntry(key).sub1;
    }
    
    // search$exn: search for key, throw exception if not found
    public FnList<V> search$exn(String key) {
	FnTupl2<String, FnList<V>> entry = findEntry(key);
	if (entry == null) throw new MyMap00NoKeyExn();
	return entry.sub1;
    }
    
    // search$opt: search for key, return null if not found
    public FnList<V> search$opt(String key) {
	FnTupl2<String, FnList<V>> entry = findEntry(key);
	return (entry == null ? null : entry.sub1);
    }
    
    // insert$raw: insert key-value pair, assume it works
    public void insert$raw(String key, V val) {
	int idx = hash(key);
	FnTupl2<String, FnList<V>> entry = findEntry(key);
	if (entry != null) {
	    entry.sub1 = new FnList<V>(val, entry.sub1);
	    return;
	}

	FnList<V> vals = new FnList<V>(val, new FnList<V>());
	FnTupl2<String, FnList<V>> newPair = new FnTupl2<String, FnList<V>>(key, vals);
	table[idx] = new LnList<FnTupl2<String, FnList<V>>>(newPair, table[idx]);
	keyCount += 1;
    }
    
    // insert$exn: insert key-value pair, throw exception if full
    public void insert$exn(String key, V val) {
	insert$raw(key, val);
    }
    
    // insert$opt: insert key-value pair, return false if full
    public boolean insert$opt(String key, V val) {
	insert$raw(key, val);
	return true;
    }

    private FnTupl2<String, FnList<V>>
	removeEntry(int idx, String key) {
	FnList<FnTupl2<String, FnList<V>>> kept = new FnList<FnTupl2<String, FnList<V>>>();
	FnTupl2<String, FnList<V>> removed = null;

	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	while (!bucket.nilq1()) {
	    FnTupl2<String, FnList<V>> pair = bucket.hd1();
	    if (removed == null && pair.sub0.equals(key)) {
		removed = pair;
	    } else {
		kept = new FnList<FnTupl2<String, FnList<V>>>(pair, kept);
	    }
	    bucket = bucket.tl1();
	}

	if (removed != null) {
	    table[idx] = new LnList<FnTupl2<String, FnList<V>>>(kept.reverse());
	}
	return removed;
    }
    
    // remove$raw: remove key, assume it exists
    public FnList<V> remove$raw(String key) {
	int idx = hash(key);
	FnTupl2<String, FnList<V>> removed = removeEntry(idx, key);
	keyCount -= 1;
	return removed.sub1;
    }
    
    // remove$exn: remove key, throw exception if not found
    public FnList<V> remove$exn(String key) {
	int idx = hash(key);
	FnTupl2<String, FnList<V>> removed = removeEntry(idx, key);
	if (removed == null) throw new MyMap00NoKeyExn();
	keyCount -= 1;
	return removed.sub1;
    }
    
    // remove$opt: remove key, return null if not found
    public FnList<V> remove$opt(String key) {
	int idx = hash(key);
	FnTupl2<String, FnList<V>> removed = removeEntry(idx, key);
	if (removed == null) return null;
	keyCount -= 1;
	return removed.sub1;
    }
    
    // strmize: create stream of all key-value list pairs
    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
	// collect all pairs into an fnlist, then convert to stream
	FnList<FnTupl2<String, FnList<V>>> pairs = new FnList<FnTupl2<String, FnList<V>>>();

	for (int i = capacity - 1; i >= 0; i--) {
	    LnList<FnTupl2<String, FnList<V>>> bucket = table[i];
	    while (!bucket.nilq1()) {
		pairs = new FnList<FnTupl2<String, FnList<V>>>(bucket.hd1(), pairs);
		bucket = bucket.tl1();
	    }
	}
	return pairs.toLnStrm();
    }
    
    // foritm: iterate through all key-value pairs
    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    LnList<FnTupl2<String, FnList<V>>> bucket = table[i];
	    LnList<FnTupl2<String, FnList<V>>> curr = bucket;
	    while (!curr.nilq1()) {
		FnTupl2<String, FnList<V>> pair = curr.hd1();
		String key = pair.sub0;
		FnList<V> vals = pair.sub1;
		// iterate through all values for this key
		FnList<V> valList = vals;
		while (!valList.nilq()) {
		    work.accept(key, valList.hd());
		    valList = valList.tl();
		}
		curr = curr.tl1();
	    }
	}
    }
}
