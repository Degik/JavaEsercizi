package LaboratorioInformatica;

import java.util.*;
import java.util.concurrent.locks.*;

public class Professore implements Runnable {
	private int idProfessore;
	private final static int maxTime = 100;
	
	Random rnd = new Random();
	ReentrantLock lock = new ReentrantLock();
	private Computer[] computers = null;

	public Professore(int idProfessore) {
		this.idProfessore = idProfessore;
	}
	
	public void setComputer(Computer[] computers) {
		this.computers = computers;
	}
	
	public void run() {
		try {
			lock.lock();
			int timeSleep = rnd.nextInt(maxTime);
			System.out.println("Professore {" + idProfessore + "}: sto usando tutti i computer");
			for(Computer c : computers) {
				c.useComputer();
			}
			System.out.println("Professore {" + idProfessore + "}: smetto di usare tutti i computer");
		} finally {
			lock.unlock();
		}
	}
	
	public int getIdProfessore() {
		return idProfessore;
	}
	
	private static void sleep(int longMillis) {
		try {
			Thread.sleep(longMillis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
