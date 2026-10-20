import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static final int REGISTRY_PORT = 1099;
    public static final String SERVICE_NAME = "CalculatorService";

    public static void main(String[] args) {
        try {
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(REGISTRY_PORT);
                System.out.println("created rmi registry on port " + REGISTRY_PORT);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(REGISTRY_PORT);
                System.out.println("connected to existing rmi registry on port " + REGISTRY_PORT);
            }

            CalculatorImpl calculator = new CalculatorImpl();
            registry.rebind(SERVICE_NAME, calculator);
            System.out.println("calculator service bound as '" + SERVICE_NAME + "' in registry");
            System.out.println("calculator rmi server is ready and running");
        } catch (Exception e) {
            System.out.println("calculator server failed: " + e.getMessage());
        }
    }
}
