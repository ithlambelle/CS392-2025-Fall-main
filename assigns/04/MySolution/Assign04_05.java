/*
  HX-2025-09-30: 25 points
*/
public class Assign04_05 {
    //
    // HX-2025-09-30:
    // implement a method that uses higher-order methods to implement
    // a simple calculator that can evaluate expressions stored in a
    // functional list using postfix notation and a stack
    //
    public static int evaluatePostfix(FnList<String> expression) {
        MyStackList<Integer> stack = new MyStackList<>();
        
        expression.foritm(token -> {
            if (isNumber(token)) {
                stack.push$raw(Integer.parseInt(token));
            } else if (isOperator(token)) {
                if (stack.size() >= 2) {
                    int b = stack.pop$raw();
                    int a = stack.pop$raw();
                    int result = performOperation(a, b, token);
                    stack.push$raw(result);
                }
            }
        });
        
        return stack.isEmpty() ? 0 : stack.top$raw();
    }
    
    private static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
    
    private static int performOperation(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: return 0;
        }
    }

    public static void main(String[] argv) {
        // test simple addition
        FnList<String> add = new FnList<>("3", new FnList<>("4", new FnList<>("+", new FnList<>())));
        System.out.println("3 4 + = " + evaluatePostfix(add));
        
        // test simple multiplication
        FnList<String> mult = new FnList<>("5", new FnList<>("6", new FnList<>("*", new FnList<>())));
        System.out.println("5 6 * = " + evaluatePostfix(mult));
        
        // test complex expression: (3 + 4) * 2 = 14
        FnList<String> complex = new FnList<>("3", new FnList<>("4", new FnList<>("+", new FnList<>("2", new FnList<>("*", new FnList<>())))));
        System.out.println("(3 + 4) * 2 = " + evaluatePostfix(complex));
        
        // test division
        FnList<String> div = new FnList<>("8", new FnList<>("2", new FnList<>("/", new FnList<>())));
        System.out.println("8 2 / = " + evaluatePostfix(div));
        
        // test subtraction
        FnList<String> sub = new FnList<>("10", new FnList<>("3", new FnList<>("-", new FnList<>())));
        System.out.println("10 3 - = " + evaluatePostfix(sub));
        
        // test empty expression
        FnList<String> empty = new FnList<>();
        System.out.println("empty expression = " + evaluatePostfix(empty));
    }
}
