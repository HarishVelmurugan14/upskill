package com.dsa.week1.quickfindunion.assignment;

public class UnionFindCanonicalElement {
    private static int[] id;


    public UnionFindCanonicalElement(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void pathCompression(int toChange, int withValue) {
        int count = 0;
        for (int idVal : id) {
            if (idVal == toChange) {
                id[count] = withValue;
            }
            count++;
        }
    }

    public void union(int p, int q) {
        int rootOfP = root(p);
        int rootOfQ = root(q);
        if (rootOfP > rootOfQ) {
            id[rootOfQ] = rootOfP;
            pathCompression(rootOfQ, rootOfP);
        } else {
            id[rootOfP] = rootOfQ;
            pathCompression(rootOfP, rootOfQ);
        }
    }

    public int root(int x) {
        while (id[x] != x) {
            x = id[x];
        }
        return x;
    }

    public int[] getId() {
        return id;
    }

    public int find(int x) {
        System.out.println(id[x]);
        return id[x];
    }


    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }


    public void printCheck() {
        int count = 0;
        System.out.print("Count | ");
        for (int idValue : getId()) {
            System.out.print(count + " | ");
            count++;
        }
        System.out.println();
        count = 0;
        System.out.print("Value | ");
        for (int idValue : getId()) {
            System.out.print(idValue + " | ");
            count++;
        }
        System.out.println();
        System.out.println();
    }


}

class UnionFindCanonicalElementClient {
    public static void main(String[] args) {
        UnionFindCanonicalElement qf = new UnionFindCanonicalElement(10);
//        qf.union(1, 6);
//        qf.union(5, 6);
//        qf.union(4, 3);
//        qf.union(3, 2);
//        qf.union(5, 9);
//        qf.printCheck();
//        qf.union(5, 3);
//        qf.printCheck();
//        qf.union(8, 7);
//        qf.printCheck();
//        qf.union(0, 8);
//        qf.printCheck();
//        qf.union(0, 1);
//        qf.printCheck();
//        qf.find(6);
//        qf.find(3);
    }
}
