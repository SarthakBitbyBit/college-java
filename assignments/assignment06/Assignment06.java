package assignments.assignment06;

interface DeliveryAction {
    void update(String message);
}

class Vehicle {
    private String number = "KA-01-1234";

    class Details {
        void display() {
            System.out.println("Vehicle number: " + number);
        }
    }

    void startDelivery() {
        DeliveryAction action = new DeliveryAction() {
            public void update(String message) {
                System.out.println("Delivery status: " + message);
            }
        };
        action.update("Started");
    }
}

public class Assignment06 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        Vehicle.Details details = vehicle.new Details();
        details.display();
        vehicle.startDelivery();
    }
}
