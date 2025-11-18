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
	return Math.abs(h) % capacity;
    }
    
    // size: return number of distinct keys
    public int size() {
	return keyCount;
    }
    
    // isFull: check if table is full (all buckets have at least one key)
    public boolean isFull() {
	return keyCount >= capacity;
    }
    
    // isEmpty: check if table has no keys
    public boolean isEmpty() {
	return keyCount == 0;
    }
    
    // search$raw: search for key, assume it exists
    public FnList<V> search$raw(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	LnList<FnTupl2<String, FnList<V>>> curr = bucket;
	while (!curr.nilq1()) {
	    FnTupl2<String, FnList<V>> pair = curr.hd1();
	    if (pair.sub0.equals(key)) {
		return pair.sub1;
	    }
	    curr = curr.tl1();
	}
	// should not reach here if key exists
	return new FnList<V>();
    }
    
    // search$exn: search for key, throw exception if not found
    public FnList<V> search$exn(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	LnList<FnTupl2<String, FnList<V>>> curr = bucket;
	while (!curr.nilq1()) {
	    FnTupl2<String, FnList<V>> pair = curr.hd1();
	    if (pair.sub0.equals(key)) {
		return pair.sub1;
	    }
	    curr = curr.tl1();
	}
	throw new MyMap00NoKeyExn();
    }
    
    // search$opt: search for key, return null if not found
    public FnList<V> search$opt(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	LnList<FnTupl2<String, FnList<V>>> curr = bucket;
	while (!curr.nilq1()) {
	    FnTupl2<String, FnList<V>> pair = curr.hd1();
	    if (pair.sub0.equals(key)) {
		return pair.sub1;
	    }
	    curr = curr.tl1();
	}
	return null;
    }
    
    // insert$raw: insert key-value pair, assume it works
    public void insert$raw(String key, V val) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	LnList<FnTupl2<String, FnList<V>>> curr = bucket;
	
	// check if key already exists
	while (!curr.nilq1()) {
	    FnTupl2<String, FnList<V>> pair = curr.hd1();
	    if (pair.sub0.equals(key)) {
		// key exists, prepend value to list (LIFO)
		pair.sub1 = new FnList<V>(val, pair.sub1);
		return;
	    }
	    curr = curr.tl1();
	}
	
	// key doesn't exist, add new entry
	FnList<V> vals = new FnList<V>(val, new FnList<V>());
	FnTupl2<String, FnList<V>> newPair = new FnTupl2<String, FnList<V>>(key, vals);
	table[idx] = new LnList<FnTupl2<String, FnList<V>>>(newPair, bucket);
	keyCount++;
    }
    
    // insert$exn: insert key-value pair, throw exception if full
    public void insert$exn(String key, V val) {
	if (isFull()) {
	    throw new MyMap00FullExn();
	}
	insert$raw(key, val);
    }
    
    // insert$opt: insert key-value pair, return false if full
    public boolean insert$opt(String key, V val) {
	if (isFull()) {
	    return false;
	}
	insert$raw(key, val);
	return true;
    }
    
    // remove$raw: remove key, assume it exists
    public FnList<V> remove$raw(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	
	// if bucket is empty, key doesn't exist (shouldn't happen)
	if (bucket.nilq1()) {
	    return new FnList<V>();
	}
	
	// check first element
	if (bucket.hd1().sub0.equals(key)) {
	    FnList<V> vals = bucket.hd1().sub1;
	    table[idx] = bucket.tl1();
	    keyCount--;
	    return vals;
	}
	
	// search in rest of bucket
	LnList<FnTupl2<String, FnList<V>>> prev = bucket;
	LnList<FnTupl2<String, FnList<V>>> curr = bucket.tl1();
	while (!curr.nilq1()) {
	    if (curr.hd1().sub0.equals(key)) {
		FnList<V> vals = curr.hd1().sub1;
		prev.link1(curr.tl1());
		keyCount--;
		return vals;
	    }
	    prev = curr;
	    curr = curr.tl1();
	}
	
	// should not reach here
	return new FnList<V>();
    }
    
    // remove$exn: remove key, throw exception if not found
    public FnList<V> remove$exn(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	
	if (bucket.nilq1()) {
	    throw new MyMap00NoKeyExn();
	}
	
	// check first element
	if (bucket.hd1().sub0.equals(key)) {
	    FnList<V> vals = bucket.hd1().sub1;
	    table[idx] = bucket.tl1();
	    keyCount--;
	    return vals;
	}
	
	// search in rest of bucket
	LnList<FnTupl2<String, FnList<V>>> prev = bucket;
	LnList<FnTupl2<String, FnList<V>>> curr = bucket.tl1();
	while (!curr.nilq1()) {
	    if (curr.hd1().sub0.equals(key)) {
		FnList<V> vals = curr.hd1().sub1;
		prev.link1(curr.tl1());
		keyCount--;
		return vals;
	    }
	    prev = curr;
	    curr = curr.tl1();
	}
	
	throw new MyMap00NoKeyExn();
    }
    
    // remove$opt: remove key, return null if not found
    public FnList<V> remove$opt(String key) {
	int idx = hash(key);
	LnList<FnTupl2<String, FnList<V>>> bucket = table[idx];
	
	if (bucket.nilq1()) {
	    return null;
	}
	
	// check first element
	if (bucket.hd1().sub0.equals(key)) {
	    FnList<V> vals = bucket.hd1().sub1;
	    table[idx] = bucket.tl1();
	    keyCount--;
	    return vals;
	}
	
	// search in rest of bucket
	LnList<FnTupl2<String, FnList<V>>> prev = bucket;
	LnList<FnTupl2<String, FnList<V>>> curr = bucket.tl1();
	while (!curr.nilq1()) {
	    if (curr.hd1().sub0.equals(key)) {
		FnList<V> vals = curr.hd1().sub1;
		prev.link1(curr.tl1());
		keyCount--;
		return vals;
	    }
	    prev = curr;
	    curr = curr.tl1();
	}
	
	return null;
    }
    
    // strmize: create stream of all key-value list pairs
    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
	// collect all pairs into an fnlist, then convert to stream
	FnList<FnTupl2<String, FnList<V>>> pairs = new FnList<FnTupl2<String, FnList<V>>>();
	
	// iterate through all buckets and collect pairs
	for (int i = capacity - 1; i >= 0; i--) {
	    LnList<FnTupl2<String, FnList<V>>> bucket = table[i];
	    LnList<FnTupl2<String, FnList<V>>> curr = bucket;
	    
	    // collect pairs from this bucket (in reverse order to maintain insertion order)
	    while (!curr.nilq1()) {
		pairs = new FnList<FnTupl2<String, FnList<V>>>(curr.hd1(), pairs);
		curr = curr.tl1();
	    }
	}
	
	// convert fnlist to stream
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

