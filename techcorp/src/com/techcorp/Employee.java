package com.techcorp;

abstract class Employee implements Payable {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public final String getCompanyName() {
        return "TechCorp";
    }

    public abstract String getRole();

    public void displayEmployeeDetails(){
        System.out.println("Company: " + getCompanyName());
        System.out.println("Name: " + name);
        System.out.println("Role: "+ getRole());
        System.out.println("Eligible Bonus: " + calculateBonus());
    }

}