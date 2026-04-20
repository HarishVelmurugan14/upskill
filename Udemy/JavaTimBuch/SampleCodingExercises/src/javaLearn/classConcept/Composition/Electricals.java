package javaLearn.classConcept.Composition;

public class Electricals {
    int lights;
    int fans;

    public Electricals(int lights, int fans) {
        this.lights = lights;
        this.fans = fans;
        System.out.println("Number of light = "+ lights+ " & Number of fans = "+ fans);
    }
}
