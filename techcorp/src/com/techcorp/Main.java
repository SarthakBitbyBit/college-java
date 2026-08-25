package com.techcorp;

public class Main {
    public static void main(String[] args) {
        Employee manager = new Manager ("Sarthak", 100000000);
        Employee intern = new Intern("Vedant", 30000);

        manager.displayEmployeeDetails();
        intern.displayEmployeeDetails();

    }

}
