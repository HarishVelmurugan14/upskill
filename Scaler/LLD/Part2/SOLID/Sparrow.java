package LLD.Part2.SOLID;

public class Sparrow {
    Flying flying = new HighFly();

    public void fly() {
        flying.fly();
    }
}
