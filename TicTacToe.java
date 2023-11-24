import java.util.List;
import java.util.Scanner;

public class TicTacToe {
	private Queue<Player> players;
	boolean gameOver = false;
	private Scanner input = new Scanner(System.in);
	private Player player1;
	private Player player2;

	/**
	 * TicTacToe constructor
	 * @param players Player list
	 */
	public TicTacToe(Queue<Player> players) {
		this.players = players;
	}
	
	
	/**
	 * Method that starts the game. Prints welcome message, and prints instructions
	 * upon request
	 */
	public void startGame() {
		System.out.println("***** Welcome to Tic Tac Toe! *****");
	
		
		System.out.println("\nWould you like to see the rules? (yes/no)");
		String response = input.nextLine();
		
		// validate response
		response = yesNoValidator(response, input);
		
		if (response.equalsIgnoreCase("yes")) {
			System.out.println(instructions());
		}
		
	}
	
	
	/**
	 * This method validates yes/no response
	 * @param response user inputed response
	 * @param userInput scanner
	 * @return response that is either "yes" or "no"
	 */
	public String yesNoValidator(String response, Scanner userInput) {
		while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
			System.out.println("Invalid entry. Please try again:");
			response = userInput.nextLine();
		}
		
		return response;
	}
	
	
	/**
	 * This method returns string that is the game instructions
	 * @return String that is the game instructions
	 */
	public String instructions() {
		String instructions = "- The game is played on a square grid.\r\n"
				+ "- Two players take turns, one using \"X\" and the other using \"O.\"\r\n"
				+ "- The first player to get an entire row of their marks (horizontally, vertically, or diagonally) wins.\r\n"
				+ "- A mark is placed on the board by specifying the row number and the column number\r\n"
				+ "- If the grid is filled without a winner, the game is a stale mate.\r\n";
		return instructions;
	}
	
	

	public void playTurn() {
		gameOver = false;

		while (!gameOver) {
			player1 = players.dequeue();
			player1.play();
			
			if (!gameOver) {
				player2 = players.dequeue();
				player2.play();
			}
			
			players.enqueue(player1);
			players.enqueue(player2);
		}
	}
	
	
	/**
	 * Method that declares Stale mate then calls the gameOver method
	 */
	public void staleMate() {
		System.out.println("\nStale Mate");
		printScores();
		gameOver = true;
		gameOver();
		
	}
	
	
	/**
	 * Method that declares winner, then calls gameOver method
	 * @param player
	 */
	public void declareWinner(Player player) {
		System.out.println(player.name + " won!");
		gameOver = true;
		
		player.incrementScore();
		printScores();
		gameOver();
	}
	
	
	/**
	 *  Method that prints scores
	 */
	public void printScores() {
		System.out.println("\nScores:");
		System.out.println("\t" + player1.getName() + ": " + player1.getScore());
		System.out.println("\t" + player2.getName() + ": " + player2.getScore());
	}
	/*
	 * Method that asks player if would like to play again
	 */
	public void gameOver() {

		System.out.println("\nWould you like to play again?");
		String response = input.nextLine();
		response = yesNoValidator(response, input);
		
		if (response.equalsIgnoreCase("no")) {
			System.out.println("\nGoodbye!");
			System.exit(0);
		}
	}
}
