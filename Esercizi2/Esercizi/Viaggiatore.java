package Esercizi;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Viaggiatore implements Runnable {
	private static int max_rand = 1000;
	private int id;
	
	//
	public Viaggiatore(int id) {
		this.id = id;
	}
	Random rnd = new Random();
	public void run() {
		System.out.println("Viaggiatore {" + id + "}: sto acquistando un biglietto");
		sleep(rnd.nextInt(max_rand+1));
		System.out.println("Viaggiatore {" + id + "}: ho acquistato il biglietto");
	}
	
	private static void sleep(int longMillis) {
		try {
			Thread.sleep(longMillis);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}