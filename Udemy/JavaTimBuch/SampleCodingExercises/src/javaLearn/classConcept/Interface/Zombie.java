package javaLearn.classConcept.Interface;

import java.util.ArrayList;
import java.util.List;

public class Zombie implements ISaveable {

    private String name;
    private int damagePerSecond;
    private int health;

    public Zombie(String name, int damagePerSecond, int health) {
        this.name = name;
        this.damagePerSecond = damagePerSecond;
        this.health = health;
    }

    @Override
    public List<String> write() {
        List<String> values = new ArrayList<>();
        values.add(0, this.name);
        values.add(1, String.valueOf(this.damagePerSecond));
        values.add(2, String.valueOf(this.health));
        values.add(3, "antiSunlight");
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        if (savedValues != null && savedValues.size() > 0) {
            this.name = savedValues.get(0);
            this.damagePerSecond = Integer.parseInt(savedValues.get(1));
            this.health = Integer.parseInt(savedValues.get(2));
        }
    }

    public String getName() {
        return name;
    }

    public int getDamagePerSecond() {
        return damagePerSecond;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return "Zombie{" +
                "name='" + name + '\'' +
                ", damagePerSecond=" + damagePerSecond +
                ", health=" + health +
                '}';
    }
}
