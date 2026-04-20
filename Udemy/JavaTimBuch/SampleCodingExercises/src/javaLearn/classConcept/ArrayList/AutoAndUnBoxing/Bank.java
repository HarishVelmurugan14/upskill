package javaLearn.classConcept.ArrayList.AutoAndUnBoxing;

import java.util.ArrayList;

public class Bank {
    public String name;
    public ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName) {
        if (findBranch(branchName) != null) {
            return false;
        }
        branches.add(new Branch(branchName));
        return true;
    }

    public boolean addCustomer(String branchName, String cxName, double initialTransaction) {
        Branch branchDetails = findBranch(branchName);
        if (branchDetails != null) {
            if (findCustomer(cxName, branchDetails) != null) {
                return false;
            }
            return branchDetails.newCustomer(cxName,initialTransaction);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String cxName, double transaction) {
        Branch branchDetails = findBranch(branchName);
        if (branchDetails != null) {
            Customer customer = findCustomer(cxName, branchDetails);
            if (customer != null) {
                customer.addTransaction(transaction);
                return true;
            }
        }
        return false;
    }

    public boolean listCustomers(String branchName, boolean printTransaction) {
        Branch branchDetails = findBranch(branchName);
        if (branchDetails != null) {
            int customerCount = 1;
            System.out.println("Customer Details for branch " + branchDetails.getName());
            for (Customer customer : branchDetails.getCustomers()) {
                System.out.println("Customer: " + customer.getName() + "[" + customerCount + "]");
                if (printTransaction) {
                    System.out.println("Transactions");
                    int transactionCount = 1;
                    for (Double transaction : customer.getTransactions()) {
                        System.out.println("[" + transactionCount + "] Amount " + transaction);
                        transactionCount++;
                    }
                }
                customerCount++;
            }

            return true;
        }
        return false;
    }

    private Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getName().equalsIgnoreCase(branchName)) {
                return branch;
            }
        }
        return null;
    }

    private Customer findCustomer(String cxName, Branch branchInfo) {
        for (Customer customer : branchInfo.getCustomers()) {
            if (customer.getName().equalsIgnoreCase(cxName)) {
                return customer;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Bank bank = new Bank("National Australia Bank");

        bank.addBranch("Adelaide");

        bank.addCustomer("Adelaide", "Tim", 50.05);
        bank.addCustomer("Adelaide", "Mike", 175.34);
        bank.addCustomer("Adelaide", "Percy", 220.12);

        bank.addCustomerTransaction("Adelaide", "Tim", 44.22);
        bank.addCustomerTransaction("Adelaide", "Tim", 12.44);
        bank.addCustomerTransaction("Adelaide", "Mike", 1.65);

        bank.listCustomers("Adelaide", true);
    }
}
