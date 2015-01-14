package text;
import java.util.HashMap;

public class Text{
	private String text;
	private Sentence sentences[];
	private HashMap<Word, Integer> wordsFreq;

	private int numberOfTotalWords, numberOfDistinctWords, numberOfSentences, numberOfSyllables, numberOfBigSentences,
		longestWordLength, longestSentenceLength,numberOfLettersAndNumbers, numberOfParagraphs;
	private double averageWordLength, averageLongestWordLength;
	
	public Text(String text){	
		this.text = text;
		splitSentences();
		calculateParagraphs();
		wordsFreq = new HashMap<Word, Integer>();
		metrics();
	}

	public HashMap<Word, Integer> getWordsFreq() {
		return wordsFreq;
	}

	public int getNumberOfSentences(){
		return numberOfSentences;
	}

	public Sentence[] getSentences(){
		return sentences;
	}

	public int getNumberOfWords(){
		return numberOfTotalWords;
	}

	public int getNumberOfDistinctWords(){
		return numberOfDistinctWords;
	}

	public int getNumberOfSyllables(){
		return numberOfSyllables;
	}

	public int getNumberOfBigSentences(){
		return numberOfBigSentences;
	}

	public int getLongestWordLength(){
		return longestWordLength;
	}

	public int getLongestSentenceLength(){
		return longestSentenceLength;
	}

	public int getNumberOfLettersAndNumbers(){
		return numberOfLettersAndNumbers;
	}

	public int getNumberOfParagraphs(){
		return numberOfParagraphs;
	}

	public double getWordsPerSentence(){
		return (double)numberOfTotalWords/numberOfSentences;
	}

	public double getSyllablesPerWord(){
		return (double)numberOfSyllables/numberOfTotalWords;
	}

	public double getAverageWordLength(){
		return averageWordLength;
	}

	public double getAverageLongestWordLength(){
		return averageLongestWordLength;
	}
	
	private void splitSentences(){
		String tmp[] = text.trim().split("((\\.)*(\\.)\\s)|((\\!)*(\\!)\\s)|((\\;)*(\\;)\\s)|((\\?)*(\\?)\\s)");
		sentences = new Sentence[tmp.length];
		for (int i=0;i<tmp.length; i++){
			sentences[i] = new Sentence(tmp[i]);
		}
	}
	
	private void calculateParagraphs(){
		String tmp[] = text.trim().split("((\\n\\n)*(\\\n)\\s)");
		numberOfParagraphs = tmp.length;
	}
	
	private void metrics(){
		numberOfTotalWords = 0;
		numberOfDistinctWords = 0;
		numberOfSyllables = 0;
		numberOfSentences = sentences.length;
		longestWordLength = 0;
		longestSentenceLength = 0;
		numberOfLettersAndNumbers = 0;
		averageWordLength = 0;
		averageLongestWordLength = 0;
		numberOfBigSentences = 0;
		
		for (int i=0; i<numberOfSentences; i++){
			if (sentences[i].getNumberOfWords()>longestSentenceLength)
				longestSentenceLength = sentences[i].getNumberOfWords();
			
			Word words[] = sentences[i].getWords();
			numberOfTotalWords += words.length;
			
			averageLongestWordLength += sentences[i].getLongestWordLength();
			
			if (sentences[i].getLongestWordLength()>longestWordLength)
				longestWordLength = sentences[i].getLongestWordLength();
			
			if (sentences[i].getNumberOfWords() >= 15)
				numberOfBigSentences++;
			
			
			for (int j=0;j<words.length; j++){
				numberOfLettersAndNumbers += words[j].getLength();
				if (wordsFreq.containsKey(words[j])){
					wordsFreq.put(words[j], wordsFreq.get(words[j]) + 1);
				}
				else {
					numberOfDistinctWords++;
					wordsFreq.put(words[j], 1);
				}
			}
		}
		averageWordLength = (double)numberOfLettersAndNumbers/numberOfTotalWords;
		averageLongestWordLength = averageLongestWordLength/numberOfSentences;
	}

	public double flesch() {
		// Flesch reading ease score
		double fres = 206.835 - (1.015 * numberOfTotalWords) / numberOfSentences - 
				(84.6 * numberOfSyllables) / numberOfTotalWords;
		return fres;
	}

	public double fleschKincaid() {
		// Flesch-Kincaid grade level
		double fkgl = (0.39 * numberOfTotalWords) / numberOfSentences + 
				(11.8 * numberOfSyllables) / numberOfTotalWords - 15.59;
		return fkgl;
	}
}
