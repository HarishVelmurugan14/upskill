package LLD.Part2.StructuralDesignPattern.Flyweight.Arena;

import java.util.concurrent.ThreadLocalRandom;

public class Player implements Runnable {
    private final String name;
    private final String gun;

    Player(String name, String gun) {
        this.name = name;
        this.gun = gun;
    }

    private BulletType ammo() {
        switch (gun) {
            case "M416": return BulletFactory.get("5.56mm", "light", 90);
            case "AKM":  return BulletFactory.get("7.62mm", "light", 120);
            case "AWM":  return BulletFactory.get(".300mag", "heavy", 290);
            default: throw new IllegalArgumentException("Unknown gun: " + gun);
        }
    }

    @Override
    public void run() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < 3; i++) {
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            int dir = rand.nextInt(360);
            new Bullet(x, y, dir, ammo()).fire(name);
        }
    }
}