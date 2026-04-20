package javaLearn.classConcept.Encapsulation;

public class Printer {
    private int tonerLevel = 100;
    private int numberOfPagesPrinted;
    boolean isDuplex;

    public Printer(int tonerLevel, int numberOfPagesPrinted, boolean isDuplex) {
        if(tonerLevel > 0 && tonerLevel < 100){
            this.tonerLevel = tonerLevel;
        }
        this.numberOfPagesPrinted = numberOfPagesPrinted;
        this.isDuplex = isDuplex;
    }

    public void printAPage(){
        this.numberOfPagesPrinted = this.numberOfPagesPrinted +1;
        this.tonerLevel -= 1;
    }

    public void fillToner(int refill){
        this.tonerLevel = this.getTonerLevel() + refill;
        if(this.tonerLevel > 100){
            this.tonerLevel =100;
        }
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public int getNumberOfPagesPrinted() {
        return numberOfPagesPrinted;
    }

    public boolean isDuplex() {
        return isDuplex;
    }
}
