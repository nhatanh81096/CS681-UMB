package edu.umb.cs681.hw16;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
    private AtomicBoolean changed = new AtomicBoolean(false);
    private LinkedList<Observer> observers;
    private ReentrantLock lockObs = new ReentrantLock();

    Observable() {
        observers = new LinkedList<>();
    }

    public void addObserver(Observer o) {
        lockObs.lock();
        this.observers.add(o);
        lockObs.unlock();
    }

    public void deleteObserver(Observer o) {
        lockObs.lock();
        this.observers.remove(o);
        lockObs.unlock();
    }

    protected void setChanged() {
        changed.set(true);
    }

    protected void clearChanged() {
        changed.set(false);
    }

    public boolean hasChanged() {
        return changed.get();
    }

    public void notifyObservers(Object obj) {
        Object[] localObjs;

        //COPY TO LOCAL VARIABLE
        if (!hasChanged()) return;
        lockObs.lock();
        localObjs = observers.toArray();
        clearChanged();
        lockObs.unlock();

        //OPEN CALL
        for (Object localObj : localObjs) {
            ((Observer) localObj).update(this, obj);
        }
    }

    public int countObservers() {
        try {
            lockObs.lock();
            return observers.size();
        } finally {
            lockObs.unlock();
        }

    }
}
