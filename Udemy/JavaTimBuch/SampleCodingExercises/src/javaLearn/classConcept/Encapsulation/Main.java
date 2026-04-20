package javaLearn.classConcept.Encapsulation;

//Encapsulation : To keeps things simple all value additions and access should be maintained in a
// single place to avoid multiple place renaming and multiple initialization; Modify scopes based on it

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer(98, 5, false);
        System.out.println("Pages Printed = " + printer.getNumberOfPagesPrinted() + " & Toner Level = " + printer.getTonerLevel());
        printer.printAPage();
        System.out.println("Pages Printed = " + printer.getNumberOfPagesPrinted() + " & Toner Level = " + printer.getTonerLevel());
        printer.fillToner(200);
        System.out.println("Pages Printed = " + printer.getNumberOfPagesPrinted() + " & Toner Level = " + printer.getTonerLevel());
    }
}
