package LLD.Part2.StructuralDesignPattern.Flyweight.Arena;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class BulletFactory {
    private static final Map<String, BulletType> cache = new ConcurrentHashMap<>();

    private BulletFactory() {}

    static BulletType get(String name, String model, int damage) {
        return cache.computeIfAbsent(name, k -> new BulletType(name, model, damage));
    }

    static int size() {
        return cache.size();
    }
}