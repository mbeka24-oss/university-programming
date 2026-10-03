import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private HangmanCanvas canvas;
	private String word; // word that we should guess
	private HangmanLexicon lexicon;
	private RandomGenerator rNum; //random number
	private String unvisableword;
	private String input; //inputed string
	private char inputChar;// input that will transform from String into char
	static int LIVES = 8;
	static String CHARBOX = ""; //String were i save incorrect guesses
	static String CHAR;
    public void run() {
    	canvas = new HangmanCanvas(); 
        canvas.reset();
    	add(canvas); 
    	biggining();
    //	println(word); //prints word to help u analyze my code faster, in reality it should not be there
    	game();
	    endOfThegame();

    }
    private void game(){
        while(unvisableword.contains("-") && LIVES != 0){        //while full word is not visible
            println("your word look slike this: " + unvisableword);        	//prints word
            input = readLine("Write a character: ");            //enter char
            while(input.length() == 0){
                input = readLine("Write a character: ");            //enter char
            }
            input = input.toUpperCase();
            inputChar = input.charAt(0);     	            	//transfers String to char
            transformToChar();
            String wordDublicate = word;            //Duplicate of word
        	CHAR = input;
            int offset = 0;	
            boolean found = false;
            compareChar(wordDublicate, offset, found);
            foundNotFound(found);
        }
    }
    private void endOfThegame(){
        if(LIVES == 0){
	        println("your lose. your word was " + word);
        }else{
	        println("congrulations, your word is " + word);
        }
    }
    private void foundNotFound(boolean found){
        if (!found ) {
            println("Character not found in the word.");
	        println("you got: " + LIVES + " lives");
        }
        
    }
    private void compareChar(String wordDublicate, int offset, boolean found){
        while (true) { // compares char to every char
            int index = wordDublicate.indexOf(inputChar); // searches for an index of inputed char
            if (index == -1) {
                if (!word.contains(CHAR)) {
                    LIVES -= 1;
                    canvas.reset();
                    CHARBOX = CHARBOX + CHAR;
                }
                canvas.noteIncorrectGuess(CHARBOX);//Update the word on the canvas
                canvas.displayWord(unvisableword); // Update the word on the canvas
                break; // No need to continue if the character isn't found
            }
            char middlePart = wordDublicate.charAt(index);
            String firstPart = unvisableword.substring(0, index + offset);
            String lastPart = unvisableword.substring(index + offset + 1);
            unvisableword = firstPart + middlePart + lastPart; // Update visible word
            offset += index + 1;
            wordDublicate = wordDublicate.substring(index + 1);
            found = true;
        }
    }
    
    private void transformToChar(){
        while (input.length() != 1 || !(inputChar >= 'A' && inputChar <= 'Z') ) {//if you do not type 1 english char it will automatically ask you again 
            if(input.length() != 1){
	            input = readLine("Write a only 1 character: ");            //enter char
	            input = input.toUpperCase();
	            inputChar = input.charAt(0);             	            
            }else if(!(inputChar >= 'A' && inputChar <= 'Z')){
	            input = readLine("Write a 1 english character: ");            //enter char
	            input = input.toUpperCase();
	            inputChar = input.charAt(0); 
            }
        }
    }
    private void biggining(){
		println("Welcome to hangman");
		
        lexicon = new HangmanLexicon(); //i want to get random word from HangmanLexicon
        rNum = RandomGenerator.getInstance();
        int randomIndex = rNum.nextInt(0, lexicon.getWordCount() - 1);
        word = lexicon.getWord(randomIndex);
            	        
        int wordLength = word.length();
        unvisableword = "-";
        for(int k = 0; k < wordLength - 1; k++){ //creates "- - -" as long as wordLength, but without spaces
        	unvisableword += "-";
        }
    }
}
