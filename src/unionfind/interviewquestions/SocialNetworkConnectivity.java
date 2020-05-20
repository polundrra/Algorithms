/*
Given a social network containing nn members and a log.txt file containing m timestamps at which times pairs of members
formed friendships, design an algorithm to determine the earliest time at which all members are connected
(i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log.txt file is sorted by
timestamp and that friendship is an equivalence relation. The running time of your algorithm should be m log.txt(n)
or better and use extra space proportional to n.
*/

package unionfind.interviewquestions;

import unionfind.WeightedQuickUnionUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SocialNetworkConnectivity {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("log.txt"));
        int n = sc.nextInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            String date = sc.next();
            String time = sc.next();
            uf.union(p, q);
            System.out.println(p + "+" + q);
            if (uf.count() == 1) {
                System.out.println("All members were connected at: " + date + " " + time);
                break;
            }
        }

    }
}
