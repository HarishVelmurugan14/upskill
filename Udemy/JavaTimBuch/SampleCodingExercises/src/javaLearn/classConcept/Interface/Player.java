package javaLearn.classConcept.Interface;

import java.util.ArrayList;
import java.util.List;

public class Player implements ISaveable {
    private String name;
    private int kills;
    private int health;
    private String gun;

    public Player(String name, int kills, int health, String gun) {
        this.name = name;
        this.kills = kills;
        this.health = health;
        this.gun = gun;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Override
    public List<String> write() {
        List<String> values = new ArrayList<>();
        values.add(0, this.name);
        values.add(1, String.valueOf(this.kills));
        values.add(2, String.valueOf(this.health));
        values.add(3, this.gun);
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        if (savedValues != null && savedValues.size() > 0) {
            this.name = savedValues.get(0);
            this.kills = Integer.parseInt(savedValues.get(1));
            this.health = Integer.parseInt(savedValues.get(2));
            this.gun = savedValues.get(3);
        }
    }

    public String getName() {
        return name;
    }


    public int getKills() {
        return kills;
    }

    public int getHealth() {
        return health;
    }

    public String getGun() {
        return gun;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", kills=" + kills +
                ", health=" + health +
                ", gun='" + gun + '\'' +
                '}';
    }
}
