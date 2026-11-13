package LLD.Part2.StructuralDesignPattern.Flyweight;

import java.util.HashMap;
import java.util.Map;

class BulletFactory {
    private static final Map<String, BulletType> cache = new HashMap<>();

    private BulletFactory() {}

    static BulletType get(String name, String model, int damage) {
        return cache.computeIfAbsent(name, k -> new BulletType(name, model, damage));
    }
}