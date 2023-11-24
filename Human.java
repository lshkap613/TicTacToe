import java.util.Scanner;

public class Human extends Player {
	private char xO;
	
	/**
	 * Human player constructor
	 * @param ttt TicTacToe game
	 * @param gb GameBoard
	 * @param xO char human's playing piece
	 */
	public Human(TicTacToe ttt, GameBoard gb, char xO, int score) {
		super(ttt, gb, score);
		this.xO = xO;
		this.score = score;
	}
	
	Scanner input = new Scanner(System.in);
	
	@Override
	/**
	 * Play method for human asks human to select a cell and places
	 * the player's mark on that cell
	 */
	public void play() {
		int row, col;

		// Ask human to select a cell
		System.out.println("\n" + this.name + ", select a cell");
		
		do {
			// choose row to place game piece
			System.out.print("Row: ");
			row = input.nextInt();
			
			// make sure inputed row is valid
			while (!colRowValidate(row)) {
				System.out.print("Invalid input. Please enter 1-" + gb.getDimension() + ". \nRow: ");
				row = input.nextInt();
			}
				
			
			// choose column to place game piece
			System.out.print("Column: ");
			col = input.nextInt();
			
			while(!colRowValidate(col)) {
				System.out.print("Invalid Input. Please enter 1-" + gb.getDimension() + ". \nColumn: ");
				col = input.nextInt();
			}
		
		} while(taken(row, col)); // if that cell taken, ask to input row/column numbers again
		
		// clear keyboard buffer
		input.nextLine();
		
		
		// convert row and column numbers to index values
		row -= 1;
		col -= 1;
		
		// set mark down at requested cell and print board
		gb.setCell(row, col, xO);
		gb.printGameBoard();
		
		// check if human won game. If so, declare human as winner
		if (gb.checkIfWon(row, col, xO)) {
			ttt.declareWinner(this);
		}

	}
	
	
	/**
	 * Method that validates row or column number
	 * @param colRow row or column number to validate
	 * @return true if valid, false if not
	 */
	public boolean colRowValidate(int colRow) {
		if (colRow < 1 || colRow > gb.getDimension()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Method that determines if a cell is already taken
	 * @param row selected cell's row number
	 * @param col selected cell's column number
	 * @return true if taken, false if not
	 */
	public boolean taken(int row, int col) {
		if (gb.getSpot(row - 1, col - 1) == ' ') {
			return false;
		} 
		
		System.out.println("\nThat spot has already been taken. Please try again.\n");
		return true;
	}

}
