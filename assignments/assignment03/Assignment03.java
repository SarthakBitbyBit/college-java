class Calculator {
    static int calculationCount = 0;

    int add(int a, int b) {
        calculationCount++;
        return a + b;
    }

    double add(double a, double b) {
        calculationCount++;
        return a + b;
    }

    static void showCalculationCount() {
        System.out.println("Calculations: " + calculationCount);
    }
}

public class Assignment03 {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Integer addition: " + calculator.add(10, 20));
        System.out.println("Decimal addition: " + calculator.add(10.5, 2.5));
        Calculator.showCalculationCount();
    }
}
