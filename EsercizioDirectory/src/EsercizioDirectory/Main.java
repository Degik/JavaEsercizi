package EsercizioDirectory;

import java.io.*;
import java.util.*;

public class Main {

	static String dir = "directories";
	static String fil = "files";
	
	public static void main(String[] args) {
		LinkedList<File> list = new LinkedList<>();
		
		if(args.length < 1) {
			System.err.println("Lunghezza args errata");
			System.exit(1);
		}
		
		File mainDir = new File(args[0]);
		if(mainDir.isDirectory()) {
			System.err.println("Non è una directory");
			System.exit(1);
		}
		
		FileOutputStream outDir = null;
		FileOutputStream outFil = null;
		try {
			outDir = new FileOutputStream(new File(dir));
			outFil = new FileOutputStream(new File(fil));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		list.add(mainDir);
		try {
			while(!list.isEmpty()) {
				File[] arrayFile = list.removeFirst().listFiles();
				
				for(File f : arrayFile) {
					String name = f.getName() + "\n";
					
					if(f.isDirectory()){
						list.add(f);
						outDir.write(name.getBytes());
					}else {
						outFil.write(name.getBytes());
					}
				}
				
			}
			outDir.close();
			outFil.close();
		} catch(IOException e) {
			System.err.println("Qualcosa è andato storto");
			System.exit(1);
		}
	}

}
