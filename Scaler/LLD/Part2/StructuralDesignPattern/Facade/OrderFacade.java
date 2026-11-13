package LLD.Part2.StructuralDesignPattern.Facade;

// ===== Facade =====
class OrderFacade {
    private final RestaurantService restaurant = new RestaurantService();
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final DeliveryService delivery = new DeliveryService();
    private final NotificationService notification = new NotificationService();

    public void placeOrder(String rest, String item, int amount) {
        if (!restaurant.isOpen(rest)) return;
        if (!inventory.inStock(item)) return;

        payment.pay(amount);
        delivery.assignPartner();
        notification.notifyUser("Order placed for " + item);
    }
}