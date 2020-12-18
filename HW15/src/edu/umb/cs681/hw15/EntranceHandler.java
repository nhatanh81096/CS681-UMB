package edu.umb.cs681.hw15;

public class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean done = false;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone() {
        done = true;
    }

    public void run() {
        while (true) {
            if (done) break;

            monitor.enter();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }
}
