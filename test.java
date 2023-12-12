import java.util.*;

public class test {

	public static void main(String[] args) {
		Queue<Player>players = new Queue<Player>();
		TicTacToe ttt = new TicTacToe(players);
		Scanner input = new Scanner(System.in);

		// start game
		ttt.startGame();
		
		// create gameBoard based on user-requested dimensions
		GameBoard gb = new GameBoard(determineSize(input));
		
		// Create the players
		Player human = new Human(ttt, gb, 'X', 0);
		Player computer = new Computer(ttt, gb, "Computer", 'O', 0);
		
		// add players to the queue
		players.enqueue(human);
		players.enqueue(computer);
		
		
		// Ask user's name
		System.out.print("\nWhat is your name?  ");
		String name = input.nextLine();
		
		// set human player's name
		human.setName(name);
		
		// start taking turns
		while(true) {
			// print board
			gb.printGameBoard();
			
			ttt.playTurn();
			
			gb = new GameBoard(determineSize(input));
			players = new Queue<Player>();
			ttt = new TicTacToe(players);
			int humanScore = human.getScore();
			String keepName = human.getName();
			human = new Human(ttt, gb, 'X', humanScore);
			human.setName(keepName);
			
			int computerScore = computer.getScore();
			computer = new Computer(ttt, gb, "Computer", 'O', computerScore);
			players.enqueu(human);
			players.enqueu(computer);
		}
		
	}
	
	/**
	 * Method that determines size of game board based on user input
	 * @param input Scanner
	 * @return dimension
	 */
	public static int determineSize(Scanner input) {
		System.out.println("\nHow many rows/columns would you like your board to have?");
		int dimension = input.nextInt();
		
		while (dimension < 3) {
			System.out.println("\nTic Tac Toe board must be at least 3x3");
			dimension = input.nextInt();
		}
		
		while (dimension > 10) {
			System.out.println("\nWe don't recommend using such a big board."
					+ "\nTry a number not greater than 10");
			dimension = input.nextInt();
		}
		input.nextLine();
		
		return dimension;
	}

}
