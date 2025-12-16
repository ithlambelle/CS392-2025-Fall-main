# MyFinalLib

Library code for CS392 final exam. Everything here is in the default package so it compiles easily.

## What's in here

- **FnList/** - functional lists with map/filter/fold, sorting (insert, merge, quick)
- **FnTuple/** - tuples (FnTupl2, FnTupl3) used in assignments 6, 8, 9
- **FnGtree/** - tree interface and utilities for BFS/DFS traversal
- **LnStrm/** - lazy streams for BFS/DFS traversal
- **LnList/** - mutable lists used in hash table implementation
- **MyStack/** - stack implementations (array and list based)
- **MyQueue/** - queue implementations (array and list based)  
- **MyDeque/** - double-ended queue, used for BFS/DFS in assign07
- **MyMap00/** - hash table interface and implementations
  - MyMap00SeparateChaining - separate chaining (from assign08_01)
  - MyMap00QuadraticProbing - quadratic probing (from assign08_02)
- **MyPQueue/** - priority queue implementation (from assign09)
- **Algorithms.pdf** - algorithm reference from class
- **ExamExamples.java** - some example code patterns
- **FnListTest.java** - quick test file

## Notes

- All code uses default package (no package declarations)
- MyStack, MyQueue, MyDeque, MyPQueue have $raw, $opt, $exn variants
- FnList has sorting helpers: insertSort, mergeSort, quickSort
- LnStrm useful for tree traversal problems
- Hash tables are my own implementations from assignment 8
- Priority queue is my own implementation from assignment 9
- Used in assignments 03, 04, 05, 06, 07, 08, 09

## Compilation

From MySolution directory:
```
javac -cp .:./../MyFinalLib ...
```
