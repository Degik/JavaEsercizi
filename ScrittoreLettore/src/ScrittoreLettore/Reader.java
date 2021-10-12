package ScrittoreLettore;

public class Reader implements Runnable{
	private int id;
	private Counter counter = null;
	
	public Reader(int id, Counter counter) {
		this.id = id;
		this.counter = counter;
	}
	
	public void run() {
		int elem = counter.get();
		System.out.println("Reader {" + id + "}: prendo counter: " + elem);
	}
	
}
