package StackAndQueues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int n;


    public ResizingArrayStack() {
        s = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        s = Arrays.copyOf(s, capacity);
    }

    public void push(Item item) {
        if (n == s.length) {
            resize(s.length * 2);
        }
        s[n++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = s[n - 1];
        s[n - 1] = null;
        n--;
        if (n == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    public Item peak() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
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
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
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
