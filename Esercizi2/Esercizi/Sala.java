package Esercizi;

import java.util.concurrent.*;

public class Sala {
	private static int num_queue = 10;
	private static int n_thread = 5;
	private static int termination_time = 1000;
	private static int max_visitors = 50;
	private static int waiting_time = 50;
	
	public static void main(String[] args) {
		ExecutorService poolThread = new ThreadPoolExecutor(n_thread,n_thread,termination_time,TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(num_queue));
		for(int i = 0; i < max_visitors; i++) {
			try {
				poolThread.execute(new Viaggiatore(i));
				sleep(waiting_time);
			} catch(RejectedExecutionException e) {
				System.out.println("Viaggiatore {" + i +"}: sala esaurita");
			}
		}
		
		poolThread.shutdown();
		
	}
	
	private static void sleep(int longMillis) {
		try {
			Thread.sleep(longMillis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}