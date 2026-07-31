final class LibraryRules {
    static final int MAX_BOOKS = 5;

    final void displayRule() {
        System.out.println("A student can issue up to " + MAX_BOOKS + " books.");
    }
}

class BankAccount {
    private final int accountNumber;
    private double balance;

    BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void display() {
        System.out.println("Account: " + accountNumber + ", Balance: " + balance);
    }
}

public class Assignment09 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1001, 15000);
        account.display();
        new LibraryRules().displayRule();
    }
}
