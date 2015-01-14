import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import text.Text;

/**
 * 
 */

/**
 * @author chris
 * Jan 14, 2015
 *
 * TextInformation /  / TextInformationMain.java
 */
public class TextInformationMain {

	public static void main(String[] args) {
		Text t = new Text(readText());
		System.err.println(t.getNumberOfWords());
		System.err.println(t.getWordsFreq());
		
	}
	
	public static String readText(){
	    try {
		    File file = new File("text.txt");
	        Scanner sc = new Scanner(file);
	        StringBuffer sb = new StringBuffer();
	        while (sc.hasNextLine()) {
	            sb.append(sc.nextLine() + (sc.hasNext()?"\n":""));
	        }
	        sc.close();
	        return sb.toString();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return null;
	 }

}
