import java.util.Scanner;

public class ATM_PIN_Verification {

    static void verifyPIN(int pin) throws Exception {
        if (pin != 1234) {
            throw new Exception("Invalid PIN.");
        }

        System.out.println("PIN verified successfully.");
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            try {
                System.out.print("Enter ATM PIN: ");
                int pin = sc.nextInt();

                verifyPIN(pin);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            finally {
                System.out.println("PIN verification process completed.");
            }
        }
    }
}
