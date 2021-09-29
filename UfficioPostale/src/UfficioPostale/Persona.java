package UfficioPostale;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Persona implements Runnable {
	private static final int max_wainting_time = 3000;
	private int id;
	
	Random rnd = new Random();
	BlockingQueue<Runnable> queue = null;
	//
	public Persona(int id) {
		this.id = id;
	}
	
	public Persona(int id, BlockingQueue<Runnable> queue) {
		this.id = id;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		System.out.println("Persona {" + id + "}: sono allo sportello");
		sleep(rnd.nextInt(max_wainting_time+1));
		System.out.println("Persona {" + id + "}: sto uscendo dall'ufficio postale");
	}
	
	private static void sleep(int longMillis) {
		try {
			Thread.sleep(longMillis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
