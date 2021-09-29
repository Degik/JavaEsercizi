package Esercizio2;

import java.util.concurrent.Callable;

public class Power implements Callable<Double>{
	private int esp;
	private int base;
	
	public Power(int esp, int base) {
		this.esp = esp;
		this.base = base;
	}
	
	public Double call() {
		double result = Math.pow(base, esp);
		System.out.println("Esecuzione {" + base + "}^{" + esp + "}" + " in {"+ Thread.currentThread().getName() + "}");
		return result;
	}
}
