package StackAndQueues.linkedlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int n;

    public Queue() {
        head = null;
        tail = null;
        n = 0;
    }

    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        Node<Item> oldTail = tail;
        tail = new Node<Item>();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = head.item;
        head = head.next;
        n--;
        if (isEmpty()) {
            tail = null;
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return head.item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(' ');
        }
        return sb.toString();
    }

    // return Iterator instance
    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(head);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Queue<String> queue = new Queue<>();
        File tobe = new File("inputForStack.txt");
        Scanner sc = new Scanner(tobe);
        while (sc.hasNext()) {
            String item = sc.next();
            if (!item.equals("-")) {
                queue.enqueue(item);
                continue;
            }
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
        System.out.println(queue.size() + " item(s) left on queue: " + queue.toString());
    }
}
