package LLD.Part2.StructuralDesignPattern.Adapter;

public class OriginClasses {
    // OWNED BY BANK
}

// 2. Adaptees: real bank APIs (can't change them)
class YesBank {
    void sendMoney(double amt) {
        System.out.println("YesBank sent ₹" + amt);
    }
}

class IciciBank {
    void makePayment(String amt) {
        System.out.println("ICICI paid ₹" + amt);
    }
}

