package UfficioPostale;

import java.util.concurrent.*;
import java.util.*;

public class Ufficio {
	final static int numSportelli = 4;
	final static int kElements = 10;
	final static int terminationTime = 5000;
	final static int people = 15;
	
	public static void main(String[] args) {
		/*
		 * Gestire le code di attesa
		 * Sono due 
		 * Una coda di tipo BlockingQueue
		 * Seconda coda di tipo Array che rapressenta la prima sala
		 */
		BlockingQueue<Runnable> blkQueue = new LinkedBlockingDeque<Runnable>(); // Prima sala
		BlockingQueue<Runnable> blkArrayK = new ArrayBlockingQueue<Runnable>(kElements); // Seconda sala
		// Sportelli
		
		ExecutorService poolThread = new ThreadPoolExecutor(
				numSportelli,
				numSportelli,
				terminationTime,
				TimeUnit.MILLISECONDS,
				blkArrayK,
				new ThreadPoolExecutor.AbortPolicy());
		
		for(int i = 0; i < people; i++) {
			Persona persona = new Persona(i);
			try {
				poolThread.execute(persona);
			} catch(RejectedExecutionException e) {
				System.out.println("Persona {" + i + "}: Sala piccola piena resto nella grande");
				blkQueue.add(persona);
			}
		}
		while(!blkQueue.isEmpty()) {
			for(Runnable task: blkQueue) {
				try {
					poolThread.execute(task);
					if(blkQueue.remove(task)) {
						System.out.println("Persona {" + task + "}: sto lasciando la prima sala");
					}
				} catch (RejectedExecutionException e) {
					System.out.println("Persona {" + task + "}: sala piccola ancora piena");
				}
			}
		}
		poolThread.shutdown();
	}
	
	public static void sleep(int longMillis) {
		try {
			Thread.sleep(longMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
