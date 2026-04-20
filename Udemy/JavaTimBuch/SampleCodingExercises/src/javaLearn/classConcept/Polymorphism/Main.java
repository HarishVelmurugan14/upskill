package javaLearn.classConcept.Polymorphism;

//Same call of a method acts differently based on the place from where it is called.

public class Main {
    public static void main(String[] args) {
        Lamborgini avendator = new Lamborgini(3, "aven");
        Ford mustang = new Ford(4, "mustang");
        System.out.println(avendator.getTopSpeed());
        System.out.println(mustang.getTopSpeed());
    }
}

class Car {
    private String name;
    private boolean engines = true;
    private int cylinders;

    private int topSpeed = 200;
    private int wheels = 4;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders < 0 ? 1 : cylinders;
        this.name = name;
    }

    public boolean getEngines() {
        return engines;
    }

    public String getName() {
        return name;
    }

    public int getCylinders() {
        return cylinders;
    }

    public int getWheels() {
        return wheels;
    }

    public int getTopSpeed() {
        return topSpeed;
    }
}

class Lamborgini extends Car {
    public Lamborgini(int cylinders, String name) {
        super(cylinders, name);
    }

    public int getTopSpeed() {
        return 350;
    }
}

class Ford extends Car {
    public Ford(int cylinders, String name) {
        super(cylinders, name);
    }
    // No Top speed provided
}