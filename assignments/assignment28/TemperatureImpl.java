import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TemperatureImpl extends UnicastRemoteObject implements TemperatureRemote {
    public TemperatureImpl() throws RemoteException {
        super();
    }

    @Override
    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    @Override
    public double fahrenheitToCelsius(double fahrenheit) throws RemoteException {
        return (fahrenheit - 32.0) * 5.0 / 9.0;
    }
}
