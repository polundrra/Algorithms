package StackAndQueues.interviewquestions;

import StackAndQueues.linkedlist.Stack;

import java.util.NoSuchElementException;

public class StackWithMax {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> trackStack = new Stack<>();

    public void push(int i) {
        if (stack.isEmpty()) {
            stack.push(i);
            trackStack.push(i);
        }
        if (trackStack.peek() < i) {
            stack.push(i);
            trackStack.push(i);
        } else {
            stack.push(i);
            trackStack.push(trackStack.peek());
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        trackStack.pop();
        return stack.pop();
    }

    public int getMax() {
        return trackStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax s = new StackWithMax();
        s.push(20);
        System.out.println(s.getMax());
        s.push(10);
        System.out.println(s.getMax());
        s.push(50);
        System.out.println(s.getMax());
    }
}
