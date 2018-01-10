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
	
	/**
	 * This constructor permit to assign the default IA (Random) to his color.
	 * @param firstColor && secondColor are the color of the IA chosen
	 * @param strategy is the name of the IA chosen
	 */
	public ComputerPlayer(Strategy strategy, Color firstColor) {
		super(strategy.getName() + "-" + firstColor, firstColor);
		this.strategy = strategy;
	}
	
	public ComputerPlayer(Strategy strategy, Color firstColor, Color secondColor) {
		super(strategy.getName() + "-" + firstColor, firstColor, secondColor);
		this.strategy = strategy;
	}

	/**
	 * @param board is the board
	 * @return the move to be made by the strategy
	 */
	public int[] determineBase(Board board) {
		if (super.getColor()[1] != null) {
			return strategy.determineBase(board, super.getColor()[0], super.getColor()[1]);
		} else {
			return strategy.determineBase(board, super.getColor()[0]);
		}
	}
	
	/**
	 * @param board is the board
	 * @return the move to be made by the strategy
	 */
	public Object[] determineMove(Board board) {
		if (super.getColor()[1] != null) {
			return strategy.determineMove(board, super.getColor()[0], super.getColor()[1]);
		} else {
			return strategy.determineMove(board, super.getColor()[0]);

		}
	}
}