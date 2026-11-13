package LLD.Part2.CreationalDesignPattern.Factory.AbstractFactory;

public interface Transactions {
    void createTransaction();
}

final class MySqlTransaction implements Transactions {
    public void createTransaction() {
        System.out.println("MySql txn has been created");
    }
}

class PostrgresTransaction implements Transactions {
    public void createTransaction() {
        System.out.println("PostgreSql txn has been created");
    }
}