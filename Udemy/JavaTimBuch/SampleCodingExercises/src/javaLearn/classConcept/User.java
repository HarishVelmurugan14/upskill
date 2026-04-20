package javaLearn.classConcept;

public class User {

    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        ba.setBalance(900);

        System.out.println(ba.depositAmount(-1));
        System.out.println(ba.withdrawAmount(1200));
    }
}
