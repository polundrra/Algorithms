package StackAndQueues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FixedCapacityStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int n;

    public FixedCapacityStack (int capacity) {
        s = (Item[]) new Object[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == s.length;
    }

    public void push(Item item) {
        s[n++] = item;
    }

    public Item pop() {
        Item item = s[n - 1];
        s[n - 1] = null;
        n--;
        return item;
    }

    public Item peak() {
        return s[n - 1];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }


    public class ReverseArrayIterator implements Iterator<Item> {
        private int i = n - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(10);
        File tobe = new File("inputForStack.txt");
        Scanner sc = new Scanner(tobe);
        while (sc.hasNext()) {
            String next = sc.next();
            if (!next.equals("-")) {
                stack.push(next);
            } else if (stack.isEmpty()) {
                System.out.println("Bad input");
            } else {
                System.out.print(stack.pop() + " ");
            }
        }
        System.out.println();
        System.out.print("Left on stack: ");
        for (String s: stack) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
