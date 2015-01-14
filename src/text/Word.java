package text;
import java.io.Serializable;


public class Word implements Serializable, Comparable<Word> {
	private static final long serialVersionUID = 1L;
	protected String wordToLowerCase, wordUnmodified;

	public Word() {
	}

	public Word(String word) {
		word = word
				.replaceAll(
						"(\\«)|(\\*)|(\\»)|(\\()|(\\))|(\\{)|(\\})|(\\[)|(\\])|(\\<)|(\\>)|(\\=)|(\\%)|(\\€)|(\\$)",
						"");
		this.wordUnmodified = word.trim();
		this.wordToLowerCase = word.toLowerCase().trim();
		// all initializations must go here!!!
	}

	public String getWord() {
		return wordToLowerCase;
	}

	public String getWordUnmodified() {
		return wordUnmodified;
	}

	public int getLength() {
		return wordToLowerCase.length();
	}

	@Override
	public boolean equals(Object x) {
		Word w = (Word) x;
		return w.getWord().equalsIgnoreCase(this.wordToLowerCase);
	}

	@Override
	public String toString() {
		return wordToLowerCase;
	}

	@Override
	public int hashCode() {
		return wordToLowerCase.hashCode();
	}

	private String eliminateStartingSymbols() {
		int i = 0;
		while (i < wordToLowerCase.length()
				&& (wordToLowerCase.charAt(i) == '\'' || wordToLowerCase.charAt(i) == '`' || wordToLowerCase
						.charAt(i) == '΄')) {
			i++;
		}
		return wordToLowerCase.substring(i);
	}

	@Override
	public int compareTo(Word o) {
		Word w = (Word) o;
		// String theWord = w.getWord();
		return (this.eliminateStartingSymbols()).compareTo(w
				.eliminateStartingSymbols());
	}
}
