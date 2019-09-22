/**
 * @title Assignment 1 - Game of Pig
 * @author Kyle Meade
 * @netid 18kdm2@queensu.ca
 * @studentNumber 20129191
 * @class CISC124 - Macleod
 * @university Queen's University in Kingston
 */

import java.util.Random;
import java.util.Scanner;

public class Assn1_18kdm2 {
	
	static Random generator = new Random(System.currentTimeMillis());
	static Scanner in = new Scanner(System.in);
	static String userInput;
	static int playerScore, computerScore;
	static boolean forceTurn = false;
	
	// This method turns an integer (from 1-6) into it's English name as a String
	private static String mapIntToWord(int num) {
		// Array of number words from one to 6
		String[] words = {"one", "two", "three", "four", "five", "six"};
		
		// return the word of the number, with safe-case to prevent crashes
		if (num > 0 && num < 7) {
			return words[num - 1];
		} else {
			return "";
		}
	}
	
	// Reset the game scores
	private static void resetScores() {
		playerScore = 0;
		computerScore = 0;
	}
	
	
	/**
	 * Roll the die and return scores to add based on condition
	 * @param the player that is playing
	 * @return the score from the randomly rolled die
	 */
	private static int rollDie(String player) {
		int die1 = 1 + generator.nextInt((5) + 1);
		int die2 = 1 + generator.nextInt((5) + 1);
		
		System.out.println(player + " rolled " + mapIntToWord(die1) + " and " + mapIntToWord(die2));
		
		if (die1 == 6 && die2 == 6) {
			System.out.println("A double 6.. Turn over and score reset");
			return -1;
		} else if (die1 == 6 || die2 == 6) {
			System.out.println("Turn over!");
			return 0;
		} else if (die1 == die2) {
			System.out.println("A double die! Rolling again.");
			forceTurn = true;
			return die1 * 4;
		} else  {
			return die1 + die2;
		}
		
	}
	
	/**
	 * Method to ask the user if they want to continue
	 * @return whether they want to continue
	 */
	private static boolean getContinue(String prompt, Boolean reset) {
		String consumeScanner;
		
		// Check if the player wants to continue playing
		System.out.print(prompt + " (Enter 'y' or 'n'): ");
		userInput = in.next();
		consumeScanner = in.nextLine();
		
		if (userInput.equals("y")) {
			if (reset) {
				playerScore = 0;
				computerScore = 0;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Host the player's turn in a round
	 */
	private static void playerTurn() {
		boolean playing = true;
		int turnSum = 0;
		int roll = 0;
		
		System.out.println("Your turn");
		
		// Game loop
		while (playing) {
			// Roll the die and collect results
			roll = rollDie("You");
			
			// Set round properties based on results
			if (roll == -1) {
				turnSum = -playerScore;
				playing = false;
			} else if (roll == 0) {
				turnSum = 0;
				playing = false;
			} else {
				turnSum += roll;
			}
			
			System.out.println("Turn score: " + turnSum + " and game sum would be: " + (playerScore + turnSum));
			
			// See if the user wants to roll again or not
			if (!forceTurn && playing) {
				playing = getContinue("roll again?", false);
			}
			
			forceTurn = false;
		}
		
		forceTurn = false;
		playerScore += turnSum;
	}
	
	/**
	 * Host the computers turn in the round
	 */
	private static void computerTurn() {
		boolean playing = true;
		int turnSum = 0;
		int roll = 0;
		int turns = 0;
		
		System.out.println("My turn");
		
		// Game loop
		while (playing) {
			// Roll the die and collect result
			turns += 1;
			roll = rollDie("computer");
			
			// Set round properties based on result
			if (roll == -1) {
				turnSum = -computerScore;
				playing = false;
			} else if (roll == 0) {
				turnSum = 0;
				playing = false;
			} else {
				turnSum += roll;
			}
			
			// Don't let the computer take more than 3 chances per round
			if (turns >= 3) {
				playing = false;
			}
			
			System.out.println("Turn score: " + turnSum + " and game sum would be: " + (computerScore + turnSum));
		}
		
		computerScore += turnSum;
		
	}
	
	/*
	 * Output scores to users
	 */
	private static void outputScore() {
		System.out.println("");
		System.out.println("Your score: " + playerScore + ". My score: " + computerScore + ".");
		System.out.println("");
	}
	
	/**
	 * Loop that runs a game
	 * @return who won
	 */
	private static String gameLoop() {
		int round = 1;
		
		boolean playing = true;
		String consumeScanner;
		
		// Run game loop
		while (playing) {
			System.out.println("");
			System.out.println("Round: " + round);
			
			// Run turns
			playerTurn();
			
			outputScore();
			
			computerTurn();
			
			outputScore();
			
			// Check if someone won, if so, end the round
			if (playerScore >= 100 || computerScore >= 100) {
				playing = false;
			}
			
			// Create a pause so the user can see what happened during the round
			if (playing) {
				System.out.println("Enter anything to begin next round ");
				consumeScanner = in.next();
				consumeScanner = in.nextLine();
			}
			
			round += 1;
		}
		
		// Return the output of who won the round
		if (playerScore >= 100) return "player";
		else return "computer";
	}
	
	public static void main(String[] args) {
		// Greet the user
		System.out.println("Welcome to pig!");
		System.out.println("");
		
		// Variable to keep track if the user is playing
		boolean playing = true;
		String winner = "";
		
		// Run the game loop
		while (playing) {
			winner = gameLoop();
			System.out.println(winner + " won!");
			
			// Check if the player wants to continue playing
			playing = getContinue("Continue playing?", true);
			resetScores();
		}
		
		// Classic end of command line game thank you.
		System.out.println("");
		System.out.println("Thanks for playing");
	}

}
