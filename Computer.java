public class Computer extends Player {
	private int[] boardCell;
	private char xO = 'O';
	
	/**
	 * Computer player constructor
	 * @param ttt TicTacToe game
	 * @param gb GameBoard
	 * @param name computer's name
	 * @param xO computer's game piece
	 */
	public Computer(TicTacToe ttt, GameBoard gb, String name, char xO, int score) {
		super(ttt, gb, name, score);
		this.xO = xO;
		this.score = score;
	}
	
	
	/**
	 * computer's play method:
	 * 1) Checks for a victory move. If this does not exits:
	 * 2) If applicable, prevents a winning shot by the other player. Otherwise:
	 * 3) Looks for a non pointless move to make
	 * 4) If that doesn't exist, sets its mark down on the first available spot
	 */
	public void play() {
		
		System.out.println(this.name + "'s turn.");
		
		// check for potential victory move in row and save indexes in boardCell
		boardCell = gb.checkRowVictory(xO);
		
		// if boardCell is not -1, -1, set returned cell indexes, print game board, 
		// and declare human as winner
		if (boardCell[0] != -1) {
			gb.setCell(boardCell[0], boardCell[1], xO);
			gb.printGameBoard();
			ttt.declareWinner(this);
		// otherwise, check columns for victory shot and set new output to boardCell
		} else {
			boardCell = gb.checkColVictory(xO);
			
			/*
			 *  if boardCell is not -1, -1, set returned cell indexes, print game board, 
			 *  and declare human as winner
			 */
			if (boardCell[0] != -1) {
				gb.setCell(boardCell[0], boardCell[1], xO);
				gb.printGameBoard();
				ttt.declareWinner(this);

			/*
			 * otherwise, check left-to-right diagonal for victory shot and set new output 
			 * to boardCell
			 */
			} else {
				boardCell = gb.checkLRDiagVictory(xO);
				
				// repeat above process if boardCell is -1, -1
				if (boardCell[0] != -1) {
					gb.setCell(boardCell[0], boardCell[1], xO);
					gb.printGameBoard();
					ttt.declareWinner(this);

				/*
				 * otherwise, check right-to-left diagonal for victory shot and set new output 
				 * to boardCell
				 */
				} else {
					boardCell = gb.checkRLDiagVictory(xO);
					
					// repeat above process for right-to-left diagonal if boardCell is -1, -1
					if (boardCell[0] != -1) {
						gb.setCell(boardCell[0], boardCell[1], xO);
						gb.printGameBoard();
						ttt.declareWinner(this);
						
					/*
					 * otherwise, move on to seeing if there's a victory it can prevent
					 * start by checking rows to see if human is one move away from victory.
					 * If yes, save indexes to boardCell. If not, boardCell is -1, -1
					 */
					} else {
						boardCell = gb.preventRowVictory(xO);
						
						/*
						 * if not (boardCell = -1, -1), prevent a column victory and save output
						 * to boardCell. If yes, save indexes to boardCell. If not, boardCell is -1, -1
						 * If boardCell is anything other than -1, -1, skip to the else statement
						 */
						if (boardCell[0] != -1) {
							gb.setCell(boardCell[0], boardCell[1], xO);
							gb.printGameBoard();
						
						} else {
							boardCell = gb.preventColVictory(xO);
							
							if (boardCell[0] != -1) {
								gb.setCell(boardCell[0], boardCell[1], xO);
								gb.printGameBoard();

							} else {
								/*
								 * if no column victory to prevent (boardCell = -1, -1), prevent a victory 
								 * in the left-to-right diagonal and save output to boardCell. If this is 
								 * anything other than -1, -1, skip to the else statement
								 */
								boardCell = gb.preventLRDiagVictory(xO);
								
								if (boardCell[0] != -1) {
									gb.setCell(boardCell[0], boardCell[1], xO);
									gb.printGameBoard();

								} else {
									/*
									 * if no left-to-right diagonal victory to prevent (boardCell = -1, -1), prevent 
									 * a victory in the left-to-right diagonal and save output to boardCell. If this 
									 * is anything other than -1, -1, skip to the else statement
									 */
									boardCell = gb.preventRLDiagVictory(xO);
									
									if (boardCell[0] != -1) {
										gb.setCell(boardCell[0], boardCell[1], xO);
										gb.printGameBoard();

									} else {
										/*
										 * If no right-to-left diagonal victory to prevent (boardCell = -1, -1), look 
										 * for a move that might eventually lead to victory among the rows and save
										 * the indexes of the move to boardCell, or -1, -1 if no good moves left
										 */
										boardCell = gb.rowGoodMove(xO);
									
										if (boardCell[0] != -1) {
											gb.setCell(boardCell[0], boardCell[1], xO);
											gb.printGameBoard();

										} else {
											/*
											 * If no good moves that could lead to victory among the rows (boardCell is -1, -1), 
											 * repeat for columns
											 */
											boardCell = gb.colGoodMove(xO);
											
											if (boardCell[0] != -1) {
												gb.setCell(boardCell[0], boardCell[1], xO);
												gb.printGameBoard();

											} else {
												// If no good moves among the columns, repeat for left-to-right diagonal
												boardCell = gb.leftRightGoodMove(xO);
												
												if (boardCell[0] != -1) {
													gb.setCell(boardCell[0], boardCell[1], xO);
													gb.printGameBoard();

												} else {
													// If no good moves among the left-to-right diagonal, repeat for right-to-left diagonal
													boardCell = gb.rightLeftGoodMove(xO);

													if (boardCell[0] != -1) {
														gb.setCell(boardCell[0], boardCell[1], xO);
														gb.printGameBoard();

													} else {
														// If there's still no good move, place mark in first empty spot on board
														if (boardCell[0] != -1) {
															gb.setCell(boardCell[0], boardCell[1], xO);
															gb.printGameBoard();

														} else {
															boardCell = gb.placeFirstEmpty(xO);
															
															if (boardCell[0] == -1) {
																// if all methods returned -1, stale mate
																ttt.staleMate();

															} else {
																/*
																 * otherwise, whichever method returned an output that is not -1, -1, it's output 
																 * was saved to boardCell and all subsequent code was skipped until here. Here, the
																 * mark is set down at the indexes determined by the last method
																 */
																gb.setCell(boardCell[0], boardCell[1], xO);
																// and updated board is printed
																gb.printGameBoard();
																if (gb.checkIfWon(boardCell[0], boardCell[1], xO)) {
																	ttt.declareWinner(this);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
