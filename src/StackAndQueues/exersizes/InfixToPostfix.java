package StackAndQueues.exersizes;

import StackAndQueues.linkedlist.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InfixToPostfix {

    public static String infixToPostfix() throws FileNotFoundException {
        Stack<String> ops = new Stack<>();
        Scanner sc = new Scanner(new File("Expression.txt"));
        StringBuilder postfix = new StringBuilder();
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                ops.push(s);
            } else if (!s.equals("(") && !s.equals(")")) {
                postfix.append(s);
            } else if (s.equals(")")) {
                postfix.append(ops.pop());
            }
            /*String s = sc.next();
            if (s.equals("(")) {
                while (!sc.next().equals(")")) {
                    s = sc.next();
                    if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                        ops.push(s);
                    }
                    postfix.append(s);
                }
                postfix.append(ops.pop());
            } else if ((s.equals("+") || s.equals("-"))) {
                if (ops.isEmpty()) {
                    ops.push(s);
                }
                postfix.append(ops.peek()).append(s);
            } else if (s.equals("*") || s.equals("/")) {
                if (ops.isEmpty()) {
                    ops.push(s);
                } else if (ops.peek().equals("+") || ops.peek().equals("-")) {
                    postfix.append(s).append(ops.peek());
                }
                postfix.append(ops.peek()).append(s);
            } else if (!s.equals(")")) {
                postfix.append(s);
            }*/
        }
        return postfix.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String result = infixToPostfix();
        System.out.println(result);
    }
}
