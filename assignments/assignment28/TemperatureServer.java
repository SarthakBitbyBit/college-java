import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TemperatureServer {
    public static final int REGISTRY_PORT = 1099;
    public static final String SERVICE_NAME = "TemperatureService";

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

            TemperatureImpl service = new TemperatureImpl();
            registry.rebind(SERVICE_NAME, service);
            System.out.println("temperature service bound as '" + SERVICE_NAME + "' in registry");
            System.out.println("temperature rmi server is ready and running");
        } catch (Exception e) {
            System.out.println("temperature server error: " + e.getMessage());
        }
    }
}
