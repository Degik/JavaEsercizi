package LaboratorioInformaticaMonitor;

import java.util.*;
import java.util.concurrent.locks.*;

public class Professore implements Runnable {
	private int idProfessore;
	
	Random rnd = new Random();
	ReentrantLock lock = new ReentrantLock();
	private Computer[] computers = null;

	public Professore(int idProfessore) {
		this.idProfessore = idProfessore;
	}
	
	public void setComputer(Computer[] computers) {
		this.computers = computers;
	}
	
	public synchronized void run() {
		System.out.println("Professore {" + idProfessore + "}: sto usando tutti i computer");
			for(Computer c1 : computers) {
				try {
					c1.useComputer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Professore {" + idProfessore + "}: smetto di usare tutti i computer");
			for(Computer c2: computers) {
				c2.setInUse(false);
			}
	}
	
	public int getIdProfessore() {
		return idProfessore;
	}
}
