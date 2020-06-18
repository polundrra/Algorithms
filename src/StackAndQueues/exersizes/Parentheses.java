package StackAndQueues.exersizes;

import StackAndQueues.linkedlist.Stack;

import java.util.Scanner;

public class Parentheses {
    char openRound = '(';
    char closeRound = ')';
    char openCurly = '{';
    char closeCurly = '}';
    char openSquare = '[';
    char closeSquare = ']';

    public boolean isOpen(char p) {
        return p == openRound || p == openCurly || p == openSquare;
    }

    public boolean isMatch(char open, char close) {
        return open == openRound && close == closeRound
                || open == openCurly && close == closeCurly
                || open == openSquare && close == closeSquare;
    }

    public boolean isValid(String p) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < p.length(); i++) {
            if (isOpen(p.charAt(i))) {
                stack.push(p.charAt(i));
            } else if (!stack.isEmpty()) {
                if (isMatch(stack.peek(), p.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Parentheses par = new Parentheses();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter parentheses: ");
        System.out.println();
        String p = sc.next();
        boolean test = par.isValid(p);
        System.out.println(test);
    }
}
