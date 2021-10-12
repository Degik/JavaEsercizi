package LaboratorioInformatica;

import java.util.*;
import java.util.concurrent.locks.*;

public class Studente implements Runnable {
	final static int maxTime = 3000;
	private int idStudente;
	
	ReentrantLock lock = new ReentrantLock();
	Computer computer = null;
	
	public Studente(int idStudente) {
		this.idStudente = idStudente;
	}
	
	public Studente(int idStudente, Computer computer) {
		this.idStudente = idStudente;
		this.computer = computer;
	}
	
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void run() {
		try {
			lock.lock();
			System.out.println("Studente {" + idStudente + "}: sto usando il computer {" + computer.getIdComputer() + "}");
			computer.useComputer();
			System.out.println("Studente {" + idStudente + "}: sto lasciando il computer {" + computer.getIdComputer() + "}");
		} finally {
			lock.unlock();
		}
	}
}
