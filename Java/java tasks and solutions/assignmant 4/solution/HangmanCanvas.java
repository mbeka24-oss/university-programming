/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
private int a = -1;
private int topY = 100;
private int centerX = 189; // i measure width of window and 189 is center of it
private int bottomY = topY + BODY_LENGTH; // Bottom of the body
private GLabel printWord; 
private GLabel charbox;

/** Resets the display so that only the scaffold appears */
	public void reset() {//before u input anything scaffold is there, after u do not guess correct char body parts will pops up each by each 
		scaffold();
		a += 1;
		if(a == 1){
			head();
		}else if(a == 2){
			addBody();
		}else if(a == 3){
			leftUpperHand();
			leftHand();
		}else if(a == 4){
			rigthUpperHand();
			rigthLowerHand();	
		}else if(a == 5){
			leftLeg();
			rightHip();
			leftHip();
		}else if(a == 6){
			rightLeg();
		}else if(a == 7){
			righfeet();
		}else if(a == 8){
			rightfeet();
		}
	}
	// it is hard to understand why is that coordinates come from. the idea begin from addbody() and other parts are depend on the coordinates of body
	//so it is easy to change position 
	private void leftUpperHand(){
		

	    GLine leftHand = new GLine(centerX, topY + ARM_OFFSET_FROM_HEAD, centerX - UPPER_ARM_LENGTH, topY + ARM_OFFSET_FROM_HEAD);
	    add(leftHand);
	}
	private void leftHand(){
	    GLine leftHand = new GLine( centerX - UPPER_ARM_LENGTH, topY + ARM_OFFSET_FROM_HEAD, centerX - UPPER_ARM_LENGTH, topY + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
	    add(leftHand);
	}
	private void rigthUpperHand(){
	    GLine rigthHand = new GLine(centerX, topY + ARM_OFFSET_FROM_HEAD, centerX + UPPER_ARM_LENGTH, topY + ARM_OFFSET_FROM_HEAD);
	    add(rigthHand);
	}
	private void rigthLowerHand(){
	    GLine rigthHand = new GLine( centerX + UPPER_ARM_LENGTH, topY + ARM_OFFSET_FROM_HEAD, centerX + UPPER_ARM_LENGTH, topY + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
	    add(rigthHand);
	}
 	private void addBody() {
 		GLine body = new GLine(centerX, topY, centerX, bottomY);
	    add(body);
	}
	private void head(){
	    GOval head = new GOval(centerX - HEAD_RADIUS, topY - HEAD_RADIUS * 2, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
	    add(head);
	}
	private void leftHip(){
	    GLine leftHip = new GLine(centerX, bottomY, centerX - HIP_WIDTH, bottomY);
	    add(leftHip);
	}
	private void rightHip(){
	    GLine rightHip = new GLine(centerX, bottomY, centerX + HIP_WIDTH, bottomY);
	    add(rightHip);
	}
	private void rightLeg(){
	    GLine rightLeg = new GLine(centerX + HIP_WIDTH, bottomY, centerX + HIP_WIDTH, bottomY + LEG_LENGTH);
	    add(rightLeg);
	}
	private void rightfeet(){
	    GLine rightfeet = new GLine( centerX + HIP_WIDTH, bottomY + LEG_LENGTH,  centerX + HIP_WIDTH + FOOT_LENGTH, bottomY + LEG_LENGTH );
	    add(rightfeet);
	}
	private void leftLeg(){
	    GLine leftLeg = new GLine(centerX - HIP_WIDTH, bottomY, centerX - HIP_WIDTH, bottomY + LEG_LENGTH);
	    add(leftLeg);
	}
	private void righfeet(){
	    GLine righfeet = new GLine( centerX - HIP_WIDTH, bottomY + LEG_LENGTH,  centerX - HIP_WIDTH - FOOT_LENGTH, bottomY + LEG_LENGTH);
	    add(righfeet);
	}
	private void scaffold(){
		GLine rope = new GLine(centerX, topY - HEAD_RADIUS * 2, centerX, (topY - HEAD_RADIUS * 2) - ROPE_LENGTH);
	    GLine beam = new GLine( centerX, (topY - HEAD_RADIUS * 2) - ROPE_LENGTH, centerX - BEAM_LENGTH, (topY - HEAD_RADIUS * 2) - ROPE_LENGTH);
	    GLine scaffold = new GLine(centerX - BEAM_LENGTH,(topY - HEAD_RADIUS * 2) - ROPE_LENGTH,centerX  - BEAM_LENGTH,(topY - HEAD_RADIUS * 2) - ROPE_LENGTH + SCAFFOLD_HEIGHT);
	    add(scaffold);
	    add(rope);
	    add(beam);
	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String unvisableword) {
	    if (printWord != null) {
	        remove(printWord); // Remove the old label if it exists
	    }
    	printWord = new GLabel(unvisableword);
    	printWord.setFont("Serif-30");
    	printWord.setLocation(centerX - BEAM_LENGTH ,bottomY + LEG_LENGTH + 50);	
    	
    	add(printWord);	
    }

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String CHARBOX) {
    	charbox = new GLabel(CHARBOX);
    	charbox.setFont("Serif-20");
    	charbox.setLocation(centerX - BEAM_LENGTH ,bottomY + LEG_LENGTH + 100);	
    	
    	add(charbox);	
    	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
