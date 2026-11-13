package LLD.Part2.StructuralDesignPattern.Flyweight;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Bullet> bullets = new ArrayList<>();
        BulletType fiveMM = BulletFactory.get("5.56mm", "light", 90);
        BulletType sevenMM = BulletFactory.get("7.62mm", "light", 120);
        BulletType sniper = BulletFactory.get(".300mag", "heavy", 290);


        for (int i = 0; i < 10; i++) {
            bullets.add(new Bullet((int) Math.round(Math.random()), (int) Math.round(Math.random()), 43, fiveMM));
        }

        bullets.forEach(bullet -> bullet.fire("A"));
    }
}
