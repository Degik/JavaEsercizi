package PiGreco;

public class ThreadTime implements Runnable{
	private long time;
	boolean finish = false;
	private Thread th;
	
	public void run() {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			System.out.println();
		}
		if(Thread.currentThread().isInterrupted()) {
			System.out.println("ThreadTime chiuso");
		}else {
			th.interrupt();
			System.out.println("Tempo scaduto");
		}
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	// Set Competitor Thread
	public void setThread(Thread th) {
		this.th = th;
	}
}
