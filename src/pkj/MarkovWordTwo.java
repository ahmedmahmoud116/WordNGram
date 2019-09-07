package pkj;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel{

	private Random myRandom;
	private String[] myText;
	
	public MarkovWordTwo() {
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
		int index = myRandom.nextInt(myText.length - 2);//i need to pick any index that allow one character substring
		String key1 = myText[index];
		String key2 = myText[index + 1];
		
		sb.append(key1);
		sb.append(" ");
		
		sb.append(key2);
		sb.append(" ");
		
		for(int i = 0;i<numWords;i++) {
	
			ArrayList<String> follows = getFollows(key1, key2);
//			System.out.println("key1,key2 : follows: " + key1 + "," + key2 + ": " + follows );
			if(follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String temp = follows.get(index);
			sb.append(temp);
			sb.append(" ");
			key1 = key2;
			key2 = temp;
		}
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
		ArrayList<String> follows = new ArrayList<String>();
		int pos = 0;
		if(myText == null) {
			return follows;
		}
		
		for(int i = 0;i<myText.length;i++) {
			int indx = indexOf(myText, key1, key2, pos);
			if(indx + 2 == myText.length || indx == -1) { 
				break;
			}
			int new_indx = indx + 2;
			String k = myText[new_indx];
			pos = indx + 1;
			follows.add(k);
		}
		return follows;
    }
	
	private int indexOf(String[] words, String target1, String target2, int start) {
		
		for(int i = start;i<words.length -1; i++) {
			if(target1.equals(words[i]) && target2.equals(words[i+1])) {
				return i;
			}
		}
		return -1;
	}
	
	public void testIndexOf() {
		
		String test = "this is just a test yes this is a simple test";
		String[] st = test.split("\\s+");
		
		int indx = indexOf(st, "this", "a", 0);
		System.out.println(indx);
	}
	
	public String toString() {
		return "MarkovTwo of order 2";
	}
	
}
