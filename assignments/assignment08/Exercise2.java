package assignments.assignment08;

class InsuredVehicle {
    protected String registrationNumber;
    protected String model;

    InsuredVehicle(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

    void displayVehicle() {
        System.out.println("Vehicle: " + registrationNumber + ", Model: " + model);
    }
}

class ComprehensiveInsurance extends InsuredVehicle {
    private final double premium;

    ComprehensiveInsurance(String registrationNumber, String model, double premium) {
        super(registrationNumber, model);
        this.premium = premium;
    }

    void displayPolicy() {
        super.displayVehicle();
        System.out.println("Comprehensive premium: Rs. " + premium);
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        new ComprehensiveInsurance("MH-12-AB-1234", "Honda City", 18000).displayPolicy();
    }
}
