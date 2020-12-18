package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private HashMap<Path, Integer> countMap = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock sLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {}

    public static AccessCounter getInstance() {
        sLock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            sLock.unlock();
        }
    }

    public void increment(Path path) {
        lock.lock();
        Integer oldCount = countMap.get(path);
        int newCount = oldCount == null? 1 : oldCount+1;
        countMap.put(path, newCount);
        lock.unlock();
    }

    public int getCount(Path path) {
        try {
            lock.lock();
            Integer count = countMap.get(path);
            return count==null? 0 : count;
        } finally {
            lock.unlock();
        }
    }
}
