package Topics;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class Threads {

    static void main(String[] args) {
        ZeroEvenOdd z = new ZeroEvenOdd(5);
        H2O h = new H2O();
    }

    public void ZeroEvenOdd(){

    }

    public void H20(){

    }

}

class ZeroEvenOdd {
    private int n;
    private Semaphore z;
    private Semaphore e;
    private Semaphore o;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.z = new Semaphore(1);
        this.o = new Semaphore(0);
        this.e = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            z.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                o.release();
            } else {
                e.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i = i + 2) {
            e.acquire();
            printNumber.accept(i);
            z.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i + 2) {
            o.acquire();
            printNumber.accept(i);
            z.release();
        }
    }
}

class H2O {
    private Semaphore h;
    private Semaphore o;

    public H2O() {
        this.h = new Semaphore(2);
        this.o = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }
}
