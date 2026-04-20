package javaLearn.classConcept.ArrayList.AutoAndUnBoxing;

import java.util.ArrayList;

public class Customer {
    private String name;
    private final ArrayList<Double> transactions = new ArrayList<>();


    public Customer(String name, double transaction) {
        this.name = name;
        transactions.add(transaction);
    }

    public void addTransaction(double transaction){
        transactions.add(transaction);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }
}
