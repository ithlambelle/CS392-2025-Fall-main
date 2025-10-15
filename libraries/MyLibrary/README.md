# MyLibrary - CS392 Functional Programming Library

This library contains all the essential components needed for CS392 assignments and take-home exams. It provides a comprehensive set of functional programming tools built on immutable data structures.

## 📁 Directory Structure

```
MyLibrary/
├── FnList/           # Functional List implementation
│   ├── FnList.java   # Core FnList class
│   ├── FnListUtil.java # Utility functions
│   └── FnListTest.java # Test suite
├── MyStack/          # Stack implementations
│   ├── MyStack.java  # Stack interface
│   ├── MyStackArray.java # Array-based stack
│   ├── MyStackList.java # List-based stack
│   └── MyStackBase.java # Base implementation
├── MyQueue/          # Queue implementations
│   ├── MyQueue.java  # Queue interface
│   ├── MyQueueArray.java # Array-based queue
│   ├── MyQueueList.java # List-based queue
│   └── MyQueueBase.java # Base implementation
└── ExamExamples.java # Comprehensive examples for exams
```

## 🚀 Quick Start

### Compilation
```bash
# Compile all library components
javac -cp . FnList/*.java MyStack/*.java MyQueue/*.java ExamExamples.java

# Run examples
java -cp . ExamExamples

# Run tests
java -cp . FnListTest
```

### Basic Usage
```java
import FnList.*;
import MyStack.*;
import MyQueue.*;

// Create a functional list
FnList<Integer> numbers = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>())));

// Map operation
FnList<Integer> doubled = FnListUtil.map_list(numbers, x -> x * 2);

// Filter operation
FnList<Integer> evens = FnListUtil.filter(numbers, x -> x % 2 == 0);

// Fold operation
Integer sum = FnListUtil.folditm(numbers, 0, (acc, x) -> acc + x);
```

## 📚 Core Components

### FnList - Functional List
Immutable functional list with comprehensive utility functions.

**Key Methods:**
- `hd()` - Get head element
- `tl()` - Get tail (rest of list)
- `nilq()` - Check if empty
- `consq()` - Check if non-empty
- `length()` - Get list length
- `reverse()` - Reverse the list

**Utility Functions:**
- `map_list()` - Transform each element
- `filter()` - Keep elements matching predicate
- `folditm()` - Reduce list to single value
- `foritm()` - Iterate over elements
- `iforitm()` - Iterate with indices
- `take()` - Get first n elements
- `drop()` - Skip first n elements
- `nth()` - Get element at index
- `append()` - Concatenate lists
- `zipWith()` - Combine two lists

### MyStack - Stack Interface
Generic stack interface with multiple implementations.

**Methods:**
- `push$exn()` - Push element (throws exception if full)
- `push$raw()` - Push element (unsafe)
- `pop$exn()` - Pop element (throws exception if empty)
- `pop$raw()` - Pop element (unsafe)
- `top$exn()` - Peek at top element
- `isEmpty()` - Check if empty
- `isFull()` - Check if full
- `size()` - Get current size

**Implementations:**
- `MyStackArray` - Fixed-size array-based
- `MyStackList` - Dynamic list-based

### MyQueue - Queue Interface
Generic queue interface with multiple implementations.

**Methods:**
- `enque$exn()` - Enqueue element (throws exception if full)
- `enque$raw()` - Enqueue element (unsafe)
- `deque$exn()` - Dequeue element (throws exception if empty)
- `deque$raw()` - Dequeue element (unsafe)
- `top$exn()` - Peek at front element
- `isEmpty()` - Check if empty
- `isFull()` - Check if full
- `size()` - Get current size

**Implementations:**
- `MyQueueArray` - Fixed-size array-based
- `MyQueueList` - Dynamic list-based

## 🎯 Common Exam Patterns

### 1. Map-Filter-Reduce
```java
// Square all even numbers and sum them
FnList<Integer> result = FnListUtil.folditm(
    FnListUtil.filter(
        FnListUtil.map_list(numbers, x -> x * x),
        x -> x % 2 == 0
    ),
    0,
    (acc, x) -> acc + x
);
```

### 2. Stack-based Algorithms
```java
// Balanced parentheses checker
public static boolean isBalanced(String text) {
    MyStackList<Character> stack = new MyStackList<>();
    // Implementation using push$exn and pop$exn
}
```

### 3. Queue-based Algorithms
```java
// BFS traversal
public static void bfs(FnList<Integer> nodes) {
    MyQueueList<Integer> queue = new MyQueueList<>();
    // Implementation using enque$raw and deque$raw
}
```

### 4. List Processing with Indices
```java
// Process elements with their indices
FnList<String> indexed = FnListUtil.imap_list(items, 
    (i, item) -> "[" + i + "] " + item);
```

## 🔧 Error Handling

### Stack/Queue Exceptions
- `MyStackEmptyExn` - Thrown when popping from empty stack
- `MyStackFullExn` - Thrown when pushing to full stack
- `MyQueueEmptyExn` - Thrown when dequeuing from empty queue
- `MyQueueFullExn` - Thrown when enqueuing to full queue

### Safe vs Unsafe Methods
- `$exn` methods throw exceptions for error conditions
- `$raw` methods are unsafe and may cause undefined behavior
- Always use `$exn` methods in production code

## 📖 Best Practices

1. **Use immutable data structures** - FnList is immutable, always create new lists
2. **Prefer functional style** - Use map, filter, fold instead of loops
3. **Handle exceptions properly** - Use `$exn` methods with try-catch
4. **Test edge cases** - Empty lists, single elements, large datasets
5. **Use type safety** - Leverage Java generics for type safety

## 🧪 Testing

Run the comprehensive test suite:
```bash
java -cp . FnListTest
```

The test suite covers:
- Basic operations
- Utility functions
- Higher-order functions
- Edge cases
- Performance with large datasets

## 📝 Exam Tips

1. **Start with simple cases** - Empty list, single element
2. **Use helper methods** - Break complex problems into smaller functions
3. **Test thoroughly** - Verify with multiple test cases
4. **Consider efficiency** - Some operations are O(n), others O(1)
5. **Read the assignment carefully** - Pay attention to method signatures and requirements

## 🔗 Related Files

- `assigns/03/` - Stack and Queue assignments
- `assigns/04/` - Functional programming assignments
- `assigns/05/` - Sorting algorithm assignments

This library provides everything you need to succeed in CS392 assignments and exams!
