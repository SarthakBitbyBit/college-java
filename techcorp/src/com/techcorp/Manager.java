package com.techcorp;

class Manager extends Employee {
    public Manager(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
        public String getRole() {
        return "Manager";
    }

    @Override
    public double calculateBonus() {
        return baseSalary * 0.5;
    }
}

