package LLD.Part1;

import java.util.Queue;
import java.util.concurrent.*;

class Store {
    // Thread-safe data structure (fixes the ArrayList problem)
    private final Queue<Object> items = new ConcurrentLinkedQueue<>();

    public void add(Object item) { items.add(item); }
    public Object remove() { return items.poll(); }
    public int size() { return items.size(); }
}

class Producer implements Runnable {
    private final Store store;
    private final Semaphore prodSem, consSem;

    Producer(Store store, Semaphore prodSem, Semaphore consSem) {
        this.store = store; this.prodSem = prodSem; this.consSem = consSem;
    }

    public void run() {
        while (true) {
            try {
                prodSem.acquire();          // wait for empty slot
                store.add(new Object());    // produce
                System.out.println(Thread.currentThread().getName() + " produced. Size: " + store.size());
                consSem.release();          // signal consumer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

class Consumer implements Runnable {
    private final Store store;
    private final Semaphore prodSem, consSem;

    Consumer(Store store, Semaphore prodSem, Semaphore consSem) {
        this.store = store; this.prodSem = prodSem; this.consSem = consSem;
    }

    public void run() {
        while (true) {
            try {
                consSem.acquire();          // wait for an item
                store.remove();             // consume
                System.out.println(Thread.currentThread().getName() + " consumed. Size: " + store.size());
                prodSem.release();          // signal producer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Store store = new Store();
        Semaphore prodSem = new Semaphore(5); // 5 empty slots
        Semaphore consSem = new Semaphore(0); // 0 items at start

        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i < 8; i++) ex.execute(new Producer(store, prodSem, consSem));
        for (int i = 0; i < 20; i++) ex.execute(new Consumer(store, prodSem, consSem));
    }
}