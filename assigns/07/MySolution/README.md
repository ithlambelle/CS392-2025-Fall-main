# Assignment 7 Solutions

## Assignment 7-1: BFirstEnumerate and DFirstEnumerate

### Implementation Details

Both `BFirstEnumerate` and `DFirstEnumerate` use `MyDequeList` from the Library to traverse trees.

**BFirstEnumerate (Breadth-First Search):**
- Uses `MyDequeList` as a queue
- Enqueues to rear: `renque$exn()` 
- Dequeues from front: `fdeque$raw()`
- This simulates FIFO queue behavior for level-order traversal

**DFirstEnumerate (Depth-First Search):**
- Uses `MyDequeList` as a stack
- Enqueues to front: `fenque$exn()`
- Dequeues from front: `fdeque$raw()`
- This simulates LIFO stack behavior for depth-first traversal
- Uses `rforitm()` to add children in reverse order to maintain correct DFS order

### Changes to MyDequeList

The following methods were implemented in `libraries/Library/MyDeque/MyDequeList.java`:

1. **`fdeque$raw()`**: Removes and returns element from front
   - Updates `frnt` pointer to next node
   - Handles empty deque case
   - Updates `rear` if deque becomes empty

2. **`rdeque$raw()`**: Removes and returns element from rear
   - Updates `rear` pointer to previous node
   - Handles empty deque case
   - Updates `frnt` if deque becomes empty

3. **`fenque$raw(T itm)`**: Adds element to front
   - Creates new node with `prev=null, next=frnt`
   - Updates `frnt.prev` if list not empty
   - Handles empty deque case

4. **`renque$raw(T itm)`**: Adds element to rear
   - Creates new node with `prev=rear, next=null`
   - Updates `rear.next` if list not empty
   - Handles empty deque case

## Assignment 7-2: Game-of-24 Solver

### High-Level Description

Game-of-24 is solved by building a search tree where:
- Each node represents a state containing a list of terms (numbers or expressions)
- The root contains 4 initial numbers
- Each level combines two terms with an operator (+, -, *, /)
- Leaf nodes contain a single term that can be evaluated
- Solutions are terms that evaluate to 24

**BFS Approach:**
- Uses `BFirstEnumerate` to explore all states level by level
- Finds solutions by exploring all combinations at each depth before going deeper
- Guarantees finding shortest solution paths first

**DFS Approach:**
- Uses `DFirstEnumerate` to explore states depth-first
- Explores one complete path to a solution before backtracking
- May find solutions faster for some inputs but explores deeper paths first

### Implementation Details

1. **GameState Class**: Implements `FnGtree<Term>`
   - `value()`: Returns the term if state has 1 term (leaf), null otherwise
   - `children()`: Generates all possible next states by:
     - Selecting all pairs of terms
     - Applying all 4 operators to each pair
     - Creating new states with the combined term and remaining terms
     - Handling division by zero

2. **Filtering Solutions**: 
   - Both BFS and DFS methods filter the stream of terms
   - Only terms evaluating to 24 (within floating-point tolerance) are returned

3. **Testing**:
   - Test code included in `main()` method
   - Tests with input (1, 2, 3, 4) which has known solutions
   - Prints count and values of solutions found

### Notes

- Division by zero is prevented by checking if divisor evaluates to near-zero
- Floating-point comparison uses tolerance (1e-10) for equality with 24
- Both commutative and non-commutative operator orderings are explored
- The tree structure allows lazy evaluation through streams





