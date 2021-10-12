package ScrittoreLettore;

import java.util.concurrent.*;

public class Union {
	final static int numWriter = 20000;
	final static int numReader = 20000;
	final static long terminationTime = 2;
	
	public static void main(String[] args) {
		
		Counter counterReadWrite = new CounterReadWrite();
		Counter counterReentrant = new ReentrantCounter();
		ExecutorService poolThread1 = Executors.newCachedThreadPool();
		long firstTime = executionTime(poolThread1, counterReadWrite);
		ExecutorService poolThread2 = Executors.newCachedThreadPool();
		long secondTime = executionTime(poolThread2, counterReentrant);
		
		long finalTime = secondTime - firstTime;
		
		System.out.println("La differenza dei tempi è: " + finalTime);
		
	}
	
	public static long executionTime(ExecutorService poolThread, Counter counter) {
		long firstTime = System.nanoTime();
		
		for(int i = 0; i < numWriter; i++)
			poolThread.execute(new Writer(i, counter));
		for(int i = 0; i < numReader; i++)
			poolThread.execute(new Reader(i, counter));
		
		poolThread.shutdown();
		try {
			poolThread.awaitTermination(terminationTime, TimeUnit.SECONDS);
			poolThread.shutdownNow();
		} catch(InterruptedException e) {
			poolThread.shutdownNow();
		}
		// Terminato il poolThread salvo il tempo
		long secondTime = System.nanoTime();
		
		return (secondTime-firstTime);
	}
}
