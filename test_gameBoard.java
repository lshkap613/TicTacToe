
public class test_gameBoard {

	public static void main(String[] args) {
		GameBoard gb = new GameBoard();
		
		gb.setCell(1, 0, 'X');
		gb.setCell(1, 1, 'O');
		
		gb.setCell(2, 2, 'X');
		
		gb.setCell(0, 0, 'X');
		gb.setCell(0, 2, 'X');
		
		gb.preventRowVictory('O');
		
	}

}
