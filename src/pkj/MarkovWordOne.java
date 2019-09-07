package pkj;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordOne implements IMarkovModel{

	private Random myRandom;
	private String[] myText;
	
	public MarkovWordOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed) {// to generate the same sequence of random number help in testing
		myRandom = new Random(seed);
	}
	
	public void setTraining(String text) {
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords) {
		
		
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - 1);//i need to pick any index that allow one character substring
		String key = myText[index];

		sb.append(key);
		sb.append(" ");
		
		for(int i = 0;i<numWords-1;i++) {
	
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String temp = follows.get(index);
			sb.append(temp);
			sb.append(" ");
			key = temp;
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		if(myText == null) {
			return follows;
		}
		
		for(int i = 0;i<myText.length;i++) {
			int indx = indexOf(myText, key, pos);
			if(indx + 1 == myText.length || indx == -1) { 
				break;
			}
			int new_indx = indx + 1;
			String k = myText[new_indx];
			pos = indx + 1;
			follows.add(k);
		}
		return follows;
    }
	
	private int indexOf(String[] words, String target, int start) {
		
		for(int i = start;i<words.length; i++) {
			if(target.equals(words[i])) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void testIndexOf() {
		
		String test = "this is just a test yes this is a simple test";
		String[] st = test.split("\\s+");
		
		int indx = indexOf(st, "test", 5);
		System.out.println(indx);
	}
	
	public String toString() {
		return "MarkovOne of order 1";
	}
}
