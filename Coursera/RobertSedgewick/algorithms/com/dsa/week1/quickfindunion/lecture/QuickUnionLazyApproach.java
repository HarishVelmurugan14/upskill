package com.dsa.week1.quickfindunion.lecture;
public class QuickUnionLazyApproach {

    private int id[];

    public QuickUnionLazyApproach(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            System.out.println(i);
        }
    }

    public void union(int p, int q) {
        int rootOfP = root(p);
        int rootOfQ = root(q);
        id[rootOfP] = rootOfQ;
    }

    public int root(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public int[] getId() {
        return id;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}

class QuickUnionCLient {
    public static void main(String[] args) {
        QuickUnionLazyApproach qf = new QuickUnionLazyApproach(10);
        //  System.out.println(qf.connected(1,2));
        qf.union(1, 6);
        qf.union(5, 6);
        qf.union(4, 3);
        qf.union(3, 2);
        qf.union(5, 3);

        int count = 0;
        for (int idValue : qf.getId()) {
            System.out.println(count + "->" + idValue);
            count++;
        }

          System.out.println(qf.connected(7,2));
    }
}