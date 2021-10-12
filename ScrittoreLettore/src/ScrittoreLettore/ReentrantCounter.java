package ScrittoreLettore;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantCounter extends Counter{
	ReentrantLock lock = new ReentrantLock();
	@Override
	void increment() {
		try {
			lock.lock();
			counter++;
		} finally {
			lock.unlock();
		}
	}

	@Override
	int get() {
		try {
			lock.lock();
			return counter;
		} finally {
			lock.unlock();
		}
	}
	
}
