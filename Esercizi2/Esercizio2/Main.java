package Esercizio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	private static final int max_esp = 50;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ExecutorService poolThread = Executors.newCachedThreadPool();
		
		int base;
		System.out.println("Inserisci base");
		base = sc.nextInt();
		List<Future <Double>> list = new ArrayList<>();
		for(int i = 2; i <= max_esp; i++) {
			list.add(poolThread.submit(new Power(i,base)));
		}
		double result = 0;
		try {
			for(Future<Double> f: list) {
				result += f.get();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		poolThread.shutdown();
		System.out.println("Risultato: " + result);
	}

}
