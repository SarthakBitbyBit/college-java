package com.techcorp;

final class Intern extends Employee{
    public Intern(String name, double baseSalary){
        super(name, baseSalary);
    }

    @Override
        public String getRole(){
            return "Intern";
        }
    @Override
        public double calculateBonus(){
            return 500.0;
        }
}
