package StackAndQueues.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int num = 0;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
            num++;
        }
        for (int i = 0; i < num - k; i++) {
            rq.dequeue();
        }
        for (String s: rq) {
            StdOut.println(s);
        }
    }
}
