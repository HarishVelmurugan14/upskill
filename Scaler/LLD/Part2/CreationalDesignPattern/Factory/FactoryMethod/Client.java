package LLD.Part2.CreationalDesignPattern.Factory.FactoryMethod;

public class Client {
    public static void main(String[] args) {
        DatabaseFactory service = new PostgreService();
        service.getQueryConnection().createQuery();
    }
}
