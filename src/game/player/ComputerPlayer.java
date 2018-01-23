package game.player;

import game.*;

/**
 * This is the class ComputerPlayer defining the methods for the IA.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ComputerPlayer extends Player {
	
	private Strategy strategy;
	private final int playerNumber;
	
	/**
	 * This constructor permit to assign the default IA (Random) to his color.
	 * @param firstColor && secondColor are the color of the IA chosen
	 * @param strategy is the name of the IA chosen
	 */
	public ComputerPlayer(Strategy strategy, Color firstColor, int playerNumber) {
		super(strategy.getName() + "-" + firstColor, firstColor, playerNumber);
		this.strategy = strategy;
		this.playerNumber = playerNumber;
	}
	
	public ComputerPlayer(Strategy strategy, Color firstColor, Color secondColor, int playerNumber) {
		super(strategy.getName() + "-" + firstColor, firstColor, secondColor, playerNumber);
		this.strategy = strategy;
		this.playerNumber = playerNumber;
	}

	/**
	 * @param board is the board
	 * @return the move to be made by the strategy
	 */
	public int[] determineBase(Board board) {
		return strategy.determineBase(board);
	}
	
	/**
	 * @param board is the board
	 * @return the move to be made by the strategy
	 */
	public Object[] determineMove(Board board, int colorAmount) {
		return strategy.determineMove(board, colorAmount, super.getColor()[0], super.getColor()[1]);
	}
}