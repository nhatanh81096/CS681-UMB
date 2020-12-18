package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount implements BankAccount {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount) {
        lock.lock();
        System.out.println("Lock acquired");
        System.out.println(Thread.currentThread().getId() +
                " (d): current balance: " + balance);
        while (balance >= 300) {
            try {
                System.out.println(Thread.currentThread().getId() +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                return;
            }
        }
        balance += amount;
        System.out.println(Thread.currentThread().getId() +
                " (d): new balance: " + balance);
        sufficientFundsCondition.signalAll();
        lock.unlock();
        System.out.println("Lock released");
    }

    public void withdraw(double amount) {
        lock.lock();
        System.out.println("Lock acquired");
        System.out.println(Thread.currentThread().getId() +
                " (w): current balance: " + balance);
        while (balance <= 0) {
            try {
                System.out.println(Thread.currentThread().getId() +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                return;
            }
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getId() +
                " (w): new balance: " + balance);
        belowUpperLimitFundsCondition.signalAll();
        lock.unlock();
        System.out.println("Lock released");
    }

    public static void main(String[] args) {
        ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();

        DepositRunnable d1 = new DepositRunnable(bankAccount);
        DepositRunnable d2 = new DepositRunnable(bankAccount);
        DepositRunnable d3 = new DepositRunnable(bankAccount);
        DepositRunnable d4 = new DepositRunnable(bankAccount);

        WithdrawRunnable w1 = new WithdrawRunnable(bankAccount);
        WithdrawRunnable w2 = new WithdrawRunnable(bankAccount);
        WithdrawRunnable w3 = new WithdrawRunnable(bankAccount);
        WithdrawRunnable w4 = new WithdrawRunnable(bankAccount);

        Thread td1 = new Thread(d1); td1.start();
        Thread td2 = new Thread(d2); td2.start();
        Thread td3 = new Thread(d3); td3.start();
        Thread td4 = new Thread(d4); td4.start();

        Thread tw1 = new Thread(w1); tw1.start();
        Thread tw2 = new Thread(w2); tw2.start();
        Thread tw3 = new Thread(w3); tw3.start();
        Thread tw4 = new Thread(w4); tw4.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        d1.setDone(); d2.setDone(); d3.setDone(); d4.setDone();
        w1.setDone(); w2.setDone(); w3.setDone(); w4.setDone();

        td1.interrupt(); td2.interrupt(); td3.interrupt(); td4.interrupt();
        tw1.interrupt(); tw2.interrupt(); tw3.interrupt(); tw4.interrupt();

        try {
            td1.join(); td2.join(); td3.join(); td4.join();
            tw1.join(); tw2.join(); tw3.join(); tw4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
