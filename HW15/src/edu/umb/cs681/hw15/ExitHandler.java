package edu.umb.cs681.hw15;

public class ExitHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean done = false;

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone() {
        done = true;
    }

    public void run() {
        while (true) {
            if (done) break;

            monitor.exit();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }
}
