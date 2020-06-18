package StackAndQueues.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int last;

    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[last++] = item;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = (int) (StdRandom.uniform() * (n - 1));
        Item item = q[random];
        swap(random);
        n--;
        if (n == q.length / 4 && n != 0) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = (int) (StdRandom.uniform() * (n - 1));
        return q[random];
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        int j = 0;
        for (int i =0; i < q.length; i++) {
            if (q[i] != null) {
                copy[j++] = q[i];
            }
        }
        q = copy;
        last = n;
    }

    private void swap(int idx) {
        q[idx] = q[last - 1];
        q[last - 1] = null;
        last--;
    }

    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = 0;
        private final int[] idx = new int[n];

        public RandomArrayIterator() {
            for (int i = 0; i < n; i++) {
                idx[i] = i;
            }
            StdRandom.shuffle(idx);
        }

        public boolean hasNext() {
            return i < n;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[idx[i]];
            i++;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i);
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (Integer i: rq) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        for (Integer i: rq) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(rq.size());
        StdOut.println(rq.sample());
        for (Integer i: rq) {
            StdOut.print(i + " ");
        }
    }
}
