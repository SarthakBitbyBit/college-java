package assignments.assignment06;

interface DeliveryStatus {
    void update(String status);
}

class FoodOrder {
    private final int orderNumber;
    private final String item;

    FoodOrder(int orderNumber, String item) {
        this.orderNumber = orderNumber;
        this.item = item;
    }

    class OrderDetails {
        void display() {
            System.out.println("Order " + orderNumber + ": " + item);
        }
    }

    void trackDelivery() {
        DeliveryStatus status = new DeliveryStatus() {
            public void update(String message) {
                System.out.println("Delivery status: " + message);
            }
        };
        status.update("Order picked up");
        status.update("Order delivered");
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        FoodOrder order = new FoodOrder(101, "Vegetable Biryani");
        order.new OrderDetails().display();
        order.trackDelivery();
    }
}
