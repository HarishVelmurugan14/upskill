package javaLearn.classConcept.ArrayList.AutoAndUnBoxing;

import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers;


    public Branch(String branchName, String cxName, double initialTransaction) {
        this.name = branchName;
        customers.add(new Customer(cxName, initialTransaction));
    }

    public boolean newCustomer(String customerName, double initialAmount){
        return customers.add(new Customer(customerName,initialAmount));
    }

    public Branch(String branchName) {
        this.name = branchName;
        customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

}
