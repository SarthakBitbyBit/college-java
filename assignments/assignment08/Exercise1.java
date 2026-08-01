package assignments.assignment08;

class Employee {
    protected String name;
    protected double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void display() {
        System.out.println("Employee: " + name + ", Salary: " + salary);
    }
}

class Manager extends Employee {
    private final double allowance;

    Manager(String name, double salary, double allowance) {
        super(name, salary);
        this.allowance = allowance;
    }

    void display() {
        super.display();
        System.out.println("Manager total salary: " + (super.salary + allowance));
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        new Manager("Neha", 30000, 5000).display();
    }
}
