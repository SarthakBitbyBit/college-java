interface Printable {
    void printDetails();
}

class StudentRecord implements Printable {
    public void printDetails() {
        System.out.println("Student: Anaya, Roll number: 21");
    }
}

class EmployeeRecord implements Printable {
    public void printDetails() {
        System.out.println("Employee: Rahul, ID: 101");
    }
}

interface Switchable {
    void turnOn();
}

class Light implements Switchable {
    public void turnOn() {
        System.out.println("Light is on.");
    }
}

class Fan implements Switchable {
    public void turnOn() {
        System.out.println("Fan is on.");
    }
}

public class Assignment11 {
    public static void main(String[] args) {
        Printable student = new StudentRecord();
        Printable employee = new EmployeeRecord();
        student.printDetails();
        employee.printDetails();

        Switchable light = new Light();
        Switchable fan = new Fan();
        light.turnOn();
        fan.turnOn();
    }
}
