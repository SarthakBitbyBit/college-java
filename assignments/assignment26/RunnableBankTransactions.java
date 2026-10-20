public class RunnableBankTransactions {
    static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public synchronized void deposit(double amount, String workerName) {
            System.out.println(workerName + " depositing: " + amount);
            balance += amount;
            System.out.println(workerName + " deposit completed. current balance: " + balance);
        }

        public synchronized void withdraw(double amount, String workerName) {
            System.out.println(workerName + " requesting withdrawal: " + amount);
            if (balance >= amount) {
                balance -= amount;
                System.out.println(workerName + " withdrawal approved. current balance: " + balance);
            } else {
                System.out.println(workerName + " withdrawal rejected: insufficient balance (available: " + balance + ")");
            }
        }

        public synchronized double getBalance() {
            return balance;
        }
    }

    static class DepositTask implements Runnable {
        private final BankAccount account;
        private final double[] amounts;

        public DepositTask(BankAccount account, double[] amounts) {
            this.account = account;
            this.amounts = amounts;
        }

        @Override
        public void run() {
            for (double amt : amounts) {
                account.deposit(amt, Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    static class WithdrawTask implements Runnable {
        private final BankAccount account;
        private final double[] amounts;

        public WithdrawTask(BankAccount account, double[] amounts) {
            this.account = account;
            this.amounts = amounts;
        }

        @Override
        public void run() {
            for (double amt : amounts) {
                account.withdraw(amt, Thread.currentThread().getName());
                try {
                    Thread.sleep(130);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("starting runnable bank transactions simulation");
        BankAccount account = new BankAccount(2000.0);
        System.out.println("initial account balance: " + account.getBalance());

        double[] deposits = {400.0, 600.0, 500.0};
        double[] withdrawals = {800.0, 1500.0, 900.0, 500.0};

        Thread depositWorker = new Thread(new DepositTask(account, deposits), "deposit-worker");
        Thread withdrawWorker = new Thread(new WithdrawTask(account, withdrawals), "withdraw-worker");

        depositWorker.start();
        withdrawWorker.start();

        try {
            depositWorker.join();
            withdrawWorker.join();
        } catch (InterruptedException e) {
            System.out.println("main thread interrupted");
        }

        System.out.println("final verified balance: " + account.getBalance());
    }
}
