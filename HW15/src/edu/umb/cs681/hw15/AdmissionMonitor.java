package edu.umb.cs681.hw15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AdmissionMonitor {
    private int currentVisitor = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition belowMaxNoVisitorsCondition = lock.newCondition();
    private Condition aboveMinNoVisitorsCondition = lock.newCondition();

    public int countCurrentVisitor() {
        try {
            lock.lock();
            return currentVisitor;
        } finally {
            lock.unlock();
        }
    }

    public void enter() {
        lock.lock();
        System.out.println("Lock acquired");
        System.out.println(Thread.currentThread().getId() +
                " (in): Current number of visitor: " + currentVisitor);
        while (currentVisitor >= 5) {
            try {
                System.out.println(Thread.currentThread().getId() +
                        " (in): Too many visitor. Please wait for a while!");
                belowMaxNoVisitorsCondition.await();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                return;
            }
        }
        currentVisitor++;
        System.out.println(Thread.currentThread().getId() +
                " (in): New number of visitor: " + currentVisitor);
        aboveMinNoVisitorsCondition.signalAll();
        lock.unlock();
        System.out.println("Lock released");
    }

    public void exit() {
        lock.lock();
        System.out.println("Lock acquired");
        System.out.println(Thread.currentThread().getId() +
                " (out): Current number of visitor: " + currentVisitor);
        while (currentVisitor <= 0) {
            try {
                System.out.println(Thread.currentThread().getId() +
                        " (out): There is no visitor inside left!");
                aboveMinNoVisitorsCondition.await();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                return;
            }
        }
        currentVisitor--;
        System.out.println(Thread.currentThread().getId() +
                " (out): New number of visitor: " + currentVisitor);
        belowMaxNoVisitorsCondition.signalAll();
        lock.unlock();
        System.out.println("Lock released");
    }

    public static void main(String[] args) {
        AdmissionMonitor monitor = new AdmissionMonitor();

        EntranceHandler in1 = new EntranceHandler(monitor);
        EntranceHandler in2 = new EntranceHandler(monitor);
        EntranceHandler in3 = new EntranceHandler(monitor);
        EntranceHandler in4 = new EntranceHandler(monitor);
        EntranceHandler in5 = new EntranceHandler(monitor);
        EntranceHandler in6 = new EntranceHandler(monitor);


        ExitHandler ou1 = new ExitHandler(monitor);
        ExitHandler ou2 = new ExitHandler(monitor);
        ExitHandler ou3 = new ExitHandler(monitor);
        ExitHandler ou4 = new ExitHandler(monitor);
        ExitHandler ou5 = new ExitHandler(monitor);
        ExitHandler ou6 = new ExitHandler(monitor);


        Thread ti1 = new Thread(in1); ti1.start();
        Thread ti2 = new Thread(in2); ti2.start();
        Thread ti3 = new Thread(in3); ti3.start();
        Thread ti4 = new Thread(in4); ti4.start();
        Thread ti5 = new Thread(in5); ti5.start();
        Thread ti6 = new Thread(in6); ti6.start();

        Thread to1 = new Thread(ou1); to1.start();
        Thread to2 = new Thread(ou2); to2.start();
        Thread to3 = new Thread(ou3); to3.start();
        Thread to4 = new Thread(ou4); to4.start();
        Thread to5 = new Thread(ou5); to5.start();
        Thread to6 = new Thread(ou6); to6.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }

        in1.setDone(); in2.setDone(); in3.setDone(); in4.setDone(); in5.setDone(); in6.setDone();
        ou1.setDone(); ou2.setDone(); ou3.setDone(); ou4.setDone(); ou5.setDone(); ou6.setDone();

        ti1.interrupt(); ti2.interrupt(); ti3.interrupt(); ti4.interrupt(); ti5.interrupt(); ti6.interrupt();
        to1.interrupt(); to2.interrupt(); to3.interrupt(); to4.interrupt(); to5.interrupt(); to6.interrupt();

        try {
            ti1.join(); ti2.join(); ti3.join(); ti4.join(); ti5.join(); ti6.join();
            to1.join(); to2.join(); to3.join(); to4.join(); to5.join(); to6.join();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
