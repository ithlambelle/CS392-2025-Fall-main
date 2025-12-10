# MyLibrary - CS392 Functional Toolkit

This is a package-free library you can drop into the final exam workspace. It keeps everything self-contained so you do not need Java collection classes beyond `java.util.function` interfaces.

## Layout
```
MyLibrary/
├── FnList/        # Immutable functional list and utilities (map/filter/fold/sorts)
├── LnStrm/        # Lazy streams for on-demand traversal
├── MyStack/       # Array- and list-based stacks
├── MyQueue/       # Array- and list-based queues
├── MyDeque/       # Double-ended queue used in BFS/DFS patterns
├── FnListTest.java
└── ExamExamples.java
```

## Quick start
- Build everything: `make compile`
- Run FnList smoke tests: `make test-fnlist`
- Run examples: `make test-examples`

## Highlights
- `FnList` supports map/filter/fold, indexed iteration, slicing (`take`/`drop`), concatenation, and sorting helpers (`insertSort`, `mergeSort`, `quickSort`, `orderedq`).
- `LnStrm` + `LnStcn` enable lazy enumerations for traversal-heavy problems (BFS/DFS).
- `MyStack`, `MyQueue`, `MyDeque` expose `$raw`, `$opt`, and `$exn` variants so you can pick between performance and safety without relying on `java.util` collections.
- All structures stay in the default package for painless compilation during timed exams.

## 🔗 Related Files

- `assigns/03/` - Stack and Queue assignments
- `assigns/04/` - Functional programming assignments
- `assigns/05/` - Sorting algorithm assignments

This library provides everything you need to succeed in CS392 assignments and exams!
