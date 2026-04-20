package javaLearn.classConcept.InnerClass;

public class Gearbox {
    private int currentGear = 0;
    private int maximumGears;





    public class Gear {
        private int gearNumber;
        private int speedInKMPH;

        public Gear(int gearNumber, int speedInKMPH) {
            this.gearNumber = gearNumber;
            this.speedInKMPH = speedInKMPH;
        }

        public int distanceTraveled(int timeInHour) {
            return speedInKMPH * timeInHour;
        }
    }
}
