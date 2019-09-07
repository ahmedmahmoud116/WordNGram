package pkj;

import java.util.ArrayList;

public class WordGramTester {

	public WordGramTester() {
		
	}
	
	public void testWordGram() {
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int indx = 0; indx < words.length - size; indx++) {
			WordGram wg = new WordGram(words, indx, size);
			System.out.println(indx+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	public void testWordGramEquals() {
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		
		for(int indx = 0; indx < words.length - size; indx++) {
			WordGram wg = new WordGram(words, indx, size);
			list.add(wg);
		}
		
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}
	
	public void testWordGramShift() {
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		
		for(int indx = 0; indx < words.length - size; indx++) {
			WordGram wg = new WordGram(words, indx, size);
			list.add(wg);
		}
		
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		
		WordGram out = first.shiftAdd("yes");
		System.out.println("Out shifted: " + out);
		System.out.println("original after shift: " + first);
	}
	
	
}
