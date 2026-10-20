import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TemperatureClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 1099;
        String serviceName = "TemperatureService";

        System.out.println("connecting to rmi registry at " + host + ":" + port + "...");
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            TemperatureRemote service = (TemperatureRemote) registry.lookup(serviceName);
            System.out.println("successfully obtained remote reference for " + serviceName);

            double cInput = 100.0;
            double fResult = service.celsiusToFahrenheit(cInput);
            System.out.printf("celsius to fahrenheit: %.2f C = %.2f F%n", cInput, fResult);

            double fInput = 98.6;
            double cResult = service.fahrenheitToCelsius(fInput);
            System.out.printf("fahrenheit to celsius: %.2f F = %.2f C%n", fInput, cResult);

            double freezingC = 0.0;
            System.out.printf("freezing point check: %.2f C = %.2f F%n", freezingC, service.celsiusToFahrenheit(freezingC));

            System.out.println("remote temperature conversion test completed successfully");
        } catch (Exception e) {
            System.out.println("temperature client error: " + e.getMessage());
        }
    }
}
