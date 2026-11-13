package LLD.Part2.StructuralDesignPattern.Flyweight.Arena;

public class Bullet {
    private final int x, y, direction;   // extrinsic
    private final BulletType type;       // shared

    Bullet(int x, int y, int direction, BulletType type) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = type;
    }

    void fire(String player) {
        type.fire(player, x, y, direction);
    }
}
