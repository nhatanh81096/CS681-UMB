package edu.umb.cs681.hw17;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private ConcurrentHashMap<Path, Integer> countMap = new ConcurrentHashMap<>();
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
        countMap.compute(path, (Path p, Integer count) -> count==null? 1: ++count);
    }

    public int getCount(Path path) {
        Integer count = countMap.get(path);
        return count==null? 0 : count;
    }
}
