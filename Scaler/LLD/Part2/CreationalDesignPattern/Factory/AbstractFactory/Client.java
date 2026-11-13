package LLD.Part2.CreationalDesignPattern.Factory.AbstractFactory;

public class Client {
    public static void main(String[] args) {
        DatabaseComponentFactory service = new MySqlFactory();
        service.getQueryConnection().createQuery();
        service.getTxnConnection().createTransaction();
    }
}
