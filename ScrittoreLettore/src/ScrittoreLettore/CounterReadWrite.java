package ScrittoreLettore;

import java.util.concurrent.locks.*;

public class CounterReadWrite extends Counter{
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private Lock readLock = lock.readLock();
	private Lock writeLock = lock.writeLock();
	
	
	public void increment() {
		try {
			writeLock.lock();
			counter++;
		} finally {
			writeLock.unlock();
		}
	}
	
	public int get() {
		try {
			readLock.lock();
			return counter;
		} finally {
			readLock.unlock();
		}
	}
}
