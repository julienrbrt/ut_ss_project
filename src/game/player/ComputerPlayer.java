package game.player;

import game.*;

/**
 * This is the class ComputerPlayer definining the methods for the IA.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ComputerPlayer extends Player {
	
	private Strategy strategy;
	
	/**
	 * This constructor permit to assign the default IA (Random) to his color.
	 * @param color is the mark of the IA chosen
	 * @param strategy is the name of the IA chosen
	 */
	public ComputerPlayer(Color color, Strategy strategy) {
		super(strategy.getName() + "-" + color, color);
		this.strategy = strategy;
	}

	/**
	 * This constructor permit to assign the default IA (Random) to his color.
	 * @param color is the color of the IA chosen
	 */
	public ComputerPlayer(Color color) {
		super("Random" + "-" + color, color);
		strategy = new RandomStrategy();
	}

	/**
	 * @param board is the board
	 * @return the move to be made by the strategy
	 */
	public int[] determineBase(Board board) {
		return strategy.determineMove(board, super.getColor());
	}
	
	/**
	 * @param board is the board
	 * @return the move to be made by the strategy
	 */
	public Object[] determineMove(Board board) {
		return strategy.determineMove(board, super.getColor());
	}
}