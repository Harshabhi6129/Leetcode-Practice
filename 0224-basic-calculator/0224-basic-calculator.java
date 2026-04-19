import java.util.Stack;

class Solution {
    public int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1; // +1 or -1
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } 
            else if (c == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            } 
            else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            } 
            else if (c == '(') {
                // Save current state
                stack.push(result);
                stack.push(sign);

                result = 0;
                sign = 1;
            } 
            else if (c == ')') {
                result += sign * num;
                num = 0;

                // First pop sign, then previous result
                result *= stack.pop();   // sign
                result += stack.pop();   // previous result
            }
        }

        // Add last number
        if (num != 0) {
            result += sign * num;
        }

        return result;
    }
}