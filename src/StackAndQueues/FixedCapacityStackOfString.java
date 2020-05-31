package StackAndQueues;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStackOfString implements Iterable<String> {
    private String[] s;
    private int n;

    public FixedCapacityStackOfString(int capacity) {
        s = new String[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return n == s.length;
    }

    public void push(String string) {
        s[n++] = string;
    }

    public String pop() {
        String string = s[n - 1];
        s[n - 1] = null;
        n--;
        return string;
    }

    public String peak() {
        return s[n - 1];
    }

    @Override
    public Iterator<String> iterator() {
        return new ReverseArrayIterator();
    }


    public class ReverseArrayIterator implements Iterator<String> {
        private int i = n - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[i--];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FixedCapacityStackOfString stack = new FixedCapacityStackOfString(10);
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
