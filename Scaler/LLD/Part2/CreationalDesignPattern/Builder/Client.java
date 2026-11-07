package LLD.Part2.CreationalDesignPattern.Builder;

public class Client {
    public static void main(String[] args) {
        SoldItem s = SoldItem.getBuilder()
                     .setName("Pen")
                     .setId(101)
                     .setPrice(20.0)
                     .build();
        s.printAll();
    }
}
