/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	/* ptoblems:
	 * if u ckick category twice it will change,
	 * i didnot wrote code about suming
	 */
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
        while (nPlayers < 1 || nPlayers > 4) {//this code limits you to enter more then 4 player
            nPlayers = dialog.readInt("Invalid input. Enter number of players (1-4):");
        }
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
		endgame();
	}

	private void endgame() {
		for(int i = 0; i < nPlayers; i++){
			
		}

	}

	private void playGame() {
		for(int round = 0; round <= N_SCORING_CATEGORIES; round++){//13 round
			for(int player = 1; player <= nPlayers; player++){//players number
				playerTurn(player);
			}
		}
		
	}
		
	private void playerTurn(int player) {
	display.printMessage(playerNames[player - 1] + "'s turn! Click 'Roll Dice' to roll.");
	display.waitForPlayerToClickRoll(player);
	int[] diceNum = new int[5]; // integer were i initialize with integer which is equal to dice numbers
	for (int i = 0; i < 5; i++) {
	    diceNum[i] = rgen.nextInt(1, 6);
	}
	reRoll(diceNum);
	reRoll(diceNum);
    display.displayDice(diceNum);
    int category = display.waitForPlayerToSelectCategory();
    display.updateScorecard(category, player, getPiont(diceNum, category, player));// updates scoreboard. but i had no time to sum it up
    display.updateScorecard(UPPER_SCORE, player, sumPionts(diceNum, category, player));

	}


	private void reRoll(int[] diceNum){
		display.displayDice(diceNum);
		display.waitForPlayerToSelectDice();
		for (int i = 0; i < 5; i++) {
		    if (display.isDieSelected(i)) {//if dice is not selected 
		        diceNum[i] = 1; //regenerating dice number
		    }
		}
	}
	private int sumPionts(int[] diceNum, int category, int player) {
	
	return 0;
	}
	
	private int getPiont(int[] diceNum, int category, int player) {
	    switch (category) {//u choose category and computer wil calcuate it itself
        case ONES:
            return scoreOns(diceNum, player);
        case TWOS:
            return scoreTwos(diceNum, player);
        case THREES:
            return scoreThrees(diceNum, player);
        case FOURS:
            return  scoreFours(diceNum, player);
        case FIVES:
            return scoreFives(diceNum, player);
        case SIXES:
            return scoreSixes(diceNum, player);
        case THREE_OF_A_KIND:
            return scoreThreeOfAKind(diceNum, player);
        case FOUR_OF_A_KIND:
            return scoreFourOfAKind(diceNum, player);
        case FULL_HOUSE:
            return scoreFullHouse (diceNum, player);
        case SMALL_STRAIGHT:
            return scoreSmallStraight(diceNum, player);
        case  LARGE_STRAIGHT:
            return scoreLargeStraight(diceNum, player);
        case YAHTZEE:
            return scoreYahtze(diceNum, player);
        case CHANCE:
            return scoreChance(diceNum, player);
        default:
            return 0;
    }

	}

	private int scoreChance(int[] diceNum, int player) {
		int sum = 0;// sums every dices nuber
		for(int i = 0; i < N_DICE; i++){
			sum = sum + diceNum[i];
		}
		return sum;
	}
	
	private int scoreYahtze(int[] diceNum, int player) {
		int[] yahtzee = new int[6];// array were i whant to see how many numbers each category  has, for example 2 twoes and 3 sixes
		for(int i = 0; i < N_DICE; i++){
			int w = diceNum[i] - 1; //diceNum[0]=1, so  diceNum[i] - 1 is position
			yahtzee[w] ++;//  category incruse by 1 
		}
		int diffNums = 6;
		for(int i = 0; i < 6; i++){// searches how many diiferent numbers do i have
			if(yahtzee[i] == 0){
				diffNums -= 1;
			}
		}
		if(diffNums == 1){// and if there is only 1 type of integers thets meens it is yahtzee 
			return 50;
		}
		return 0;
		
		}
	
	private int scoreLargeStraight(int[] diceNum, int player) {
		int final1 = 0;
		int[] largeStright = new int[6];// i use same strategy here
		for(int i = 0; i < N_DICE; i++){
			int w = diceNum[i] - 1;// here
			largeStright[w] ++;// and here
		}
	    if(largeStright[0] > 0 && largeStright[1] > 0 && largeStright[2] > 0 && largeStright[3] > 0 && largeStright[4] > 0){
	        return 40;
	    }// we have only two opportunity to get LargeStraight, and this is described in this two code
	    if(largeStright[1] > 0 && largeStright[2] > 0 && largeStright[3] > 0 && largeStright[4] > 0 && largeStright[5] > 0){
	        return 40;
	    }
		return final1;
		}
	
	private int scoreSmallStraight(int[] diceNum, int player) {
		int final1 = 0;
		int[] smallStright = new int[6];//again here is same tactic
		for(int i = 0; i < N_DICE; i++){
			int w = diceNum[i] - 1;
			smallStright[w] ++;
		}// it is little complicated,
		if(smallStright[2] <= 2 && smallStright[3] <= 2 && smallStright[2] > 0 && smallStright[3] > 0){//says 3 and 4 should be there in every situation
			//and others is 3 different types of formation
			if(smallStright[0] <= 2 && smallStright[1] <= 2 && smallStright[0] > 0 && smallStright[1] > 0){
				return 30;
			}
			if(smallStright[1] <= 2 && smallStright[4] <= 2 && smallStright[1] > 0 && smallStright[4] > 0){
				return 30;
			}
			if(smallStright[4] <= 2 && smallStright[5] <= 2 && smallStright[4] > 0 && smallStright[5] > 0){
				return 30;
			}
		}
		return final1;
		}
	
	private int scoreFullHouse(int[] diceNum, int player) {
	    int final1 = 0;
	    int[] fullHouse = new int[6];// array were i whant to see how many numbers each category  has
	    for (int i = 0; i < N_DICE; i++) {//cheks how many unicue num do we have
	        int w = diceNum[i] - 1;
	        fullHouse[w]++;
	    }
	    int threeCount = 0, twoCount = 0;
	    for (int count : fullHouse) {
	        if (count == 3) threeCount++;
	        if (count == 2) twoCount++;
	    }
	    if (threeCount == 1 && twoCount == 1) {// checks if there is 2 thress and 3 twoes
	        return 25;
	    }
	    return final1;
	}
	
	private int scoreFourOfAKind(int[] diceNum, int player) {
		int final1 = 0;
		int max = 0;
		int[] fourOfKind = new int[6];// array were i whant to see how many numbers each category  has
		for(int i = 0; i < N_DICE; i++){
			int w = diceNum[i] - 1;
			fourOfKind[w] ++;//  category incruse by 1 
		}
		for(int k = 0; k < N_DICE; k++){
				if(fourOfKind[k] > max){
					max = fourOfKind[k];
				}
		}
		if(max >= 4){//checks if there are 4 or more same types(number) dice
			for(int i = 0; i < 6; i++){
				final1 += diceNum[i];
			}
			
		}
		return final1;	
		}
	
	private int scoreThreeOfAKind(int[] diceNum, int player) {
		int final1 = 0;
		int max = 1;
		int[] threeOfKind = new int[6];// array were i whant to see how many numbers each category  has
		for(int i = 0; i < N_DICE; i++){
			int w = diceNum[i] - 1;
			threeOfKind[w] ++;
		}
		for(int k = 0; k < N_DICE; k++){//cheks how many unicue num do we have
				if(threeOfKind[k] > max){
					max = threeOfKind[k];
				}
		}
		if(max >= 3){
			for(int i = 0; i < N_DICE; i++){
				final1 += diceNum[i];
			}
			
		}
		return final1;
		}
	
	private int scoreSixes(int[] diceNum, int player) {
		int final1 = 0;
		for(int i = 0; i < N_DICE; i++){
			if(diceNum[i] == 6){//if num of dice is equal t 6  score will incruse by 6
				final1+=6;
			}
		}

		return final1;
		}
	
	private int scoreFives(int[] diceNum, int player) {
		int final1 = 0;
		for(int i = 0; i < N_DICE; i++){//if num of dice is equal t 5  score will incruse by 5
			if(diceNum[i] == 5){
				final1+= 5;
			}
		}

		return final1;
		}
	
	private int scoreFours(int[] diceNum, int player) {
		int final1 = 0;
		for(int i = 0; i < N_DICE; i++){//if num of dice is equal t 4  score will incruse by 4
			if(diceNum[i] == 4){
				final1+= 4;
			}
		}

		return final1;
	}
	
	private int scoreThrees(int[] diceNum, int player) {
		int final1 = 0;
		for(int i = 0; i < N_DICE; i++){//if num of dice is equal t 3  score will incruse by 3
			if(diceNum[i] == 3){
				final1+= 3;
			}
		}

		return final1;	}
	
	private int scoreTwos(int[] diceNum, int player) {
		int final1 = 0;
		for(int i = 0; i < N_DICE; i++){//if num of dice is equal t 2  score will incruse by 2
			if(diceNum[i] == 2){
				final1 += 2;
			}
		}

		return final1;
		}
	
	private int scoreOns(int[] diceNum, int player) {
		int final1 = 0;
		for(int i = 0; i < N_DICE; i++){//if num of dice is equal t 1  score will incruse by 1
			if(diceNum[i] == 1){
				final1++;
			}
		}
		return final1;
		}





/* Private instance variables */
	private int nPlayers;
	private boolean[][] isCategoryUsed = new boolean [N_SCORING_CATEGORIES][nPlayers];
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
