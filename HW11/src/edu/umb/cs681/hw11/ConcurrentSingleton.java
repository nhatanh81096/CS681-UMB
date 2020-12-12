package edu.umb.cs681.hw11;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton {
    private ConcurrentSingleton() {}
    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>(null);

    public static ConcurrentSingleton getInstance() {
        if (instance.get() == null) {
            instance.set(new ConcurrentSingleton());
        }
        return instance.get();
    }
}
