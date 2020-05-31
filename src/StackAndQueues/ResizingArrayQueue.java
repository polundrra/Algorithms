package StackAndQueues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int head;
    private int tail;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(head + i) % q.length];
        }
        q = copy;
        head = 0;
        tail = n;
    }

    public void enqueue(Item item) {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[tail++] = item;
        if (tail == q.length) {
            tail = 0;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        Item item = q[head];
        q[head] = null;
        head++;
        n--;
        if (head == q.length) {
            head = 0;
        }
        if (n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[head];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < n;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + head) % q.length];
            i++;
            return item;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
        File tobe = new File("inputForStack.txt");
        Scanner sc = new Scanner(tobe);
        while (sc.hasNext()) {
            String item = sc.next();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) System.out.print(queue.dequeue() + " ");
        }
        System.out.println("(" + queue.size() + " left on queue)");
    }
}
