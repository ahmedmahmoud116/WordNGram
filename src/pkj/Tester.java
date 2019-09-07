package pkj;

import java.io.FileNotFoundException;

public class Tester {

	public Tester() {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
//		MarkovWordOne mo = new MarkovWordOne();
//		mo.testIndexOf();
		
		MarkovRunner mr = new MarkovRunner();
		mr.runMarkov();
//		mr.testHashMap();
//		mr.compareMethods();
		
		MarkovWordTwo mt = new MarkovWordTwo();
//		mt.testIndexOf();
		
		WordGramTester wgt = new WordGramTester();
//		wgt.testWordGram();
//		wgt.testWordGramEquals();
//		wgt.testWordGramShift();
		
		MarkovWord mw = new MarkovWord(4);
//		mw.testIndexOf();
//		String[] source = new String[] {"ahmed", "mohamed","mahmoud"};
//		String[] source2 = new String[] {"ahmed", "mohamed","mahmoud"};
//		WordGram wg1 = new WordGram(source, 0, 1);
//		WordGram wg2 = new WordGram(source2, 0, 1);
//		System.out.println(wg1.hashCode());
//		System.out.println(wg2.hashCode());
//		System.out.println("ahmed ".hashCode());
	}

}
