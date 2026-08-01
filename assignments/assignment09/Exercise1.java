package assignments.assignment09;

final class AccountRules {
    final void displayRule() {
        System.out.println("Account number cannot be changed after creation.");
    }
}

class BankAccount {
    private final int accountNumber;
    private double balance;

    BankAccount(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void display() {
        System.out.println("Account: " + accountNumber + ", Balance: " + balance);
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1001, 15000);
        account.deposit(2000);
        account.display();
        new AccountRules().displayRule();
    }
}
