package text;
public class Sentence {
	private String sentence;
	private Word[] words;
	private int longestWordLength, numberOfLettersAndNumbers;
	private double averageWordLength;

	public Sentence(String sentence){
		this.sentence = sentence;
		splitWords();
		metrics();
	}
	
	public Word getWord(int i){
		return words[i];
	}
	
	public Word[] getWords(){
		return words;
	}
	
	public int getNumberOfWords(){
		return words.length;
	}
	
	public String getSentence(){
		return sentence;
	}

	public int getLongestWordLength(){
		return longestWordLength;
	}

	public int getNumberOfLettersAndNumbers(){
		return numberOfLettersAndNumbers;
	}

	public double getAverageWordLength(){
		return averageWordLength;
	}
	
	private void splitWords(){
		String theWords[] = sentence.split("[(\\s+)(\\.)(\\!)(\\,)(\\;)(\\-)(\\:)(\\?)(\\\")]");
		//String theWords[] = sentence.split("[(\\s+)(\\.)(\\')(\\!)(\\,)(\\;)(\\-)(\\:)(\\?)(\\\")]");
		int size = 0;
		for (int i=0;i<theWords.length; i++){
			if (!theWords[i].trim().equals("")) size++;
		}
		words = new Word[size];
		int j = 0;
		for (int i=0;i<theWords.length;i++){
			if (theWords[i].trim().equals("")) continue;
			
			words[j++] = new Word(theWords[i]);
		}
	}
	
	private void metrics(){
		longestWordLength = 0;
		averageWordLength = 0;
		numberOfLettersAndNumbers = 0;
		for (int i=0;i<words.length;i++){
			if (words[i].getLength() > longestWordLength) 
				longestWordLength = words[i].getLength();
			numberOfLettersAndNumbers += words[i].getLength();
		}
		averageWordLength = (double)numberOfLettersAndNumbers/getNumberOfWords();
	}
	
	@Override
	public String toString(){
		return sentence;
	}
}
