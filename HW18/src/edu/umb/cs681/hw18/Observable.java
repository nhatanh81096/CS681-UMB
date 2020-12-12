package edu.umb.cs681.hw18;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Observable {
    private AtomicBoolean changed = new AtomicBoolean(false);
    private ConcurrentLinkedQueue<Observer> observers;

    Observable() {
        observers = new ConcurrentLinkedQueue<>();
    }

    public void addObserver(Observer o) {
        this.observers.offer(o);
    }

    public void deleteObserver(Observer o) {
        this.observers.remove(o);
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
        if (hasChanged()) {
            observers.forEach(observer -> observer.update(this, obj));
            clearChanged();
        }
    }

    public int countObservers() {
        return observers.size();
    }
}
