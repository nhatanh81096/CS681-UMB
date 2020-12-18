package edu.umb.cs681.hw14;

public class WithdrawRunnable implements Runnable {
    private BankAccount account;
    private volatile boolean done = false;

    public WithdrawRunnable(BankAccount account) {
        this.account = account;
    }

    public void setDone() {
        done = true;
    }

    public void run() {
        while (true) {
            if (done) break;
            account.withdraw(100);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }
}

