// MyStackList is in the same directory

public class Assign03_02 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of MyStack!
	//
        MyStackList<Character> stack = new MyStackList<Character>();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            // if it's an opening bracket, push it
            if (c == '(' || c == '[' || c == '{') {
                try {
                    stack.push$exn(c);
                } catch (Exception e) {
                    return false; // stack is full, can't balance
                }
            }
            // if it's a closing bracket, check if it matches
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false; // no opening bracket to match
                }
                
                char top = stack.pop$raw();
                
                // check if the brackets match
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false; // brackets don't match
                }
            }
        }
        
        // if stack is empty, all brackets were matched
        return stack.isEmpty();
    }

    public static void main(String[] argv) {
	// Please write some testing code for your 'balencedq"
        // test cases for balanced parentheses
        String[] testCases = {
            "({()[({})]})",  // balanced
            "({()[({})])}",  // not balanced
            "()",            // balanced
            "()[]{}",        // balanced
            "([)]",          // not balanced
            "((()))",        // balanced
            "((())",         // not balanced
            "",              // balanced (empty)
            "abc",           // balanced (no brackets)
            "{[()]}",        // balanced
            "{[()]}"         // balanced
        };
        
        boolean[] expected = {
            true, false, true, true, false, true, false, true, true, true, true
        };
        
        System.out.println("testing parenthesis balance checker:");
        for (int i = 0; i < testCases.length; i++) {
            boolean result = balencedq(testCases[i]);
            String status = (result == expected[i]) ? "✓" : "✗";
            System.out.println(status + " \"" + testCases[i] + "\" -> " + result + " (expected: " + expected[i] + ")");
        }
        
        // additional edge cases
        System.out.println("\nadditional tests:");
        System.out.println("single opening: " + balencedq("(") + " (should be false)");
        System.out.println("single closing: " + balencedq(")") + " (should be false)");
        System.out.println("nested: " + balencedq("((()))") + " (should be true)");
        System.out.println("mixed: " + balencedq("{[()]}") + " (should be true)");
    }
}
