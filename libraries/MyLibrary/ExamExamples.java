import FnList.*;
import MyStack.*;
import MyQueue.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;

/**
 * Comprehensive examples for CS392 take-home exams
 * This file demonstrates common patterns and techniques
 * that are frequently used in functional programming assignments.
 */
public class ExamExamples {
    
    public static void main(String[] args) {
        System.out.println("=== CS392 Exam Examples ===");
        
        // Functional Programming with FnList
        demonstrateFnListPatterns();
        
        // Stack-based algorithms
        demonstrateStackAlgorithms();
        
        // Queue-based algorithms
        demonstrateQueueAlgorithms();
        
        // Complex data structure operations
        demonstrateComplexOperations();
        
        System.out.println("\n=== Examples completed ===");
    }
    
    // ===== FUNCTIONAL PROGRAMMING PATTERNS =====
    
    private static void demonstrateFnListPatterns() {
        System.out.println("\n--- Functional Programming Patterns ---");
        
        // Pattern 1: Map-Filter-Reduce
        FnList<Integer> numbers = new FnList<>(1, new FnList<>(2, new FnList<>(3, 
            new FnList<>(4, new FnList<>(5, new FnList<>())))));
        
        // Map: square each number
        FnList<Integer> squares = FnListUtil.map_list(numbers, x -> x * x);
        System.out.print("Squares: ");
        squares.System$out$print();
        System.out.println();
        
        // Filter: keep only even numbers
        FnList<Integer> evens = FnListUtil.filter(squares, x -> x % 2 == 0);
        System.out.print("Even squares: ");
        evens.System$out$print();
        System.out.println();
        
        // Reduce: sum all numbers
        Integer sum = FnListUtil.folditm(evens, 0, (acc, x) -> acc + x);
        System.out.println("Sum of even squares: " + sum);
        
        // Pattern 2: List processing with indices
        FnList<String> items = new FnList<>("apple", new FnList<>("banana", 
            new FnList<>("cherry", new FnList<>("date", new FnList<>()))));
        
        FnList<String> indexed = FnListUtil.imap_list(items, (i, item) -> 
            "[" + i + "] " + item);
        System.out.print("Indexed items: ");
        indexed.System$out$print();
        System.out.println();
        
        // Pattern 3: List comprehension simulation
        FnList<Integer> range = FnListUtil.list_make_int1(10);
        FnList<Integer> filtered = FnListUtil.filter(range, x -> x % 2 == 1);
        FnList<Integer> transformed = FnListUtil.map_list(filtered, x -> x * x);
        System.out.print("Odd squares from 1-10: ");
        transformed.System$out$print();
        System.out.println();
    }
    
    // ===== STACK ALGORITHMS =====
    
    private static void demonstrateStackAlgorithms() {
        System.out.println("\n--- Stack Algorithms ---");
        
        // Algorithm 1: Balanced parentheses (from Assign03_02)
        String[] expressions = {"()", "({})", "([)]", "((()))", "{[()]}"};
        for (String expr : expressions) {
            boolean balanced = isBalanced(expr);
            System.out.println("'" + expr + "' is balanced: " + balanced);
        }
        
        // Algorithm 2: Expression evaluation (postfix)
        String postfix = "3 4 + 2 * 7 /";
        try {
            int result = evaluatePostfix(postfix);
            System.out.println("Postfix '" + postfix + "' = " + result);
        } catch (Exception e) {
            System.out.println("Error evaluating postfix: " + e.getMessage());
        }
        
        // Algorithm 3: Stack-based list reversal
        FnList<Integer> original = new FnList<>(1, new FnList<>(2, 
            new FnList<>(3, new FnList<>())));
        FnList<Integer> reversed = reverseUsingStack(original);
        System.out.print("Original: ");
        original.System$out$print();
        System.out.print(" Reversed: ");
        reversed.System$out$print();
        System.out.println();
    }
    
    // ===== QUEUE ALGORITHMS =====
    
    private static void demonstrateQueueAlgorithms() {
        System.out.println("\n--- Queue Algorithms ---");
        
        // Algorithm 1: BFS simulation
        FnList<Integer> nodes = new FnList<>(1, new FnList<>(2, 
            new FnList<>(3, new FnList<>(4, new FnList<>()))));
        
        System.out.print("BFS traversal: ");
        bfsTraversal(nodes);
        System.out.println();
        
        // Algorithm 2: Queue-based list reversal
        FnList<Integer> original = new FnList<>(1, new FnList<>(2, 
            new FnList<>(3, new FnList<>())));
        FnList<Integer> reversed = reverseUsingQueue(original);
        System.out.print("Original: ");
        original.System$out$print();
        System.out.print(" Reversed: ");
        reversed.System$out$print();
        System.out.println();
    }
    
    // ===== COMPLEX OPERATIONS =====
    
    private static void demonstrateComplexOperations() {
        System.out.println("\n--- Complex Operations ---");
        
        // Operation 1: Nested list processing
        FnList<FnList<Integer>> nested = new FnList<>(
            new FnList<>(1, new FnList<>(2, new FnList<>())),
            new FnList<>(3, new FnList<>(4, new FnList<>())),
            new FnList<>(5, new FnList<>(6, new FnList<>()))
        );
        
        FnList<Integer> flattened = FnListUtil.flatten(nested);
        System.out.print("Flattened nested list: ");
        flattened.System$out$print();
        System.out.println();
        
        // Operation 2: List partitioning
        FnList<Integer> numbers = FnListUtil.list_make_int1(10);
        FnList<Integer> odds = FnListUtil.filter(numbers, x -> x % 2 == 1);
        FnList<Integer> evens = FnListUtil.filter(numbers, x -> x % 2 == 0);
        
        System.out.print("Odds: ");
        odds.System$out$print();
        System.out.print(" Evens: ");
        evens.System$out$print();
        System.out.println();
        
        // Operation 3: List zipping and transformation
        FnList<Integer> list1 = new FnList<>(1, new FnList<>(2, new FnList<>(3, new FnList<>())));
        FnList<Integer> list2 = new FnList<>(10, new FnList<>(20, new FnList<>(30, new FnList<>())));
        
        FnList<Integer> zipped = FnListUtil.zipWith(list1, list2, (x, y) -> x + y);
        System.out.print("Zipped sum: ");
        zipped.System$out$print();
        System.out.println();
    }
    
    // ===== HELPER METHODS =====
    
    private static boolean isBalanced(String text) {
        MyStackList<Character> stack = new MyStackList<>();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            if (c == '(' || c == '[' || c == '{') {
                try {
                    stack.push$exn(c);
                } catch (Exception e) {
                    return false;
                }
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop$raw();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    private static int evaluatePostfix(String expression) throws Exception {
        MyStackList<Integer> stack = new MyStackList<>();
        String[] tokens = expression.split("\\s+");
        
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push$exn(Integer.parseInt(token));
            } else {
                if (stack.size() < 2) throw new Exception("Invalid expression");
                
                int b = stack.pop$raw();
                int a = stack.pop$raw();
                
                switch (token) {
                    case "+": stack.push$exn(a + b); break;
                    case "-": stack.push$exn(a - b); break;
                    case "*": stack.push$exn(a * b); break;
                    case "/": stack.push$exn(a / b); break;
                    default: throw new Exception("Unknown operator: " + token);
                }
            }
        }
        
        if (stack.size() != 1) throw new Exception("Invalid expression");
        return stack.pop$raw();
    }
    
    private static FnList<Integer> reverseUsingStack(FnList<Integer> xs) {
        MyStackList<Integer> stack = new MyStackList<>();
        
        // Push all elements onto stack
        while (!xs.nilq()) {
            try {
                stack.push$exn(xs.hd());
            } catch (Exception e) {
                break; // Stack full
            }
            xs = xs.tl();
        }
        
        // Pop all elements to create reversed list
        FnList<Integer> result = new FnList<>();
        while (!stack.isEmpty()) {
            result = new FnList<>(stack.pop$raw(), result);
        }
        
        return result;
    }
    
    private static void bfsTraversal(FnList<Integer> nodes) {
        MyQueueList<Integer> queue = new MyQueueList<>();
        
        // Enqueue all nodes
        while (!nodes.nilq()) {
            queue.enque$raw(nodes.hd());
            nodes = nodes.tl();
        }
        
        // Process queue
        while (!queue.isEmpty()) {
            System.out.print(queue.deque$raw() + " ");
        }
    }
    
    private static FnList<Integer> reverseUsingQueue(FnList<Integer> xs) {
        MyQueueList<Integer> queue = new MyQueueList<>();
        
        // Enqueue all elements
        while (!xs.nilq()) {
            queue.enque$raw(xs.hd());
            xs = xs.tl();
        }
        
        // Dequeue all elements to create reversed list
        FnList<Integer> result = new FnList<>();
        while (!queue.isEmpty()) {
            result = new FnList<>(queue.deque$raw(), result);
        }
        
        return result;
    }
}
