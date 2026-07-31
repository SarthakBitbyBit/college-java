package assignments.assignment10;

abstract class Payment {
    abstract void pay(double amount);

    void receipt() {
        System.out.println("Payment receipt generated.");
    }
}

class CreditCardPayment extends Payment {
    void pay(double amount) {
        System.out.println("Paid by credit card: " + amount);
    }
}

class UpiPayment extends Payment {
    void pay(double amount) {
        System.out.println("Paid by UPI: " + amount);
    }
}

public class Assignment10 {
    public static void main(String[] args) {
        Payment card = new CreditCardPayment();
        Payment upi = new UpiPayment();
        card.pay(1200);
        card.receipt();
        upi.pay(850);
        upi.receipt();
    }
}
