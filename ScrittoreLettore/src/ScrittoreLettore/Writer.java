package ScrittoreLettore;

public class Writer implements Runnable {
	private int id;
	
	private Counter counter = null;
	
	public Writer(int id, Counter counter) {
		this.id = id;
		this.counter = counter;
	}
	
	public void run() {
		counter.increment();
		System.out.println("Writer {" + id + "} ho incrementato");
	}
}
