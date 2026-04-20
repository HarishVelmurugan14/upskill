package javaLearn.classConcept;

public class BankAccount {

    private int accountNumber;
    private float  balance;
    private String email;
    private String customerName;
    private long phoneNumber;

    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }

    public void setBalance(float balance){
        this.balance = balance;
    }

    public float getBalance(){
        return this.balance;
    }

    public String depositAmount(float amount){
        if(amount > 0.0) {
            this.balance = this.balance + amount;
            return "Your New balance is "+ this.balance;
        }
        return "Invalid Deposit amount, Total Balance is " + this.balance;
    }

    public String withdrawAmount(float amount){
        if(amount > 0.0 && amount < this.balance) {
            this.balance = this.balance - amount;
            return "Your New balance is "+ this.balance;
        }
        return "Insufficient Funds, Total Balance is " + this.balance;
    }
}
