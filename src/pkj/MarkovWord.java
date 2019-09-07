package pkj;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{

	private String[] myText;
	private Random myRandom;
	private int myOrder;
	
	public MarkovWord(int order) {
		myRandom = new Random();
		this.myOrder = order;
	}
	
	public void setRandom(int seed) {// to generate the same sequence of random number help in testing
		myRandom = new Random(seed);
	}
	
	public void setTraining(String text) {
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords) {
		
		
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder);//i need to pick any index that allow one character substring
		WordGram KGram = new WordGram(myText, index, myOrder);
		sb.append(KGram.toString());
		sb.append(" ");
		
		
		for(int i = 0;i<numWords;i++) {
	
			ArrayList<String> follows = getFollows(KGram);

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
	
	public String toString() {
		return "MarkovWord of order " + myOrder;
	}
	

}
