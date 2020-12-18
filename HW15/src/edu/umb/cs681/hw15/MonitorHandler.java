package edu.umb.cs681.hw15;

public class MonitorHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean done = false;

    public void setDone() {
        done = true;
    }

    public MonitorHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            if (done) break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
