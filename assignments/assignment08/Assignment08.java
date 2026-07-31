class Employee {
    String name = "Neha";
    double salary = 30000;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void display() {
        System.out.println("Employee: " + name + ", Salary: " + salary);
    }
}

class Manager extends Employee {
    double allowance;

    Manager(String name, double salary, double allowance) {
        super(name, salary);
        this.allowance = allowance;
    }

    void display() {
        super.display();
        System.out.println("Total salary: " + (super.salary + allowance));
    }
}

public class Assignment08 {
    public static void main(String[] args) {
        Manager manager = new Manager("Neha", 30000, 5000);
        manager.display();
    }
}
