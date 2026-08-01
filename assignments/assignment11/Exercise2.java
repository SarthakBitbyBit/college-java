package assignments.assignment11;

interface Switchable {
    void turnOn();
}

class Light implements Switchable {
    public void turnOn() { System.out.println("Light is on."); }
}

class Fan implements Switchable {
    public void turnOn() { System.out.println("Fan is on."); }
}

public class Exercise2 {
    public static void main(String[] args) {
        Switchable[] devices = { new Light(), new Fan() };
        for (Switchable device : devices) device.turnOn();
    }
}
