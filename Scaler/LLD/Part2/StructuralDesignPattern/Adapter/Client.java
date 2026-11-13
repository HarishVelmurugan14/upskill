package LLD.Part2.StructuralDesignPattern.Adapter;

public class Client {
    public static void main(String[] args) {
        new PhonePe().pay(new IciciBankAdapter(), 33);
        new GPay().pay(new IciciBankAdapter(), 56);
    }
}

// 4. Clients: UPI apps only know BankAPI
class PhonePe {
    void pay(BankAPI bank, int amount) {
        System.out.print("PhonePe → ");
        bank.transfer(amount);
    }
}

class GPay {
    void pay(BankAPI bank, int amount) {
        System.out.print("GPay → ");
        bank.transfer(amount);
    }
}
