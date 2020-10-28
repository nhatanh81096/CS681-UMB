package edu.umb.cs681.hw05;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void run1Thread() {
		// Generator with a single thread
		RunnablePrimeGenerator runnable = new RunnablePrimeGenerator(1L, 2000000L);
		Thread thread = new Thread(runnable);
		thread.start();
		long before = System.currentTimeMillis();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long runningTime = System.currentTimeMillis() - before;
		System.out.println("Time running on 1 thread: " + runningTime);
	}

	public static void run2Threads() {
		// Run on 2 threads
		RunnablePrimeGenerator runnable21 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator runnable22 = new RunnablePrimeGenerator(1000000L, 2000000L);
		Thread thread21 = new Thread(runnable21);
		Thread thread22 = new Thread(runnable22);
		thread21.start(); thread22.start();
		long before = System.currentTimeMillis();
		try {
			thread21.join(); thread22.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long runningTime = System.currentTimeMillis() - before;
		System.out.println("Time running on 2 thread: " + runningTime);
	}

	public static void run4Threads() {
		// Run on 4 threads
		RunnablePrimeGenerator runnable41 = new RunnablePrimeGenerator(1L, 500000L);
		RunnablePrimeGenerator runnable42 = new RunnablePrimeGenerator(500000L, 1000000L);
		RunnablePrimeGenerator runnable43 = new RunnablePrimeGenerator(1000000L, 1500000L);
		RunnablePrimeGenerator runnable44 = new RunnablePrimeGenerator(1500000L, 2000000L);
		Thread thread41 = new Thread(runnable41);
		Thread thread42 = new Thread(runnable42);
		Thread thread43 = new Thread(runnable43);
		Thread thread44 = new Thread(runnable44);
		thread41.start(); thread42.start(); thread43.start(); thread44.start();
		long before = System.currentTimeMillis();
		try {
			thread41.join(); thread42.join(); thread43.join(); thread44.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long runningTime = System.currentTimeMillis() - before;
		System.out.println("Time running on 4 thread: " + runningTime);
	}

	public static void run8Threads() {
		// Run on 8 threads
		RunnablePrimeGenerator runnable81 = new RunnablePrimeGenerator(1L, 250000L);
		RunnablePrimeGenerator runnable82 = new RunnablePrimeGenerator(250000L, 500000L);
		RunnablePrimeGenerator runnable83 = new RunnablePrimeGenerator(500000L, 750000L);
		RunnablePrimeGenerator runnable84 = new RunnablePrimeGenerator(750000L, 1000000L);
		RunnablePrimeGenerator runnable85 = new RunnablePrimeGenerator(1000000L, 1250000L);
		RunnablePrimeGenerator runnable86 = new RunnablePrimeGenerator(1250000L, 1500000L);
		RunnablePrimeGenerator runnable87 = new RunnablePrimeGenerator(1500000L, 1750000L);
		RunnablePrimeGenerator runnable88 = new RunnablePrimeGenerator(1750000L, 2000000L);
		Thread thread81 = new Thread(runnable81);
		Thread thread82 = new Thread(runnable82);
		Thread thread83 = new Thread(runnable83);
		Thread thread84 = new Thread(runnable84);
		Thread thread85 = new Thread(runnable85);
		Thread thread86 = new Thread(runnable86);
		Thread thread87 = new Thread(runnable87);
		Thread thread88 = new Thread(runnable88);
		thread81.start(); thread82.start(); thread83.start(); thread84.start();
		thread85.start(); thread86.start(); thread87.start(); thread88.start();
		long before = System.currentTimeMillis();
		try {
			thread81.join(); thread82.join(); thread83.join(); thread84.join();
			thread85.join(); thread86.join(); thread87.join(); thread88.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long runningTime = System.currentTimeMillis() - before;
		System.out.println("Time running on 8 thread: " + runningTime);
	}

	public static void run16Threads() {
		// Run on 16 threads
		RunnablePrimeGenerator runnable161 = new RunnablePrimeGenerator(1L, 125000L);
		RunnablePrimeGenerator runnable162 = new RunnablePrimeGenerator(125000L, 250000L);
		RunnablePrimeGenerator runnable163 = new RunnablePrimeGenerator(250000L, 375000L);
		RunnablePrimeGenerator runnable164 = new RunnablePrimeGenerator(375000L, 500000L);
		RunnablePrimeGenerator runnable165 = new RunnablePrimeGenerator(500000L, 675000L);
		RunnablePrimeGenerator runnable166 = new RunnablePrimeGenerator(675000L, 750000L);
		RunnablePrimeGenerator runnable167 = new RunnablePrimeGenerator(750000L, 875000L);
		RunnablePrimeGenerator runnable168 = new RunnablePrimeGenerator(875000L, 1000000L);
		RunnablePrimeGenerator runnable169 = new RunnablePrimeGenerator(1000000L, 1125000L);
		RunnablePrimeGenerator runnable1610 = new RunnablePrimeGenerator(1125000L, 1250000L);
		RunnablePrimeGenerator runnable1611 = new RunnablePrimeGenerator(1250000L, 1375000L);
		RunnablePrimeGenerator runnable1612 = new RunnablePrimeGenerator(1375000L, 1500000L);
		RunnablePrimeGenerator runnable1613 = new RunnablePrimeGenerator(1500000L, 1675000L);
		RunnablePrimeGenerator runnable1614 = new RunnablePrimeGenerator(1675000L, 1750000L);
		RunnablePrimeGenerator runnable1615 = new RunnablePrimeGenerator(1750000L, 1875000L);
		RunnablePrimeGenerator runnable1616 = new RunnablePrimeGenerator(1875000L, 2000000L);
		Thread thread161 = new Thread(runnable161);
		Thread thread162 = new Thread(runnable162);
		Thread thread163 = new Thread(runnable163);
		Thread thread164 = new Thread(runnable164);
		Thread thread165 = new Thread(runnable165);
		Thread thread166 = new Thread(runnable166);
		Thread thread167 = new Thread(runnable167);
		Thread thread168 = new Thread(runnable168);
		Thread thread169 = new Thread(runnable169);
		Thread thread1610 = new Thread(runnable1610);
		Thread thread1611 = new Thread(runnable1611);
		Thread thread1612 = new Thread(runnable1612);
		Thread thread1613 = new Thread(runnable1613);
		Thread thread1614 = new Thread(runnable1614);
		Thread thread1615 = new Thread(runnable1615);
		Thread thread1616 = new Thread(runnable1616);
		thread161.start(); thread162.start(); thread163.start(); thread164.start();
		thread165.start(); thread166.start(); thread167.start(); thread168.start();
		thread169.start(); thread1610.start(); thread1611.start(); thread1612.start();
		thread1613.start(); thread1614.start(); thread1615.start(); thread1616.start();
		long before = System.currentTimeMillis();
		try {
			thread161.join(); thread162.join(); thread163.join(); thread164.join();
			thread165.join(); thread166.join(); thread167.join(); thread168.join();
			thread169.join(); thread1610.join(); thread1611.join(); thread1612.join();
			thread1613.join(); thread1614.join(); thread1615.join(); thread1616.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long runningTime = System.currentTimeMillis() - before;
		System.out.println("Time running on 16 thread: " + runningTime);
	}

	public static void main(String[] args) {
		run1Thread();
		run2Threads();
		run4Threads();
		run8Threads();
		run16Threads();
	}

}
