package pkj;

public interface IMarkovModel {

	public void setTraining(String text);
	public String getRandomText(int numWords);
	public void setRandom(int seed);
}
