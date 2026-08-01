package assignments.assignment07;

interface Product {
    void displayDetails();
}

class EProduct implements Product {
    private final String name;
    private final double price;

    EProduct(String name, double price) { this.name = name; this.price = price; }

    public void displayDetails() { System.out.println("Electronic: " + name + ", Rs. " + price); }
}

class ClothingProduct implements Product {
    private final String name;
    private final double price;

    ClothingProduct(String name, double price) { this.name = name; this.price = price; }

    public void displayDetails() { System.out.println("Clothing: " + name + ", Rs. " + price); }
}

class GroceryProduct implements Product {
    private final String name;
    private final double price;

    GroceryProduct(String name, double price) { this.name = name; this.price = price; }

    public void displayDetails() { System.out.println("Grocery: " + name + ", Rs. " + price); }
}

public class Exercise2 {
    public static void main(String[] args) {
        Product[] products = {
            new EProduct("Headphones", 1999),
            new ClothingProduct("Denim Jacket", 2499),
            new GroceryProduct("Rice", 850)
        };
        for (Product product : products) product.displayDetails();
    }
}
