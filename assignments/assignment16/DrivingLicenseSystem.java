import java.util.Scanner;

public class DrivingLicenseSystem {

    static class InvalidDrivingAgeException extends Exception {
        InvalidDrivingAgeException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            try {
                System.out.print("Enter your age: ");
                int age = sc.nextInt();

                if (age < 18) {
                    throw new InvalidDrivingAgeException(
                        "You are not eligible for a driving license."
                    );
                }

                System.out.println("You are eligible for a driving license.");
            }
            catch (InvalidDrivingAgeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
