package game.player;

import game.*;

/**
 * Interface managing the Strategies.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public interface Strategy {
	
	/**
	 * Getter for name of this Strategy
	 * @return Name of this Strategy
	 */
	
	public String getName();
	
	/**
     * Generates coordinates for the starting base.
	 * <p>
	 * Should only be called once per game, at the beginning of the game.
	 * @param board Board to determine the base off of
	 * @return Array of coordinates to place the starting base on
     */
	
	public int[] determineBase(Board board);
	
	/**
	 * Generates a valid move, or an empty array if no moves are possible.
	 * @param board Board to determine move on
	 * @param colorAmount Amount of Colors this Player possesses
	 * @param firstColor Primary Color of this Player
	 * @param secondColor Secondary Color of this Player, if applicable. Otherwise null
	 * @return Array of possible moves
	 */
	
	public Object[] determineMove(Board board,
			int colorAmount, Color firstColor, Color secondColor);
}