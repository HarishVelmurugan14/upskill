package javaLearn.classConcept.Composition;

public class Windows {
    int numberOfWindows;
    Dimension dimension;

    public Windows(int numberOfWindows, Dimension dimension) {
        this.numberOfWindows = numberOfWindows;
        this.dimension = dimension;
        System.out.println("It has " + numberOfWindows + " windows, each has a size of "+ getSize());
    }

    public int getSize(){
        return dimension.getDepth() * dimension.getHeight() * dimension.getWidth();
    }
}
