package PiGreco;

import java.lang.Math;

public class ThreadPiGreco implements Runnable{
	boolean finish = false;
	private double accuracy;
	private Thread th;
	
	@Override
	public void run() {
		double diff;
		double pi = 1;
		double mathpi = Math.PI;
		int num = 1;
		boolean sot = false;
		while(!finish) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("ThreadPiGreco chiuso");
				finish = true;
			}
			if(sot) {
				pi = pi-(4/num);
				sot = false;
			}else {
				pi = pi+(4/num);
				sot = true;
			}
			num = num + 2;
			diff = pi - mathpi;
			if(Math.abs(diff)<accuracy) {
				try {
					th.interrupt();
				} catch(Exception e) {
					e.printStackTrace();
				}
				if(!Thread.currentThread().isInterrupted()) {
					System.out.println("Serie completata: " + diff + "  " + pi);
				}
				finish = true;
			}
		}
		
	}
	
	public void setAccuracy(double accuracy) {
		this.accuracy=accuracy;
	}
	
	public void SetThread(Thread th) {
		this.th=th;
	}
}
