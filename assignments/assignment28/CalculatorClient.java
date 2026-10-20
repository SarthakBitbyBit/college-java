import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 1099;
        String serviceName = "CalculatorService";

        System.out.println("connecting to rmi registry at " + host + ":" + port + "...");
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            CalculatorRemote calculator = (CalculatorRemote) registry.lookup(serviceName);
            System.out.println("successfully obtained remote reference for " + serviceName);

            double a = 45.0;
            double b = 15.0;
            System.out.println("performing remote calculations with a = " + a + ", b = " + b);
            System.out.println("remote add(" + a + ", " + b + ") = " + calculator.add(a, b));
            System.out.println("remote subtract(" + a + ", " + b + ") = " + calculator.subtract(a, b));
            System.out.println("remote multiply(" + a + ", " + b + ") = " + calculator.multiply(a, b));
            System.out.println("remote divide(" + a + ", " + b + ") = " + calculator.divide(a, b));

            System.out.println("testing remote division by zero handling...");
            try {
                calculator.divide(a, 0);
            } catch (ArithmeticException e) {
                System.out.println("caught expected exception: " + e.getMessage());
            }

            System.out.println("all remote calculator operations completed successfully");
        } catch (Exception e) {
            System.out.println("calculator client error: " + e.getMessage());
        }
    }
}
