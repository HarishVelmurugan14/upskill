package LLD.Part2.StructuralDesignPattern.Flyweight.Arena;

public class BulletType {
    private final String name;
    private final String model;
    private final int damage;

    BulletType(String name, String model, int damage) {
        this.name = name;
        this.model = model;
        this.damage = damage;
        System.out.println(">> Loaded " + model + " model for " + name);
    }

    void fire(String player, int x, int y, int direction) {
        System.out.println(player + " fired " + name + " (dmg " + damage
                + ") at (" + x + "," + y + ") dir " + direction);
    }
}
