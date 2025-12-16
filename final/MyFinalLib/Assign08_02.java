import Library.FnList.*;
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
    private final FnTupl2<String, FnList<V>> DELETED =
      new FnTupl2<String, FnList<V>>(null, null);
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
	return Math.floorMod(h, capacity);
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

    private FnTupl2<String, FnList<V>>
	findEntry(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot == null) {
		return null;
	    }
	    if (slot == DELETED) {
		continue;
	    }
	    if (slot.sub0.equals(key)) {
		return slot;
	    }
	}
	return null;
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
	int baseHash = hash(key);
	int firstAvail = -1; // first null or deleted slot
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot == null) {
		if (firstAvail == -1) firstAvail = idx;
		break;
	    }
	    if (slot == DELETED) {
		if (firstAvail == -1) firstAvail = idx;
		continue;
	    }
	    if (slot.sub0.equals(key)) {
		slot.sub1 = new FnList<V>(val, slot.sub1);
		return;
	    }
	}

	if (firstAvail != -1) {
	    FnList<V> vals = new FnList<V>(val, new FnList<V>());
	    table[firstAvail] = new FnTupl2<String, FnList<V>>(key, vals);
	    keyCount += 1;
	}
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

    private FnTupl2<String, FnList<V>>
	removeEntry(String key) {
	int baseHash = hash(key);
	for (int i = 0; i < capacity; i++) {
	    int idx = probe(baseHash, i);
	    FnTupl2<String, FnList<V>> slot = table[idx];
	    if (slot == null) {
		return null;
	    }
	    if (slot == DELETED) {
		continue;
	    }
	    if (slot.sub0.equals(key)) {
		table[idx] = DELETED;
		keyCount -= 1;
		return slot;
	    }
	}
	return null;
    }
    
    // remove$raw: remove key, assume it exists
    public FnList<V> remove$raw(String key) {
	return removeEntry(key).sub1;
    }
    
    // remove$exn: remove key, throw exception if not found
    public FnList<V> remove$exn(String key) {
	FnTupl2<String, FnList<V>> removed = removeEntry(key);
	if (removed == null) throw new MyMap00NoKeyExn();
	return removed.sub1;
    }
    
    // remove$opt: remove key, return null if not found
    public FnList<V> remove$opt(String key) {
	FnTupl2<String, FnList<V>> removed = removeEntry(key);
	if (removed == null) return null;
	return removed.sub1;
    }
    
    // strmize: create stream of all key-value list pairs
    public LnStrm<FnTupl2<String, FnList<V>>> strmize() {
	// collect all non-null pairs into an fnlist, then convert to stream
	FnList<FnTupl2<String, FnList<V>>> pairs = new FnList<FnTupl2<String, FnList<V>>>();
	
	// iterate through all slots and collect non-null pairs
	for (int i = capacity - 1; i >= 0; i--) {
	    FnTupl2<String, FnList<V>> slot = table[i];
	    if (slot != null && slot != DELETED) {
		pairs = new FnList<FnTupl2<String, FnList<V>>>(slot, pairs);
	    }
	}
	
	// convert fnlist to stream
	return pairs.toLnStrm();
    }
    
    // foritm: iterate through all key-value pairs
    public void foritm(BiConsumer<? super String, ? super V> work) {
	for (int i = 0; i < capacity; i++) {
	    FnTupl2<String, FnList<V>> slot = table[i];
	    if (slot == null || slot == DELETED) continue;
	    String key = slot.sub0;
	    FnList<V> vals = slot.sub1;
	    // iterate through all values for this key
	    FnList<V> valList = vals;
	    while (!valList.nilq()) {
		work.accept(key, valList.hd());
		valList = valList.tl();
	    }
	}
    }
}
