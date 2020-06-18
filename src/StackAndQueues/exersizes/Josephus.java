package StackAndQueues.exersizes;

import StackAndQueues.linkedlist.Queue;

public class Josephus {

    public static void josephus(int N, int M) {
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(i);
        }
        while (q.size() != 1) {
            for (int i = 0; i < M - 1; i++) {
                int rest = q.dequeue();
                q.enqueue(rest);
            }
            System.out.print(q.dequeue() + " ");
        }
        System.out.print("Left on queue: ");
        for (Integer i: q) {
            System.out.print(i);
        }
    }

    public static void main(String[] args) {
        josephus(7, 2);

    }
}
