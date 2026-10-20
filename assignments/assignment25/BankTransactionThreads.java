public class BankTransactionThreads {
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public synchronized void deposit(double amount, String threadName) {
            System.out.println(threadName + " depositing: " + amount);
            balance += amount;
            System.out.println(threadName + " deposit completed. current balance: " + balance);
        }

        public synchronized void withdraw(double amount, String threadName) {
            System.out.println(threadName + " attempting to withdraw: " + amount);
            if (balance >= amount) {
                balance -= amount;
                System.out.println(threadName + " withdrawal successful. current balance: " + balance);
            } else {
                System.out.println(threadName + " withdrawal failed: insufficient balance (available: " + balance + ")");
            }
        }

        public synchronized double getBalance() {
            return balance;
        }
    }

    static class DepositThread extends Thread {
        private final BankAccount account;
        private final double[] amounts;

        public DepositThread(String name, BankAccount account, double[] amounts) {
            super(name);
            this.account = account;
            this.amounts = amounts;
        }

        @Override
        public void run() {
            for (double amt : amounts) {
                account.deposit(amt, getName());
            }
        }
    }

    static class WithdrawThread extends Thread {
        private final BankAccount account;
        private final double[] amounts;

        public WithdrawThread(String name, BankAccount account, double[] amounts) {
            super(name);
            this.account = account;
            this.amounts = amounts;
        }

        @Override
        public void run() {
            for (double amt : amounts) {
                account.withdraw(amt, getName());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("starting multithreaded bank transaction simulation");
        BankAccount account = new BankAccount(1000.0);
        System.out.println("initial account balance: " + account.getBalance());

        double[] deposits = {500.0, 300.0, 700.0};
        double[] withdrawals = {400.0, 1200.0, 600.0, 800.0};

        DepositThread depositThread = new DepositThread("deposit-thread", account, deposits);
        WithdrawThread withdrawThread = new WithdrawThread("withdraw-thread", account, withdrawals);

        depositThread.start();
        withdrawThread.start();

        try {
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e) {
            System.out.println("main thread interrupted");
        }

        System.out.println("final verified account balance: " + account.getBalance());
    }
}
