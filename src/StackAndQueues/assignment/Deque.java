package StackAndQueues.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    private static class Node<Item> {
        Node<Item> next;
        Node<Item> prev;
        Item item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if (!isEmpty()) {
            oldFirst.prev = first;
        } else {
            last = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.prev = oldLast;
        if (!isEmpty()) {
            oldLast.next = last;
        } else {
            first = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        if (size() > 1) {
            last.next = null;
        }
        n--;
        if (isEmpty()) {
            first = null;
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(3);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(4);
        for (Integer i: deque) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
        for (Integer i: deque) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.isEmpty());
    }
}
