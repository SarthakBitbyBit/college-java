package assignments.assignment10;

abstract class FoodOrder {
    protected double foodPrice;

    FoodOrder(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    abstract double calculateBill();

    void displayBill() {
        System.out.println("Total bill: Rs. " + calculateBill());
    }
}

class DineInOrder extends FoodOrder {
    DineInOrder(double foodPrice) {
        super(foodPrice);
    }

    double calculateBill() {
        return foodPrice + foodPrice * 0.05;
    }
}

class TakeAwayOrder extends FoodOrder {
    TakeAwayOrder(double foodPrice) {
        super(foodPrice);
    }

    double calculateBill() {
        return foodPrice + 20;
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("Dine-in order:");
        new DineInOrder(500).displayBill();
        System.out.println("Take-away order:");
        new TakeAwayOrder(500).displayBill();
    }
}
