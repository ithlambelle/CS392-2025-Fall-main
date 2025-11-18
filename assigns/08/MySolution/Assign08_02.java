import Library.FnList.*;
import Library.LnList.*;
import Library.FnTuple.*;
import Library.MyMap00.*;
import Library.LnStrm.*;

import java.util.function.BiConsumer;

public class Assign08_02<V>
    implements MyMap00<String, V> {
    // HX-2025-11-12:
    // Please give an implementation of hash table
    // based on open addressing. The probing strategy
    // chosen for handling collisions is quadratic probing.
    private FnTupl2<String, FnList<V>> table[];
    private int capacity;
    private int keyCount; // number of distinct keys
    
    // constructor: initialize hash table with given capacity
    public Assign08_02(int cap) {
	capacity = cap;
	table = new FnTupl2[capacity];
	// all slots are initially null (empty)
	for (int i = 0; i < capacity; i++) {
	    table[i] = null;
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
    
    // quadratic probing: compute probe sequence index
    // h(k, i) = (h(k) + i^2) mod capacity
    private int probe(int baseHash, int i) {
	return (baseHash + i * i) % capacity;
    }
    
    // size: return number of distinct keys
    public int size() {
	return keyCount;
    }
    
    // isFull: check if table is full (all slots occupied)
    public boolean isFull() {
	return keyCount >= capacity;
    }
    
    // isEmpty: check if table has no keys
    public boolean isEmpty() {
	return keyCount == 0;
    }
    
    // search$raw: search for key, assume it exists
    public FnList<V> search$raw(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		return slot.sub1;
	    }
	    // if we hit a null slot, key doesn't exist (shouldn't happen in raw)
	    if (slot == null) {
		break;
	    }
	}
	// should not reach here if key exists
	return new FnList<V>();
    }
    
    // search$exn: search for key, throw exception if not found
    public FnList<V> search$exn(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		return slot.sub1;
	    }
	    // if we hit a null slot, key doesn't exist
	    if (slot == null) {
		throw new MyMap00NoKeyExn();
	    }
	}
	// table is full and key not found
	throw new MyMap00NoKeyExn();
    }
    
    // search$opt: search for key, return null if not found
    public FnList<V> search$opt(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		return slot.sub1;
	    }
	    // if we hit a null slot, key doesn't exist
	    if (slot == null) {
		return null;
	    }
	}
	// table is full and key not found
	return null;
    }
    
    // insert$raw: insert key-value pair, assume it works
    public void insert$raw(String key, V val) {
	int baseHash = hash(key);
	
	// first, check if key already exists
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		// key exists, prepend value to list (LIFO)
		slot.sub1 = new FnList<V>(val, slot.sub1);
		return;
	    }
	    if (slot == null) {
		break; // found empty slot, will insert here
	    }
	}
	
	// key doesn't exist, find empty slot to insert
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    if (table[idx] == null) {
		// found empty slot
		FnList<V> vals = new FnList<V>(val, new FnList<V>());
		table[idx] = new FnTupl2<String, FnList<V>>(key, vals);
		keyCount++;
		return;
	    }
	}
	// should not reach here if insertion is assumed to work
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
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		// found key, remove it
		FnList<V> vals = slot.sub1;
		table[idx] = null; // mark slot as empty
		keyCount--;
		return vals;
	    }
	    if (slot == null) {
		break; // key doesn't exist (shouldn't happen in raw)
	    }
	}
	// should not reach here
	return new FnList<V>();
    }
    
    // remove$exn: remove key, throw exception if not found
    public FnList<V> remove$exn(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		// found key, remove it
		FnList<V> vals = slot.sub1;
		table[idx] = null; // mark slot as empty
		keyCount--;
		return vals;
	    }
	    if (slot == null) {
		// key doesn't exist
		throw new MyMap00NoKeyExn();
	    }
	}
	// table is full and key not found
	throw new MyMap00NoKeyExn();
    }
    
    // remove$opt: remove key, return null if not found
    public FnList<V> remove$opt(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot != null && slot.sub0.equals(key)) {
		// found key, remove it
		FnList<V> vals = slot.sub1;
		table[idx] = null; // mark slot as empty
		keyCount--;
		return vals;
	    }
	    if (slot == null) {
		// key doesn't exist
		return null;
	    }
	}
	// table is full and key not found
	return null;
    }
    
    // strmize: create stream of all key-value list pairs
    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
	// collect all non-null pairs into an fnlist, then convert to stream
	FnList<FnTupl2<String, FnList<V>>> pairs = new FnList<FnTupl2<String, FnList<V>>>();
	
	// iterate through all slots and collect non-null pairs
	for (int i = capacity - 1; i >= 0; i--) {
	    if (table[i] != null) {
		pairs = new FnList<FnTupl2<String, FnList<V>>>(table[i], pairs);
	    }
	}
	
	// convert fnlist to stream
	return pairs.toLnStrm();
    }
    
    // foritm: iterate through all key-value pairs
    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    if (table[i] != null) {
		String key = table[i].sub0;
		FnList<V> vals = table[i].sub1;
		// iterate through all values for this key
		FnList<V> valList = vals;
		while (!valList.nilq()) {
		    work.accept(key, valList.hd());
		    valList = valList.tl();
		}
	    }
	}
    }
}

