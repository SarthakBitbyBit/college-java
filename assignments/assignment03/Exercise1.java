package assignments.assignment03;

class Calculator {
    private static int calculationCount;

    int add(int first, int second) {
        calculationCount++;
        return first + second;
    }

    double add(double first, double second) {
        calculationCount++;
        return first + second;
    }

    static void showCalculationCount() {
        System.out.println("Calculations: " + calculationCount);
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Integer sum: " + calculator.add(10, 20));
        System.out.println("Decimal sum: " + calculator.add(10.5, 2.5));
        Calculator.showCalculationCount();
    }
}
