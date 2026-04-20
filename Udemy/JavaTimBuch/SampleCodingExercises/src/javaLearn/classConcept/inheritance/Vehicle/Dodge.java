package javaLearn.classConcept.inheritance.Vehicle;

public class Dodge extends Car{
int wiper;

    public Dodge(int speed) {
        super(speed, 5);
        this.wiper = 2;
        System.out.println("Dodge");
    }
}

