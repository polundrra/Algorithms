package StackAndQueues;

import java.util.Stack;

public class Main {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
                continue;
            }
            if (s.charAt(i) == ')') {
                if (stack.empty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }

    //(()(())) ))(())
    public static void main(String[] args) {
        String s = "))(())";
        String s1 = "((f)(()))";
        String s2 = "((()(()))";
        boolean valid = isValid(s);
        System.out.println(valid);
        boolean valid1 = isValid(s1);
        System.out.println(valid1);
        boolean valid2 = isValid(s2);
        System.out.println(valid2);
    }
}
