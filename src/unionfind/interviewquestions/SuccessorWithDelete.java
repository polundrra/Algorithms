package unionfind.interviewquestions;

public class SuccessorWithDelete {
    UFWithFindLargest uf;


    public SuccessorWithDelete(int n) {
        UFWithFindLargest uf = new UFWithFindLargest(n);
        this.uf = uf;
    }

    public void delete(int p) {
        uf.union(p, p + 1);
    }

    public int successor(int p) {
        int n = uf.parent.length;
        if (p < 0 || p >= n - 1) {
            throw new IllegalArgumentException("Value p must be between [0, n - 1)]");
        }
        return uf.find(p);
    }

    public static void main(String[] args){
        SuccessorWithDelete suc = new SuccessorWithDelete(10);
        int[] del = {2, 4, 3, 6, 7};
        for (int i = 0; i < del.length; i ++) {
            suc.delete(del[i]);
        }
        System.out.println(suc.successor(2));
        System.out.println(suc.successor(4));
        System.out.println(suc.successor(6));
    }
}
