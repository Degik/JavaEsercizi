package LaboratorioInformatica;

import java.util.concurrent.locks.*;

public class Tesista implements Runnable {
	private int idTesista;
	
	Computer computer = null;
	ReentrantLock lock = new ReentrantLock();
	
	public Tesista(int idTesista) {
		this.idTesista = idTesista;
	}
	
	public Tesista(int idTesista, Computer computer) {
		this.idTesista = idTesista;
		this.computer = computer;
	}
	
	public int getIdTesista() {
		return idTesista;
	}
	
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	public void run() {
		try {
			lock.lock();
			System.out.println("Tesista {" + idTesista + "}: sto usando il computer {" + computer.getIdComputer() + "}");
			computer.useComputer();
			System.out.println("Tesitsa {" + idTesista + "}: smetto di usare il computer }" + computer.getIdComputer() + "}");
		} finally {
			lock.unlock();
		}
	}
}
