package assignments.assignment07;

interface PaymentMethod {
    void pay(double amount);
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Cash payment: Rs. " + amount);
    }
}

class OnlinePayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Online payment: Rs. " + amount);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        PaymentMethod[] paymentMethods = {
            new CashPayment(),
            new OnlinePayment()
        };
        for (PaymentMethod paymentMethod : paymentMethods) {
            paymentMethod.pay(500);
        }
    }
}
