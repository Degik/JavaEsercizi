package LaboratorioInformatica;

import java.util.*;
import java.util.concurrent.*;

public class Tutor {
	final static int numComputer = 20;
	final static int maxK = 5;
	
	public static void main(String[] args) {
		int numStudenti, numTesisti, numProfessori;
		Scanner sc = new Scanner(System.in);
		System.out.println("Dammi il numero di studenti");
		numStudenti = sc.nextInt();
		System.out.println("Dammi il numero di tesisti");
		numTesisti = sc.nextInt();
		System.out.println("Dammi il numero di professori");
		numProfessori = sc.nextInt();
		
		long firstTime = System.nanoTime();
		
		Computer[] laboratorio = new Computer[numComputer];
		
		for(int i = 0; i < numComputer; i++) {
			Computer computer = new Computer(i);
			laboratorio[i] = computer;
		}
		
		List<Runnable> originalList = new ArrayList<>();
		for(int i = 0; i < numStudenti; i++) {
			Studente studente = new Studente(i);
			originalList.add(studente);
		}
		for(int i = 0; i < numProfessori; i++) {
			Professore professore = new Professore(i);
			originalList.add(professore);
		}
		for(int i = 0; i < numTesisti; i++) {
			Tesista tesista = new Tesista(i);
			originalList.add(tesista);
		}
		
		/*
		 * Avvengono k ripetzioni
		 * I professori usano tutto il laboratorio
		 * I tesisti richiedono l'accesso ad un computer specifico
		 * Gli studenti prendono usano qualsiasi computer
		 */
		
		Random rnd = new Random();
		ExecutorService poolThread = Executors.newCachedThreadPool();
		
		int K = rnd.nextInt(maxK);
		int tesistaComputer = 5;
		
		for(int i = 0; i < K; i++) {
			List<Runnable> currentList = new ArrayList<>(originalList);
			while(!currentList.isEmpty()) {
				Iterator<Runnable> iterator = currentList.iterator();
				while(iterator.hasNext()) {
					Runnable l = iterator.next();
					switch(indentifyType(l)) {
					case "Professore":
						if(checkAllComputers(laboratorio)) {
							Professore p = (Professore) l;
							p.setComputer(laboratorio);
							poolThread.execute(l);
							iterator.remove();
						}else {
							System.out.println("Professore {" + l + "}: sala occupata");
						}
						break;
					case "Tesista":
						Computer computer = checkComputer(laboratorio,tesistaComputer);
						if(computer!=null) {
							Tesista t = (Tesista) l;
							t.setComputer(computer);
							poolThread.execute(l);
							iterator.remove();
						}else {
							System.out.println("Tesista {" + l + "}: computer occupato");
						}
						break;
					case "Studente":
						Computer computerStu = oneFreeComputer(laboratorio);
						if(computerStu!=null) {
							Studente s = (Studente) l;
							s.setComputer(computerStu);
							poolThread.execute(l);
							iterator.remove();
						}else {
							System.out.println("Studente {" + l + "}: sala occupata");
						}
						break;
					default:
						System.out.println("Tipo non valido");
						break;
					}
				}
			}
		}
		poolThread.shutdown();
		long secondTime = System.nanoTime() - firstTime;
		System.out.println(secondTime);
	}

	public static Computer oneFreeComputer(Computer[] laboratorio) {
		for(Computer c : laboratorio) {
			if(!c.getInUse()) {
				return c;
			}
		}
		return null;
	}
	
	public static Computer checkComputer(Computer[] laboratorio, int index) {
		for(Computer c : laboratorio) {
			if(c.getIdComputer() == index) {
				if(!c.getInUse()) {
					return c;
				}
				break;
			}
		}
		return null;
	}
	
	public static boolean checkAllComputers(Computer[] laboratorio) {
		boolean res = true;
		for(Computer k : laboratorio) {
			if(k.getInUse()) {
				res = false;
				break;
			}
		}
		return res;
	}
	
	public static String indentifyType(Runnable task) {
		String result;
		if(task instanceof Professore) {
			result = "Professore";
		}else if(task instanceof Tesista) {
			result = "Tesista";
		}else {
			result = "Studente";
		}
		return result;
	}
}
