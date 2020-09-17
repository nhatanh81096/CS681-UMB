package edu.umb.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable {
    private boolean changed = false;
    private LinkedList<Observer> observers;

    Observable() {
        observers = new LinkedList<>();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void deleteObserver(Observer o) {
        this.observers.remove(o);
    }

    protected void setChanged() {
        changed = true;
    }

    protected void clearChanged() {
        changed = false;
    }

    public boolean hasChanged() {
        return changed;
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
