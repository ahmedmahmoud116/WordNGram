package pkj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class EfficientMarkovWord implements IMarkovModel{

	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram, ArrayList<String>> myMap;
	
	public EfficientMarkovWord(int order) {
		myRandom = new Random();
		this.myOrder = order;
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	
	public void setRandom(int seed) {// to generate the same sequence of random number help in testing
		myRandom = new Random(seed);
	}
	
	public void setTraining(String text) {
		myText = text.split("\\s+");
		buildMap();
		printHashMapInfo();
	}
	
	public String getRandomText(int numWords) {
		
		
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder);//i need to pick any index that allow one character substring
		WordGram KGram = new WordGram(myText, index, myOrder);
		sb.append(KGram.toString());
		
		ArrayList<String> follows;
		for(int i = 0;i<numWords;i++) {
			
			if(myMap.containsKey(KGram))
				follows = myMap.get(KGram);
				else {
					System.out.println(i);
					System.out.println("heree");
					System.out.println(KGram);
					follows = getFollow(KGram);
					myMap.put(KGram,follows);
				}

			if(follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String temp = follows.get(index);
			sb.append(temp);
			sb.append(" ");
			
			KGram = KGram.shiftAdd(temp);
			
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
		if(myMap.containsKey(kGram)) {
			return myMap.get(kGram);
		}
		return new ArrayList<String>();
	}
	
	private ArrayList<String> getFollow(WordGram kGram) {
	
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		if(myText == null) {
			return follows;
		}
	
		
		for(int i = 0;i<myText.length;i++) {
			int indx = indexOf(myText, kGram, pos);
			if(indx + kGram.length() == myText.length || indx == -1) { 
				break;
			}
			int new_indx = indx + kGram.length();
			String k = myText[new_indx];
			pos = indx + 1;
			follows.add(k);
		}
		
		return follows;
    }
	
	private int indexOf(String[] words, WordGram target, int start) {
		
		for(int i = start;i<=words.length - target.length(); i++) {
			WordGram test = new WordGram(words, i, target.length());
			if(target.equals(test)) {
				return i;
			}
		}
		return -1;
	}
	
	public void testIndexOf() {
		
		String test = "this is just a test yes this is a simple test";
		String[] st = test.split("\\s+");
		WordGram target = new WordGram(st, 9, 2);

		int indx = indexOf(st, target, 5);
		System.out.println(indx);
	}
	
	@Override
	public String toString() {
		return "Efficient MarkovWord of order " + myOrder;
	}
	
	public void buildMap(){
		int index;
		WordGram KGram;
		
		for(int i = 0;i < myText.length + 10000000;i++) {
		 index = myRandom.nextInt(myText.length - myOrder);//i need to pick any index that allow one character substring
		 KGram = new WordGram(myText, index, myOrder);
		 if(myMap.containsKey(KGram)) {
			 continue;
		 }
		 ArrayList<String> follows = getFollow(KGram);
		 myMap.put(KGram, follows);
		}
//		double end = System.nanoTime();
//		double ttime = (end-begin)/1e9;
		

		 index = myRandom.nextInt(myText.length - myOrder);//i need to pick any index that allow one character substring
		 KGram = new WordGram(myText, index, myOrder);

		for(int i = 0;i < myText.length + 10000000;i++) {
			
			if(myMap.containsKey(KGram)) {
				if(myMap.get(KGram).size()==0) {
					 index = myRandom.nextInt(myText.length - myOrder);//i need to pick any index that allow one character substring
					 KGram = new WordGram(myText, index, myOrder);
					 continue;
				}
				index = myRandom.nextInt(myMap.get(KGram).size());
				String temp = myMap.get(KGram).get(index);
				KGram = KGram.shiftAdd(temp);
				continue;
			}
			
			
			ArrayList<String> follows = getFollow(KGram);
			myMap.put(KGram, follows);
			
			if(follows.size() == 0) {
				 index = myRandom.nextInt(myText.length - myOrder);//i need to pick any index that allow one character substring
				 KGram = new WordGram(myText, index, myOrder);
				 continue;
			}
			index = myRandom.nextInt(follows.size());
			String temp = follows.get(index);
			KGram = KGram.shiftAdd(temp);
			
		}
	}
	
	public void printHashMapInfo() {

		int max = 0;
		for(WordGram w:myMap.keySet()) {
//			System.out.println("KEY , Follows Character: " + w + ", " + myMap.get(w).toString());
			if(max<myMap.get(w).size()) {
				max = myMap.get(w).size();
			}
		}
		System.out.println("Number of keys in the map: " + myMap.size());
		
		System.out.println("The maximum size of ararylist: " + max);
		
		int counter = 0;
		System.out.println("The keys with maximum size: ");
		for(WordGram w:myMap.keySet()) {
			if(max == myMap.get(w).size())
			{
				System.out.println(w + "\t" + myMap.get(w).toString());
				counter++;
			}
		}
		System.out.println("Number of keys with max size: " + counter);
		System.out.println();
	}
}
