package game.player;

import game.*;

/**
 * This class is an inferface managing the Strategy of the Ringgz game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public interface Strategy {
	
	/**
	 * @return name of the strategy
	 */
	public String getName();
	
	/**
	 * @param b The board of the game
	 * @param c The color which must make the move
	 * @return the next legal move on the board b for the player with Color c
	 */
	public int[] determineMove(Board b, Color c);
}