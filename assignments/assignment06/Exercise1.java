package assignments.assignment06;

interface VehicleAction {
    void perform(String message);
}

class Vehicle {
    private final String number = "KA-01-1234";

    class Details {
        void display() {
            System.out.println("Vehicle number: " + number);
        }
    }

    void start() {
        VehicleAction action = new VehicleAction() {
            public void perform(String message) {
                System.out.println("Vehicle action: " + message);
            }
        };
        action.perform("Vehicle started");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.new Details().display();
        vehicle.start();
    }
}
