package LaboratorioInformaticaMonitor;

import java.util.*;

public class Computer {
	private int idComputer;
	private boolean inUse = false;
	private final static int maxTime = 100;
	public Computer(int idComputer) {
		this.idComputer = idComputer;
	}
	
	Random rnd = new Random();
	
	public synchronized void useComputer() throws InterruptedException {
		while(inUse) {
			this.wait();
		}
		inUse = true;
		int useTime = rnd.nextInt(maxTime);
		sleep(useTime);
		this.notifyAll();
	}
	
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	
	public boolean getInUse() {
		return inUse;
	}
	
	public int getIdComputer() {
		return idComputer;
	}
	
	public static void sleep(int longMillis) {
		try {
			Thread.sleep(longMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
