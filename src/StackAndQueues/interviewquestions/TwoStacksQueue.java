package StackAndQueues.interviewquestions;

import StackAndQueues.linkedlist.Stack;

import java.util.NoSuchElementException;

public class TwoStacksQueue<Item> {
    Stack<Item> stackN;
    Stack<Item> stackD;

    public TwoStacksQueue() {
        stackN = new Stack<>();
        stackD = new Stack<>();
    }

    public void enqueue(Item item) {
        stackN.push(item);
    }

    public Item dequeue() {
        if (stackD.isEmpty() && stackN.isEmpty()) {
            throw new NoSuchElementException("Error");
        }
        if (stackD.isEmpty()) {
            while (!stackN.isEmpty()) {
                stackD.push(stackN.pop());
            }
            return stackD.pop();
        }
        return stackD.pop();
    }

    public static void main(String[] args) {
        /* Create a queue with items 1 2 3*/
        TwoStacksQueue<Integer> q = new TwoStacksQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        /* Dequeue items */
        System.out.print(q.dequeue() + " ");
        System.out.print(q.dequeue() + " ");
        System.out.print(q.dequeue() + " ");
    }
}

