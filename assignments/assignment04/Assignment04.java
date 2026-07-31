public class Assignment04 {
    public static void main(String[] args) {
        String firstMark = "78";
        String secondMark = "86";

        Integer mark1 = Integer.valueOf(firstMark);
        Integer mark2 = Integer.valueOf(secondMark);
        int total = mark1 + mark2;

        Double salary = Double.valueOf("25000.50");
        Double bonus = Double.valueOf("2000.00");

        System.out.println("Total marks: " + total);
        if (salary >= 0 && bonus >= 0) {
            System.out.println("Net salary: " + (salary + bonus));
        } else {
            System.out.println("Salary and bonus must be positive.");
        }
    }
}
