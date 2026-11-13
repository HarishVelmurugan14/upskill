package LLD.Part2.StructuralDesignPattern.Flyweight.Arena;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        pool.submit(new Player("Harish", "M416"));
        pool.submit(new Player("Ravi", "AKM"));
        pool.submit(new Player("Kumar", "AWM"));
        pool.submit(new Player("Arun", "M416"));
        pool.submit(new Player("Vijay", "AKM"));

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\nBullets fired: 15");
        System.out.println("Bullet types loaded: " + BulletFactory.size());
    }
}
