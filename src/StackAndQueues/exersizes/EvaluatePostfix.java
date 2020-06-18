package StackAndQueues.exersizes;

import StackAndQueues.linkedlist.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EvaluatePostfix {

    public static int evaluate(Scanner sc) {
        Stack<Integer> stack= new Stack<>();
        while (sc.hasNext()) {
            String s = sc.next();
            if (!s.equals("+") && !s.equals("*")) {
                stack.push(Integer.parseInt(s));
            }
            if (s.equals("+") && !stack.isEmpty()) {
                stack.push(stack.pop() + stack.pop());
            }
            if (s.equals("*") && !stack.isEmpty()) {
                stack.push(stack.pop() * stack.pop());
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("evaluate.txt"));
        System.out.println(evaluate(sc));
    }
}
