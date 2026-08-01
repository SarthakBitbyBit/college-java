package assignments.assignment02;

class Mobile {
    private String brand;
    private String model;
    private double price;

    Mobile() {
        this("Unknown", "Unknown", 0);
    }

    Mobile(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    Mobile(Mobile other) {
        this(other.brand, other.model, other.price);
    }

    void display() {
        System.out.println(brand + " " + model + " - Rs. " + price);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        Mobile defaultPhone = new Mobile();
        Mobile originalPhone = new Mobile("Samsung", "Galaxy A55", 39999);
        Mobile duplicatePhone = new Mobile(originalPhone);
        defaultPhone.display();
        originalPhone.display();
        duplicatePhone.display();
    }
}
