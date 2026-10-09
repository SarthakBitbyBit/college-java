import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        double balance = 10000;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();

            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid withdrawal amount.");
            }

            if (amount > balance) {
                throw new IllegalArgumentException("Insufficient balance.");
            }

            balance -= amount;

            System.out.println("Withdrawal successful.");
            System.out.println("Remaining balance: " + balance);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        }
    }
}
