/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
import acm.util.*;
import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {
    private ArrayList<String> wordsList;
	private String wordLine;

    
    public HangmanLexicon() {
        wordsList = new ArrayList<>(); // Initialize the lexicon ArrayList
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("HangmanLexicon.txt")); 
            wordLine = "";
            while ((wordLine = bufReader.readLine()) != null) { // Read each word from the file
            	wordsList.add(wordLine); 
            }
        } catch (IOException e) {
            System.out.println("eror while searching");
        }
    }

    public int getWordCount() {
        return wordsList.size();
    }

    public String getWord(int index) {
        return wordsList.get(index);
    }
}