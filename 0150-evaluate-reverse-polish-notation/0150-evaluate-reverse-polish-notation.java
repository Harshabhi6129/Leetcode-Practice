import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();

                if (token.equals("+")) stack.push(a + b);
                else if (token.equals("-")) stack.push(a - b);
                else if (token.equals("*")) stack.push(a * b);
                else stack.push(a / b); // division truncates toward zero
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || 
               s.equals("*") || s.equals("/");
    }
}