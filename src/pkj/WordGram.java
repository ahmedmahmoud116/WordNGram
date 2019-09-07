package pkj;

import java.util.ArrayList;
import java.util.Arrays;

public class WordGram {
	
	private String[] myWords;
	private int myHash;
	public WordGram() {
		
	}
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		if((start + size) > source.length) {
			int sum = start + size;
			throw new IndexOutOfBoundsException("start + size is large: " + sum + " and the source of size: " + source.length + 
					" and the limit must be equal");
		}
		System.arraycopy(source, start, myWords, 0, size);
	}
	
//	public void setmyWords(String[] source) {
//		myWords = source;
//	}
//	
//	public String[] subgram(String[] source, int start, int size) {
//		myWords = new String[size];
//		if((start + size) > source.length) {
//			int sum = start + size;
//			throw new IndexOutOfBoundsException("start + size is large: " + sum + " and the source of size: " + source.length + 
//					" and the limit must be equal");
//		}
//		System.arraycopy(source, start, myWords, 0, size);
//		return myWords;
//	}
	
	public String wordAt(int index) {
		if(index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}
	
	public int length() {
		return myWords.length;
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(int i = 0;i < myWords.length ; i++) {
			ret += myWords[i] + " "; 
		}
		return ret.trim();
	}
	
	@Override
	public boolean equals(Object o) {
		WordGram other = (WordGram) o;
		if(this.length() != other.length())
			return false;
		for(int i = 0; i < this.length() ; i++) {
			if(!this.wordAt(i).equals(other.wordAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public WordGram shiftAdd(String word) {
		WordGram out = new WordGram(this.myWords, 0, this.length());
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(out.myWords));
		list.remove(0);
		list.add(word);
		out.myWords = list.toArray(new String[list.size()]);
		return out;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
