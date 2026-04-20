package com.dsa.week1.quickfindunion.lecture;

public class QuickFindEagerApproach {
    private int[] id;

    public QuickFindEagerApproach(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            System.out.println(i);
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        if (id[p] != id[q]) {
            int pId = id[p];
            for (int i = 0; i < id.length; i++) {
                if (id[i] == id[q]) {
                    id[i] = pId;
                }
            }
        }
    }



}
class QuickUFCLient{
    public static void main(String[] args) {
        QuickFindEagerApproach qf = new QuickFindEagerApproach(10);
        System.out.println(qf.connected(1,2));
        qf.union(1,2);
        System.out.println(qf.connected(1,2));
    }
}
