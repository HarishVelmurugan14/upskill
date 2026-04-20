package com.dsa.week1.quickfindunion.assignment;

import java.sql.Timestamp;

public class SocialNetworkConnectivity {
    private static int[] id;
    private static int[] size;
    private static Timestamp initialTime;

    public SocialNetworkConnectivity(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
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
        if (size[rootOfP] >= size[rootOfQ]) {
            id[rootOfQ] = rootOfP;
            size[rootOfP] += size[rootOfQ];
            pathCompression(rootOfQ, rootOfP);
        } else {
            id[rootOfP] = rootOfQ;
            size[rootOfQ] += size[rootOfP];
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

    public int[] getSize() {
        return size;
    }


    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int allConnected() {
        for (int size : getSize()) {
            if (size == id.length) {
                System.out.println("Connected");
                return 1;
            }
        }
        System.out.println("Not Connected");
        return -1;
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
        count = 0;
        System.out.print("Size  | ");
        for (int idValue : getSize()) {
            System.out.print(idValue + " | ");
            count++;
        }
        System.out.println();
        System.out.println();
    }


}

class SocialNetworkConnectivityClient {
    public static void main(String[] args) {
        SocialNetworkConnectivity qf = new SocialNetworkConnectivity(10);
        String line = "11212341244 1 6";
        String[] values = line.split(" ");
        System.out.println(System.currentTimeMillis());



        qf.union(Integer.parseInt(values[1]), Integer.parseInt(values[2]));


        System.out.println(System.currentTimeMillis());
        //  qf.union(1, 6);
        qf.union(5, 6);
        qf.union(4, 3);
        qf.union(3, 2);
        qf.union(5, 9);
        qf.union(5, 3);
        qf.printCheck();
        qf.allConnected();
        qf.union(8, 7);
        qf.union(0, 8);
        qf.printCheck();
        qf.allConnected();
        qf.union(0, 1);
        qf.printCheck();
        qf.allConnected();
        System.out.println(System.currentTimeMillis());
        System.out.println(qf.connected(7, 2));
        System.out.println(System.currentTimeMillis());
    }
}
