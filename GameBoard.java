import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
	private char[][] board;
	private int dimension;
	private int[] result = new int[2];

	
	/**
	 * GameBoard constructor
	 * Structure: 2D array
	 */
	public GameBoard() {
		this.dimension = 3;
		board = new char[dimension][dimension];
		for (int col=0; col<dimension; col++) {
			for (int row=0; row<dimension; row++) {
				// initialize each cell of game board to single space
				board[col][row] = ' ';
			}
		}
	}
	
	/**
	 * GameBoard constructor
	 * Structure: 2D array
	 * @param dimension amount of rows/columns
	 */
	public GameBoard(int dimension) {
		this.dimension = dimension;
		board = new char[dimension][dimension];
		for (int col=0; col<dimension; col++) {
			for (int row=0; row<dimension; row++) {
				// initialize each cell of game board to single space
				board[col][row] = ' ';
			}
		}
	}

	/**
	 * 
	 * @param row row number of requested cell
	 * @param col column number of requested cell
	 * @return char at requested cell
	 */
	public char getSpot(int row, int col) {
		if (row < 0 || row > 2 || col < 0 || col > 2) {
			// ERROR
		}
		return board[row][col];
	}
	
	/**
	 * Method to get the number of rows/columns of the game board
	 * @return int number of rows/columns 
	 */
	public int getDimension() {
		return this.dimension;
	}
	
	/**
	 * Method to set dimensions of the game board
	 * @param dimension int value for how many rows/columns
	 * the game board should have
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * Method to set a mark at a specific cell on the game board
	 * @param row row of cell that would like to set
	 * @param col column of cell that would like to set
	 * @param xo char to set at the specified cell
	 */
	public void setCell(int row, int col, char xo) {
		// check if row, col is legal index in game board
		boolean check = rowColValidate(row, col);
		// if not, throw error
		if (!check) {
			// ERROR
		}
		board[row][col] = xo;
	}
	
	/**
	 * Method that checks if the player won the game
	 * @param row row of last mark placed down by human player
	 * @param col column of last mark placed down by human player
	 * @param xO last mark placed by human player on the game board
	 * @return boolean. true if human is winner, false if not
	 */
	public boolean checkIfWon(int row, int col, char xO) {
		// start with return variable set to true
		boolean winner = true;
		
		/*
		 * Search through every cell in row of the last mark placed by 
		 * player to see if it contains anything but the that player's
		 * mark. 
		 */
		for (int rowCell = 0; rowCell < this.dimension; rowCell++) {
			/*
			 * If so, this is not a winning row, and return variable is set
			 * to false.
			 */
			if (board[row][rowCell] != xO) {
				winner = false;
				break;
			}
		}
		// Otherwise, current player won and true is returned.
		if(winner) return true;
		
		/* 
		 * return variable reset to false, and process repeated for the
		 * column of the last mark set down by the player
		 */
		winner = true;
		for (int colCell = 0; colCell < this.dimension; colCell++) {
			if (board[colCell][col] != xO) {
				winner = false;
				break;
			}
		}
		if (winner) return true;
		
		/*
		 * If the last mark placed on the board by the current player was along
		 * the left-to-right diagonal line, repeat the process along this line.
		 */
		if (row == col) {
			winner = true;
			for (int rowCol = 0; rowCol < this.dimension; rowCol++) {
				if (board[rowCol][rowCol] != xO) {
					winner = false;
					break;
				}
			}
			if(winner) return true;
		}
		
		/*
		 * If the last mark placed on the board by the current player was along
		 * the right-to-left diagonal line, repeat the process along this line.
		 */
		if (row + col == this.dimension - 1) {	
			winner = true;
			for (int r = 0, c = this.dimension - 1; r < this.dimension && c >= 0; r++, c--) {
				if (board[r][c] != xO) {
					winner = false;
					break;
				}
			}
			if (winner) return true;
			}
			
			return false;
	}

	
	/**
	 * method that determines what the other mark is (X or O) based
	 * on the current player's mark.
	 * @param xO current player's mark
	 * @return other player's mark
	 */
	public char otherXO(char xO) {
		char otherXO;
		if (xO == 'X') {
			otherXO = 'O';
		} else {
			otherXO = 'X';
		}
		return otherXO;
	}
	
	
	// THE NEXT FOUR METHODS CHECK THE GAMEBOARD FOR MOVES THAT WILL LEAD TO IMMEDIATE VICTORY 
	
	/**
	 * This method checks each row for a possible winning shot
	 * @param xO the current player's mark
	 * @return int[] {-1, -1} if no move will lead to a victory
	 * in a row, or the indexes of the spot that will lead to the
	 * first possible row victory 
	 */
	public int[] checkRowVictory(char xO) {
		char otherXO = otherXO(xO); // other player's mark
		/* initiate boolean variable that describes whether the 
		 * current row contains the other player's mark to false
		 */
		boolean hasOther = false; 
		
		// outer loop: checks each row
		for (int row=0; row<this.dimension; row++) {
			/* When checking each row, initiate empty space count to 0,
			 * and reset hasOther to false
			 */
			int emptySpaceCount = 0;
			hasOther = false;

			// inner loop: checks each cell of current row
			for (int col=0; col<this.dimension; col++) {
				/* If a row has the other player's mark, the row cannot be
				 * a victory row, so break out of the inner loop and continue
				 * on to check the next row
				 */
				if (board[row][col] == otherXO) {
					hasOther = true;
					break;
				}
				
				// keep track of blank spaces and save indexes of last blank space
				if (board[row][col] == ' ') {
					emptySpaceCount++;
					result[0] = row;
					result[1] = col;
				}
				
				/* if there is more than one empty space in a row, the row
				 * cannot be a victory row, so break out of the inner loop
				 * and continue on to check the next row
				 */
				if (emptySpaceCount > 1) {
					break;
				}
			}	
			
			/* after iterating through an entire row, if there is only one
			 * blank space and the row does not contain the other player's
			 * mark, it is a victory row so return the indexes
			 */
			
			if (emptySpaceCount == 1 && !hasOther) {
				return result;
			}
		
		}
		
		/*
		 * If this code was reached, it means there are no victory rows.
		 * set return indexes to -1, -1 and return
		 */
		result[0] = -1;
		result[1] = -1;
		return result;

	}
	
	
	/**
	 * This method checks each column for a possible winning shot.
	 * It works the same way the checkRowVictory method works.
	 * @param xO the current player's mark
	 * @return int[] {-1, -1} if no move will lead to a victory
	 * in a column, or the indexes of the spot that will lead to the
	 * first possible column victory 
	 */
	public int[] checkColVictory(char xO) {
		char otherXO = otherXO(xO);
		boolean hasOther = false;
		
		for (int col=0; col<this.dimension; col++) {
			int emptySpaceCount = 0;
			hasOther = false;

			for (int row=0; row<this.dimension; row++) {
				if (board[row][col] == otherXO) {
					hasOther = true;
					break;
				}
				
				if (board[row][col] == ' ') {
					emptySpaceCount++;
					result[0] = row;
					result[1] = col;
				}
				
				if (emptySpaceCount > 1) {
					break;
				}
			}	
			
			if (emptySpaceCount == 1 && !hasOther) {
				return result;
			}
		}
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method the left-to-right diagonal for a possible winning shot.
	 * It works the similarly to the way the checkRowVictory method works.
	 * @param xO the current player's mark
	 * @return int[] {-1, -1} if no move will lead to a victory
	 * in the diagonal, or the indexes of the spot that will lead to the
	 * diagonal victory 
	 */
	public int[] checkLRDiagVictory(char xO) {
		char otherXO = otherXO(xO);
		int emptySpaceCount = 0;
		for (int coord=0; coord<this.dimension; coord++) {
			
			if (board[coord][coord] == otherXO) {
				result[0] = -1;
				result[1] = -1;
				return result;
			} 
			
			if (board[coord][coord] == ' ') {
				emptySpaceCount++;
				result[0] = coord;
				result[1] = coord;
			}

			if (emptySpaceCount > 1) {
				result[0] = -1;
				result[1] = -1;
				return result;
			}
		}
		return result;
	}
	
	
	/**
	 * This method checks the right-to-left diagonal for a possible winning shot.
	 * It works the similarly to the checkLRDiagvictory
	 * @param xO the current player's mark
	 * @return int[] {-1, -1} if no move will lead to a victory
	 * in the diagonal, or the indexes of the spot that will lead to the
	 * diagonal victory 
	 */
	public int[] checkRLDiagVictory(char xO) {
		char otherXO = otherXO(xO);
		int emptySpaceCount = 0;

		for (int row=0, col=this.dimension - 1; row< this.dimension; row++, col--) {
			if (board[row][col] == otherXO) {
				result[0] = -1;
				result[1] = -1;
				return result;
			}
			
			if (board[row][col] == ' ') {
				emptySpaceCount++;
				result[0] = row;
				result[1] = col;
			}	
			
			if (emptySpaceCount > 1) {
				result[0] = -1;
				result[1] = -1;
		 		break;
			}
		}
		return result;
	}
	
	
	// THE FOLLOWING FOUR METHODS CHECK IF THE OTHER PLAYER IS ONE MOVE AWAY FROM
	// VICTORY FOR THE PURPOSE OF PREVENTING THAT VICTORY
	
	/**
	 * This method checks if the other player is one move away from a victory in
	 * one of the rows
	 * @param xO the current player's mark
	 * @return -1, -1 if the other player is not one move away from victory in any
	 * of the rows, or the indexes of the first spot that would be the other player's 
	 * victory move (among the rows)
	 */
	public int[] preventRowVictory(char xO) {
		boolean hasThisXO = false;

		// loop through each row
		for (int row=0; row<this.dimension; row++) {
			/* At the beginning of each row, initialize emptySpaceCount to 0, 
			 * variable describing column index of last empty cell in the row to -1,
			 * and boolean variable describing whether the row includes the current 
			 * player's mark to false;
			 */ 
			int emptySpaceCount = 0;
			int lastEmptyCol = -1;
			// reset hasThisXO to false
			hasThisXO = false;

			// loop through each cell in the row
			for (int col=0; col<this.dimension; col++) {
				
				// if the row contains this players piece, the other player can't have a 
				// victory here so break and continue with next iteration of outer loop
				if (board[row][col] == xO) {
					hasThisXO = true;
					break;
				}
				
				// Keep track of last empty space and save the column number
				if (board[row][col] == ' ') {
					emptySpaceCount++;
					lastEmptyCol = col;
				}
			}	
			
			// After checking each cell in the row, if there is only one empty space and
			// the row does not include the current player's mark, then return the empty spot
			if (emptySpaceCount == 1 && !hasThisXO) {
				result[0] = row;
				result[1] = lastEmptyCol;
				return result;
			}
		}
		
		// If no rows have a victory shot to block, return -1, -1
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method checks if the other player is one move away from a victory in
	 * one of the columns. It works the same way the preventRowVictory method works
	 * @param xO the current player's mark
	 * @return -1, -1 if the other player is not one move away from victory in any
	 * of the columns, or the indexes of the first spot that would be the other player's 
	 * victory move (among the columns)
	 */
	public int[] preventColVictory(char xO) {
		boolean hasThisXO = false;

		for (int col=0; col<this.dimension; col++) {
			
			int emptySpaceCount = 0;
			int lastEmptyRow = -1;
			// reset hasThisXO to false
			hasThisXO = false;
			
			for (int row=0; row<this.dimension; row++) {
				// if the column contains this players piece, the other player can't have a victory here
				if (board[row][col] == xO) {
					hasThisXO = true;
					break;
				}
				
				if (board[row][col] == ' ') {
					emptySpaceCount++;
					lastEmptyRow = row;
				}
			}	
			
			if (emptySpaceCount == 1 && !hasThisXO) {
				result[0] = lastEmptyRow;
				result[1] = col;
				return result;
			}
		}
		
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method checks if the other player is one move away from a victory in
	 * the left-to-right diagonal. It works similarly to the preventRowVictory method
	 * @param xO the current player's mark
	 * @return -1, -1 if the other player is not one move away from victory the 
	 * left-to-right diagonal, or the indexes of the first spot that would be the other 
	 * player's victory move
	 */
	public int[] preventLRDiagVictory(char xO) {
		int emptySpaceCount = 0;
		int lastEmptyRowCol = -1;
		
		for(int rowCol = 0; rowCol < this.dimension; rowCol++) {
			if (board[rowCol][rowCol] == xO) {
				return result;
			}
			
			if (board[rowCol][rowCol] == ' ') {
				emptySpaceCount++;
				lastEmptyRowCol = rowCol;
			}
		}
		
		if (emptySpaceCount == 1) {
			result[0] = lastEmptyRowCol;
			result[1] = lastEmptyRowCol;
			return result;
		}
		
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method checks if the other player is one move away from a victory in
	 * the right-to-left diagonal. It works similarly to the preventRowVictory method
	 * @param xO the current player's mark
	 * @return -1, -1 if the other player is not one move away from victory the 
	 * right-to-left diagonal, or the indexes of the first spot that would be the other 
	 * player's victory move
	 */
	public int[] preventRLDiagVictory(char xO) {
		
		int emptySpaceCount = 0;
		int lastEmptyRow = -1;
		int lastEmptyCol = -1;
		
		for(int row = 0, col = this.dimension - 1; row < this.dimension && col >= 0; row++, col--) {
			if (board[row][col] == xO) {
				return result;
			}
			
			if (board[row][col] == ' ') {
				emptySpaceCount++;
				lastEmptyRow = row;
				lastEmptyCol = col;
			}
		}
		
		if (emptySpaceCount == 1) {
			result[0] = lastEmptyRow;
			result[1] = lastEmptyCol;
			return result;
		}
		
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	// THE FOLLOWING FOUR METHODS SEARCH FOR A MOVE TO MAKE THAT MIGHT 
	// EVENTUALLY LEAD TO VICTORY
	
	/**
	 * This method searches the rows for a move that might eventually lead
	 * to victory. It checks each row to see if it's not already full and
	 * if it doesn't include the other player's piece. The method stops 
	 * searching after finding the first row to which these rules apply.
	 * @param xO the current player's mark
	 * @return For a row that is not full and does not include the other
	 * player's piece, the indexes of a randomly selected empty cell in that
	 * row is returned. Otherwise -1, -1 is returned.
	 */
	public int[] rowGoodMove(char xO) {
		char otherXO = otherXO(xO); // other player's mark
		// initiate boolean variable describing whether the row is full to true
		boolean isFull = true;
		boolean hasOther = false;
		int randomCol; // initiate variable that will hold random column number
		
		// for each row...
		for (int row=0; row<this.dimension; row++) {
			hasOther = false;
					
			// check each spot
			for (int col=0; col <this.dimension; col++) {
				
				// to see if there is the opponent's piece. If there is,
				// break and continue on to checking next row
				if (board[row][col] == otherXO) {
					hasOther = true;
					 break;
				}
				
				/* otherwise make sure row is not already full by setting
				 * isFull to false as soon as a blank space is found
				 */
				if (board[row][col] == ' ') {
					isFull = false;
				}
			}
			
			// After checking every cell in a row, if isFull is false and doesn't have other mark...
			if(!isFull && !hasOther) {
				/* generate a random column number for this row, and check the spot at the current
				 * row and randomly-generated column to see if it's not already taken. If taken, 
				 * repeat this
				 * process until an empty spot is found.
				 */
				do {
					randomCol = ThreadLocalRandom.current().nextInt(0, this.dimension);
				} while (board[row][randomCol] != ' ');
				
				// and return the coordinates of the empty space
				result[0] = row;
				result[1] = randomCol;
				return result;
			}
			// but if the row is full, check the next row (continue to next iteration of outer loop)
		}
		
		// If all rows are full, return -1, -1 (This code should technically never be accessed since a 
		// stale mate should have been declared by now)
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method searches the columns for a move that might eventually lead
	 * to victory. It checks each column to see if it's not already full and
	 * if it doesn't include the other player's piece. The method stops 
	 * searching after finding the first column to which these rules apply.
	 * It works the same way the rowGoodMove method works
	 * @param xO the current player's mark
	 * @return For a column that is not full and does not include the other
	 * player's piece, the indexes of a randomly selected empty cell in that
	 * column is returned. Otherwise -1, -1 is returned.
	 */
	public int[] colGoodMove(char xO) {
		char otherXO = otherXO(xO);
		boolean isFull = true;
		boolean hasOther = false;
		int randomRow;

		// for each column...
		for (int col=0; col<this.dimension; col++) {
			hasOther = false;
					
			// check each spot
			for (int row = 0; row < this.dimension; row++) {
				// to see if there is the opponent's piece
				if (board[row][col] == otherXO) {
					hasOther = true;
					break;
				}
				
				// otherwise make sure row is not already full
				if (board[row][col] == ' ') {
					isFull = false;
				}	
			}
				
			// and if it's not full/ doesn't have the other...
			if(!isFull && !hasOther) {
				// generate a random column for this row until hit an empty spot
				do {
					randomRow = ThreadLocalRandom.current().nextInt(0, this.dimension);
				} while (board[randomRow][col] != ' ');
				
				// and return the coordinates
				result[0] = randomRow;
				result[1] = col;
				return result;
			}
			// but if the row is full, check the next row
		}
			
		// If all rows are full or contain the other player's mark, return -1, -1
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method searches the left-to-right diagonal for a move that might 
	 * eventually lead to victory. It checks the diagonal to see if it's not 
	 * already full and if it doesn't include the other player's piece.
	 * @param xO the current player's mark
	 * @return If the diagonal is not full and does not include the other
	 * player's piece, the indexes of a randomly selected empty cell in that
	 * diagonal is returned. Otherwise -1, -1 is returned.
	 */
	public int[] leftRightGoodMove(char xO) {
		char otherXO = otherXO(xO); // other player's mark
		boolean isFull = true; // initialize boolean describing if diagonal is full to true
		boolean hasOther = false;
		int randomLRDiag = -1; // default value

		// iterate along the diagonal
		for (int coord = 0; coord < this.dimension; coord++) {
			
			// if diagonal contains other player's mark, stop searching
			if (board[coord][coord] == otherXO) {
				hasOther = true;
				break;
			}
			
			// set isFull to false once a blank spot is found
			if (board[coord][coord] == ' ') {
				isFull = false;
			}
		}
		
		// if the diagonal is not full/ doesn't have other mark:
		if (!isFull && !hasOther) {
			// generate random row/column index (since row number = column number on the left-to-right diagonal)
			do {
				randomLRDiag = ThreadLocalRandom.current().nextInt(0, this.dimension);
			} while (board[randomLRDiag][randomLRDiag] != ' '); // repeat if spot at that row/column index is full
			
			// return index of randomly selected blank cell
			result[0] = randomLRDiag;
			result[1] = randomLRDiag;
			return result;
		}
		
		// If the diagonal is full or contains the other player's mark, return -1, -1
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method searches the left-to-right diagonal for a move that might 
	 * eventually lead to victory. It checks the diagonal to see if it's not 
	 * already full and if it doesn't include the other player's piece. This method
	 * works similarly to the leftRightGoodMove method.
	 * @param xO the current player's mark
	 * @return If the diagonal is not full and does not include the other
	 * player's piece, the indexes of a randomly selected empty cell in that
	 * diagonal is returned. Otherwise -1, -1 is returned.
	 */
	public int[] rightLeftGoodMove(char xO) {
		char otherXO = otherXO(xO); // other player's mark
		boolean isFull = true; // initialize boolean describing if diagonal is full to true
		boolean hasOther = false;
		int randomRLDiag = -1; // default

		// iterate along the diagonal
		for (int col = this.dimension - 1, row = 0; col >= 0 && row < this.dimension; col--, row++) {
			
			// if diagonal contains other player's mark, stop searching
			if (board[row][col] == otherXO) {
				hasOther = true;
				break;
			}
			
			// set isFull to false once a blank spot is found
			if (board[row][col] == ' ') {
				isFull = false;
			}
		}
		
		// if the diagonal is not full/ doesn't have other mark:
		// generate random row index. Corresponding column is calculated by taking the total number
		// of columns and subtracting one less than the row index
		if (!isFull && !hasOther) {
			do {
				randomRLDiag = ThreadLocalRandom.current().nextInt(0, this.dimension);
			} while (board[randomRLDiag][this.dimension - randomRLDiag - 1] != ' ');
			
			// return index of randomly selected blank cell
			result[0] = randomRLDiag;
			result[1] = this.dimension - randomRLDiag + 1;
			return result;
		}
		
		// If the diagonal is full or contains the other player's mark, return -1, -1
		result[0] = -1;
		result[1] = -1;
		return result;

	}
	
	
	/**
	 * This method looks for first available cell
	 * @param xO the current player's mark
	 * @return indexes of first empty cell or -1, -1 none exist.
	 */
	public int[] placeFirstEmpty(char xO) {
		// iterate through rows
		for (int row = 0; row < this.dimension; row++) {
			
			// iterate through cells in each row
			for (int col = 0; col < this.dimension; col++) {
				
				// check if spot is empty
				if (board[row][col] == ' ') {
					// and return indexes if so
					result[0] = row;
					result[1] = col;
					return result;
				}
			}
		}
		// If no empty cell found, return -1, -1
		result[0] = -1;
		result[1] = -1;
		return result;
	}
	
	
	/**
	 * This method checks that the row and column indexes are valid.
	 * @param row row index to validate
	 * @param col column index to validate
	 * @return true if valid row and column, false if one of the indexes are invalid
	 */
	public boolean rowColValidate(int row, int col) {
		
		if(row < 0) {
			System.out.println("Row number must not be less than 1");
		} else if (row > this.dimension - 1) {
			System.out.println("Row number must not be greater than " + this.dimension);
		} else if (col < 0) {
			System.out.println("Column number must not be less than 1");
		} else if (col > this.dimension - 1) {
			System.out.println("Column number must not be greater than " + this.dimension);
		} else {
			return true;
		}
		
		return false;
		
	}
	
	
	/**
	 * This method prints out the tic tac toe game board
	 */
	public void printGameBoard() {
		System.out.println();
		
		// for each row...
		for (int row = 0; row < this.dimension; row++) {
			
			// print out each cell 
			for (int col = 0; col < this.dimension; col++) {
				
				// first cell in each row: tab, space, character, space, dividing line
				if (col == 0) {
					System.out.print("\n\t " + String.valueOf(board[row][col]) + " |");
					
				// middle cells in each row: space, character, space, dividing line
				} else if (col > 0 && col < this.dimension - 1) {
					System.out.print(" " + String.valueOf(board[row][col]) + " |");
					
				// last cell in each row: space, character
				} else {
					System.out.println(" " + String.valueOf(board[row][col]));
				}
								
			}
			
			double dashes = Math.floor(this.dimension*3.4); 
			// 3.4 is a non-precise factor I came up with when playing around with different 
			// board sizes and the unproportional length of the dash
			
			// after each row, print out dashes, the amount of which varies according to board size
			if (row != this.dimension - 1) {
				System.out.print("\t");
				for (int length = 0; length < this.dimension; length++) {
					for (int dash = 0; dash < dashes/this.dimension; dash++) {
						System.out.print("-");
					}
				}
			}

		}
		
		System.out.println();
		
	}
}
