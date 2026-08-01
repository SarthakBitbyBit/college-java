package assignments.assignment03;

class RestaurantBilling {
    private static int totalOrders;

    double calculateBill(double food, double tax) {
        totalOrders++;
        return food + tax;
    }

    double calculateBill(double food, double tax, double packaging) {
        totalOrders++;
        return food + tax + packaging;
    }

    double calculateBill(double food, double tax, double packaging, double deliveryCharge) {
        totalOrders++;
        return food + tax + packaging + deliveryCharge;
    }

    static int getTotalOrders() {
        return totalOrders;
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        RestaurantBilling billing = new RestaurantBilling();
        System.out.println("Dine-in bill: Rs. " + billing.calculateBill(500, 90));
        System.out.println("Takeaway bill: Rs. " + billing.calculateBill(500, 90, 20));
        System.out.println("Delivery bill: Rs. " + billing.calculateBill(500, 90, 20, 50));
        System.out.println("Total orders: " + RestaurantBilling.getTotalOrders());
    }
}
