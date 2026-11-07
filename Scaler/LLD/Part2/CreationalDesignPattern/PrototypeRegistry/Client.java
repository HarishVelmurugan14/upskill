package LLD.Part2.CreationalDesignPattern.PrototypeRegistry;

public class Client {
    public static void main(String[] args) {
        Pizza veg = new Pizza();
        veg.name = "Veg";
        veg.size = "Medium";

        PizzaRegistry shop = new PizzaRegistry();
        shop.add("VEG", veg);

        Pizza order1 = shop.get("VEG");
        order1.size = "Large";

        Pizza order2 = shop.get("VEG"); // Still medium
    }
}
