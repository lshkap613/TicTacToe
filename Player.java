
public class Player {
	protected String name;
	protected GameBoard gb;
	protected TicTacToe ttt;
	protected int score;
	
	/**
	 * Player constructor. Sets player's name to empty string
	 * @param ttt TicTacToe game
	 * @param gb GameBoard
	 */
	public Player(TicTacToe ttt, GameBoard gb, int score) {
		this.ttt = ttt;
		this.gb = gb;
		this.name = "";
		this.score = score;
	}
	
	/**
	 * Player constructor
	 * @param ttt TicTacToe game
	 * @param gb GameBoard
	 * @param name player's name
	 */
	public Player(TicTacToe ttt, GameBoard gb, String name, int score) {
		this.ttt = ttt;
		this.gb = gb;
		this.name = name;
		this.score = score;
	}
	
	/**
	 * Set player's name
	 * @param name String name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * get Player's name
	 * @return player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * (Empty) Play method
	 */
	public void play() {
		
	}
	
	
	/**
	 * Method to increment player's score by 1
	 */
	public void incrementScore() {
		this.score += 1;
	}
	
	
	/**
	 * Method to get player's score
	 * @return int player's score
	 */
	public int getScore() {
		return this.score;
	}
}
