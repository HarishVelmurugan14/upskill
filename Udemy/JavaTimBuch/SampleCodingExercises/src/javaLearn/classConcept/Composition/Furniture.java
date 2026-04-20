package javaLearn.classConcept.Composition;

public class Furniture {
    Chairs chair;
    int mats;

    public Furniture(Chairs chair, int mats) {
        this.chair = chair;
        this.mats = mats;
    }
}
