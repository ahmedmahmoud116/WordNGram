package pkj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarkovRunner {

	public MarkovRunner() {
		
	}
	
	public void runModel(IMarkovModel markov,String text, int size,int seed) {
		markov.setRandom(seed);
		markov.setTraining(text);
	    System.out.println("running with " + markov);
		for(int i = 0;i<3;i++) {
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}
	
	public void runModel(IMarkovModel markov,String text, int size) {
		
		markov.setTraining(text);
	    System.out.println("running with " + markov);
		for(int i = 0;i<3;i++) {
			String st = markov.getRandomText(size);
			printOut(st);
		}
	}
	
	public void runMarkov() throws FileNotFoundException {
		DirectoryResource dr = new DirectoryResource();
		Scanner sc;
		StringBuilder sb = new StringBuilder();
		
		File f = new File("confucius.txt");
		sc = new Scanner(f);
		while(sc.hasNext()) {
			String s = sc.nextLine();
			s = s + " ";
			sb.append(s);
		}
		sc.close();
		
//		for(File f:dr.selectedFiles()) {
//			sc = new Scanner(f);
//			while(sc.hasNext()) {
//				sb.append(sc.next());
//			}
//		}
//		sc.close();
		
		String st = sb.toString();
		
		int size = 120;
		int seed = 844;
//		st = "this is just a test yes this is a simple test";
		
		MarkovWordOne mo = new MarkovWordOne();
//		runModel(mo, st, size, seed);
		
		MarkovWordTwo mt = new MarkovWordTwo();
//		runModel(mt, st, size, seed);
		
		MarkovWord mw = new MarkovWord(5);
		runModel(mw, st, size, seed);
		
		EfficientMarkovWord em = new EfficientMarkovWord(2);
//		runModel(em, st, size, seed);
	}
	
	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int linesize = 0;
		System.out.println("---------------------------------------------------------");
		for(int i = 0;i<words.length;i++) {
			System.out.print(words[i] + " ");
			linesize += words[i].length() + 1;
			if(linesize>60) { //range of line size
				System.out.println();
				linesize = 0;
			}
		}
		System.out.println("\n---------------------------------------------------------");
	}
	
	public void testHashMap() throws FileNotFoundException {
		
		DirectoryResource dr = new DirectoryResource();
		Scanner sc;
		StringBuilder sb = new StringBuilder();
		
		File f = new File("confucius.txt");
		sc = new Scanner(f);
		while(sc.hasNext()) {
			String s = sc.nextLine();
			s = s + " ";
			sb.append(s);
		}
		sc.close();
		
//		for(File f:dr.selectedFiles()) {
//			sc = new Scanner(f);
//			while(sc.hasNext()) {
//				sb.append(sc.next());
//			}
//		}
//		sc.close();
		
		String st = sb.toString();
		
		st = "this is a test yes this is really a test yes a test this is wow";
		
		int seed = 42;
		int size = 50;
		
		EfficientMarkovWord em = new EfficientMarkovWord(2);
		runModel(em, st, size, seed);
	}
	
	public void compareMethods() throws FileNotFoundException {
		
		DirectoryResource dr = new DirectoryResource();
		Scanner sc;
		StringBuilder sb = new StringBuilder();
		
		File f = new File("hawthorne.txt");
		sc = new Scanner(f);
		while(sc.hasNext()) {
			String s = sc.nextLine();
			s = s + " ";
			sb.append(s);
		}
		sc.close();
		
//		for(File f:dr.selectedFiles()) {
//			sc = new Scanner(f);
//			while(sc.hasNext()) {
//				sb.append(sc.next());
//			}
//		}
//		sc.close();
		
		String st = sb.toString();
		
		int seed = 42;
		int size = 100;
		
		double begin = System.nanoTime();
//		for(int i = 0 ; i < 3 ; i++) {
//		MarkovWord mm = new MarkovWord(2);
//		runModel(mm, st, size, seed);
//		}
		double end = System.nanoTime();
		double stime = (end-begin)/1e9;
		
		
		begin = System.nanoTime();
		for(int i = 0 ; i < 1 ; i++) {
		EfficientMarkovWord em = new EfficientMarkovWord(2);
//		runModel(em, st, size, seed)
		em.setTraining(st);
		}
		end = System.nanoTime();
		double ttime = (end-begin)/1e9;
		
		System.out.printf("Normal:Efficient\t%3.2f\t%3.2f\n",stime,ttime);
	}
}
