package edu.umb.cs681.hw11;

public class MultiThread implements Runnable {
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }

    public static void main(String[] args) {
        MultiThread m1 = new MultiThread();
        MultiThread m2 = new MultiThread();
        MultiThread m3 = new MultiThread();
        MultiThread m4 = new MultiThread();
        new Thread(m1).start();
        new Thread(m2).start();
        new Thread(m3).start();
        new Thread(m4).start();
    }
}
