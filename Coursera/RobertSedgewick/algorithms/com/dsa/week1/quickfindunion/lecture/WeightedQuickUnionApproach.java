package com.dsa.week1.quickfindunion.lecture;
public class WeightedQuickUnionApproach {

    private int id[];
    private int size[];

    public WeightedQuickUnionApproach(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] =1;
        }
    }

    public void findWeight(int rootOfP, int rootOfQ){
        if(size[rootOfP] > size[rootOfQ]){
            System.out.println("P -> "+rootOfP);
            System.out.println("Q -> "+rootOfQ);
            id[rootOfQ] = rootOfP;
            size[rootOfP] = size[rootOfP] +size[rootOfQ];
        }else{
            System.out.println("Q -> "+rootOfQ);
            System.out.println("P -> "+rootOfP);
            id[rootOfP] = rootOfQ;
            size[rootOfQ] = size[rootOfQ] +size[rootOfP];
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


    public void printCheck(){
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

class WeightedQuickUnionClient {
    public static void main(String[] args) {
        WeightedQuickUnionApproach qf = new WeightedQuickUnionApproach(10);
        //  System.out.println(qf.connected(1,2));
        qf.union(1, 6);
        qf.printCheck();
        qf.union(5, 6);
        qf.printCheck();
        qf.union(4, 3);
        qf.printCheck();
        qf.union(3, 2);
        qf.printCheck();
        qf.union(5, 9);
        qf.printCheck();
        qf.union(5, 3);
        qf.printCheck();

        System.out.println(qf.connected(7, 2));
    }
}