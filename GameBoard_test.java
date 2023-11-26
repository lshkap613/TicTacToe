import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameBoard_test {
	char currentPlayerMark = 'X';

	@Test
	void checkRowVictory_test1() {
		char[][] firstRowVictory = { {'X', ' ', 'X'},
									 {'O', 'X', ' '},
									 {' ', 'O', 'O'} };

		GameBoard board = new GameBoard(firstRowVictory);
		
		int[] result = board.checkRowVictory(currentPlayerMark);
		int [] expectedResult = {0, 1};
		assertEquals(expectedResult[0], result[0]);
		assertEquals(expectedResult[1], result[1]);
	}
	
	void checkRowVictory_test2() {
		char[][] secondRowVictory = { {' ', 'X', 'O'},
									  {'X', 'X', ' '},
									  {'O', 'O', ' '} };
		//char[][] noRowVictory;
		GameBoard board = new GameBoard(secondRowVictory);
		
		int[] result = board.checkRowVictory(currentPlayerMark);
		int [] expectedResult = {1, 2};
		assertEquals(expectedResult[0], result[0]);
		assertEquals(expectedResult[1], result[1]);
	}
	
	void checkRowVictory_test3() {
		char[][] noRowVictory = { {' ', 'X', 'O'},
								  {' ', 'X', ' '},
								  {' ', 'O', ' '} };
		GameBoard board = new GameBoard(noRowVictory);
		
		int[] result = board.checkRowVictory(currentPlayerMark);
		int [] expectedResult = {-1, -1};
		assertEquals(expectedResult[0], result[0]);
		assertEquals(expectedResult[1], result[1]);
	}
	
	void checkRightLeftGoodMove_test1() {
		char[][] rightLeftGoodMove = { {' ', 'X', ' '},
				  					   {' ', ' ', ' '},
				  					   {'X', 'O', ' '} };
		
		GameBoard board = new GameBoard(rightLeftGoodMove);
		
		int[] result = board.checkRowVictory(currentPlayerMark);
		boolean isValidResult = (result[0] == 0 && result[1] == 2) 
								|| (result[0] == 1 && result[1] == 1);
	    assertTrue(isValidResult);
	}
	
	void checkRightLeftGoodMove_test2() {
		char[][] rightLeftNoGoodMove = { {' ', 'X', ' '},
				  					     {' ', 'O', ' '},
				  					     {'X', 'O', ' '} };
		
		GameBoard board = new GameBoard(rightLeftNoGoodMove);
		
		int[] result = board.checkRowVictory(currentPlayerMark);
		int [] expectedResult = {-1, -1};
		assertEquals(expectedResult[0], result[0]);
		assertEquals(expectedResult[1], result[1]);
	}

}
