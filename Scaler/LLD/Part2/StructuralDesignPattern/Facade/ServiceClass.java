package LLD.Part2.StructuralDesignPattern.Facade;

public class ServiceClass {
    // sub service classes
}

// ===== Subsystems (complex parts) =====
class RestaurantService {
    boolean isOpen(String restaurant) {
        System.out.println("Checking if " + restaurant + " is open");
        return true;
    }
}

class InventoryService {
    boolean inStock(String item) {
        System.out.println("Checking stock for " + item);
        return true;
    }
}

class PaymentService {
    void pay(int amount) {
        System.out.println("Paid ₹" + amount);
    }
}

class DeliveryService {
    void assignPartner() {
        System.out.println("Delivery partner assigned");
    }
}

class NotificationService {
    void notifyUser(String msg) {
        System.out.println("Notification: " + msg);
    }
}