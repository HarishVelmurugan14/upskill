package com.dsa.week1.quickfindunion.lecture;

public class WeightedQuickUnionWithPathCompressionApproach {


    private int id[];
    private int size[];

    public WeightedQuickUnionWithPathCompressionApproach(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public void alterAllValues(int toChange, int withValue) {
        int count = 0;
        for (int idVal : id) {
            if (idVal == toChange) {
                id[count] = withValue;
            }
            count++;
        }
    }

    public void findWeight(int rootOfP, int rootOfQ) {
        if (size[rootOfP] > size[rootOfQ]) {
            System.out.println("P -> " + rootOfP);
            System.out.println("Q -> " + rootOfQ);
            alterAllValues(rootOfQ, rootOfP);
            //id[rootOfQ] = rootOfP;
            size[rootOfP] = size[rootOfP] + size[rootOfQ];
        } else {
            System.out.println("Q -> " + rootOfQ);
            System.out.println("P -> " + rootOfP);
            alterAllValues(rootOfP, rootOfQ);
            //id[rootOfP] = rootOfQ;
            size[rootOfQ] = size[rootOfQ] + size[rootOfP];
        }
    }

    public void union(int p, int q) {
        findWeight(root(p), root(q));
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


    public void printCheck() {
        int count = 0;
        for (int idValue : getId()) {
            System.out.print(count + " | ");
            count++;
        }
        System.out.println();
        count = 0;
        for (int idValue : getId()) {
            System.out.print(idValue + " | ");
            count++;
        }
        System.out.println();
        System.out.println();
    }
}

class WeightedPathCompQuickUnionClient {
    public static void main(String[] args) {
        WeightedQuickUnionWithPathCompressionApproach qf = new WeightedQuickUnionWithPathCompressionApproach(10);
        System.out.println(System.currentTimeMillis());
        qf.union(1, 6);
        qf.union(5, 6);
        qf.union(4, 3);
        qf.union(3, 2);
        qf.union(5, 9);
        qf.union(5, 3);
        qf.printCheck();
        qf.union(8, 7);
        qf.union(0, 8);
        qf.printCheck();
        qf.union(0, 1);
        qf.printCheck();
        System.out.println(System.currentTimeMillis());
        System.out.println(qf.connected(7, 2));
        System.out.println(System.currentTimeMillis());
    }
}