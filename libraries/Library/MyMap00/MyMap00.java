package Library.MyMap00;

import Library.FnList.*;
import Library.LnStrm.*;
import Library.FnTuple.*;

import java.util.function.BiConsumer;

interface MyMap00<K,V> {
//
    int size();
//
    boolean isFull(); // checks for fullness
    boolean isEmpty(); // checks for emptiness
//
    LnStrm<FnTupl2<K,FnList<V>>> strmize();
//
    FnList<V> remove$raw(K key); // HX: [key] is assumed to be in the map
    FnList<V> remove$exn(K key); // HX: exception if [key] is not in the map
    FnList<V> remove$opt(K key); // HX: return null if [key] is not in the map
//
    void insert$raw(K key, V val); // HX: insertion is assumed to work
    void insert$exn(K key, V val); // HX: exception if no insertion is done
    boolean insert$opt(K key, V val); // HX: return false if no insertion is done
//
    void foritm(BiConsumer<? super K, ? super V> work);
//
} // end of [interface MyMap00<T>{...}]
