package LLD.Part2.StructuralDesignPattern.Adapter;

// 1. Target: what PhonePe/GPay expect
interface BankAPI {
    // Written by client teams like phonepe
    void transfer(int amount);
}

// 3. Adapters: convert BankAPI calls into bank-specific calls
class YesBankAdapter implements BankAPI {
    private final YesBank yesBank = new YesBank();

    public void transfer(int amount) {
        yesBank.sendMoney(amount);              // int → double
    }
}

class IciciBankAdapter implements BankAPI {
    private final IciciBank icici = new IciciBank();

    public void transfer(int amount) {
        icici.makePayment(String.valueOf(amount));   // int → String
    }
}