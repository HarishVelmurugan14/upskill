package LLD.Part2.StructuralDesignPattern.Facade;

public class Client {
    public static void main(String[] args) {
        OrderFacade swiggy = new OrderFacade();
        swiggy.placeOrder("A2B", "Masala Dosa", 120);
    }
}