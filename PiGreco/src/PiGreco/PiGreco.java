package PiGreco;

import java.util.Locale;
import java.util.Scanner;

public class PiGreco {
	public static void main(String[] args) {
		ThreadPiGreco thp = new ThreadPiGreco();
		ThreadTime tht = new ThreadTime();
		Scanner sc = new Scanner(System.in);
		//
		System.out.println("Inserisci tempo in secondi: ");
		int time = sc.nextInt();
		tht.setTime(time);
		Thread th1 = new Thread(tht);
		//
		System.out.println();
		System.out.println("Inserisci accuracy");
		//
		sc.useLocale(Locale.ENGLISH);
		double accuracy = sc.nextDouble();
		//
		thp.setAccuracy(accuracy);
		Thread th2 = new Thread(thp);
		sc.close();
		//
		tht.setThread(th2); // Competitor Thread 2
		thp.SetThread(th1); // Competitor Thread 1
		//
		System.out.println();
		System.out.println();
		th1.start();
		th2.start();
	}

}
