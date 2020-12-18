package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private final static String[] paths = {"./a.html", "./b.html", "./c.html", "./d.html"};

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        while(true) {
            lock.lock();
            try {
                if (done) break;
                Random random = new Random();
                int index = random.nextInt(paths.length);

                Path path = Paths.get(paths[index]);
                AccessCounter.getInstance().increment(path);
            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {
        RequestHandler r1 = new RequestHandler();
        RequestHandler r2 = new RequestHandler();
        RequestHandler r3 = new RequestHandler();
        RequestHandler r4 = new RequestHandler();
        RequestHandler r5 = new RequestHandler();
        RequestHandler r6 = new RequestHandler();
        RequestHandler r7 = new RequestHandler();
        RequestHandler r8 = new RequestHandler();
        RequestHandler r9 = new RequestHandler();
        RequestHandler r10 = new RequestHandler();
        RequestHandler r11 = new RequestHandler();
        RequestHandler r12 = new RequestHandler();

        Thread t1 = new Thread(r1); t1.start();
        Thread t2 = new Thread(r2); t2.start();
        Thread t3 = new Thread(r3); t3.start();
        Thread t4 = new Thread(r4); t4.start();
        Thread t5 = new Thread(r5); t5.start();
        Thread t6 = new Thread(r6); t6.start();
        Thread t7 = new Thread(r7); t7.start();
        Thread t8 = new Thread(r8); t8.start();
        Thread t9 = new Thread(r9); t9.start();
        Thread t10 = new Thread(r10); t10.start();
        Thread t11 = new Thread(r11); t11.start();
        Thread t12 = new Thread(r12); t12.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        r1.setDone(); r2.setDone(); r3.setDone(); r4.setDone();
        r5.setDone(); r6.setDone(); r7.setDone(); r8.setDone();
        r9.setDone(); r10.setDone(); r11.setDone(); r12.setDone();

        t1.interrupt(); t2.interrupt(); t3.interrupt(); t4.interrupt();
        t5.interrupt(); t6.interrupt(); t7.interrupt(); t8.interrupt();
        t9.interrupt(); t10.interrupt(); t11.interrupt(); t12.interrupt();

        try {
            t1.join(); t2.join(); t3.join(); t4.join();
            t5.join(); t6.join(); t7.join(); t8.join();
            t9.join(); t10.join(); t11.join(); t12.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String p: paths) {
            Path path = Paths.get(p);
            System.out.println(p + " get accessed " + AccessCounter.getInstance().getCount(path) + " time(s)");
        }
    }
}
