package LaboratorioInformatica;

import java.util.*;

public class Computer {
	private int idComputer;
	private boolean inUse = false;
	private final static int maxTime = 100;
	
	public Computer(int idComputer) {
		this.idComputer = idComputer;
	}
	
	Random rnd = new Random();
	
	public void useComputer() {
		inUse = true;
		int useTime = rnd.nextInt(maxTime);
		sleep(useTime);
		inUse = false;
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
