package javaLearn.classConcept.inheritance.Vehicle;

public class Car extends javaLearn.classConcept.inheritance.Vehicle.Vehicle {
    int gears;

    public Car(int speed, int gears) {
        super(speed);
        this.gears = gears;
        System.out.println("Car");

    }
}
