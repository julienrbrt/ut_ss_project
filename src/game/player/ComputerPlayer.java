package game.player;

import game.*;
import network.ServerPeer;

/**
 * Defines the methods for the AI.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ComputerPlayer extends Player {
	
	private Strategy strategy;
	//private final int playerNumber;
	private final String type = "Computer";
	
	/**
	 * Primary constructor for ComputerPlayer.
	 * @param strategy Type of AI
	 * @param firstColor Color for this AI
	 * @param playerNumber ID of this Player
	 */
	
	public ComputerPlayer(Strategy strategy, Color firstColor, int playerNumber) {
		super(strategy.getName() + "-" + firstColor, firstColor, playerNumber);
		this.strategy = strategy;
		//this.playerNumber = playerNumber;
	}
	
	/**
	 * Secondary constructor for ComputerPlayer.
	 * @param strategy Type of AI
	 * @param firstColor && secondColor Colors for this AI
	 * @param playerNumber ID of this Player
	 */
	
	public ComputerPlayer(Strategy strategy,
			Color firstColor, Color secondColor, int playerNumber) {
		super(strategy.getName() + "-" + firstColor, firstColor, secondColor, playerNumber);
		this.strategy = strategy;
		//this.playerNumber = playerNumber;
	}
	
	/**
	 * Forward determineBase to Strategy, while passing board.
	 * 	Generates coordinates for the starting base.
	 * <p>
	 * Should only be called once per game, at the beginning of the game.
	 * @param board Board to determine the base off of
	 * @return Array of coordinates to place the starting base on
	 * @see Strategy#determineBase(Board)
	 */
	
	public int[] determineBase(Board board) {
		return strategy.determineBase(board);
	}
	
	/**
	 * Forward determineMove to Strategy, while passing board and colorAmount.
	 * 	Generates a valid move, or an empty array if no moves are possible.
	 * @param board Board to determine move on
	 * @param colorAmount Amount of Colors this Player possesses
	 * @return Array of possible moves
	 * @see Strategy#determineMove(Board, int, Color, Color)
	 */
	
	public Object[] determineMove(Board board, int colorAmount) {
		return strategy.determineMove(board, colorAmount, super.getColor()[0], super.getColor()[1]);
	}
	
	/**
	 * Getter for strategy.
	 * @return Type of this AI
	 */
	public String getType() {
		return type;
	}

	// Useless for offline game
	@Override
	public ServerPeer getServerPeer() {
		// TODO Auto-generated method stub
		return null;
	}
}