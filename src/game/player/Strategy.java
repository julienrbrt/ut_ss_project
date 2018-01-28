package game.player;

import game.*;

/**
 * This class is an interface managing the Strategy of the Ringgz game.
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
	 * @param board The board of the game
	 * @param firstColor the color which must make the move
	 * @return the next legal move on the board for the player with Color firstColor
	 */
	public int[] determineBase(Board board);
	
	/**
	 * @param board The board of the game
	 * @param firstColor and secondColor The color which must make the move
	 * @return the next legal move on the board for the player with Color firstColor or secondColor
	 */
	public Object[] determineMove(Board board,
			int colorAmount, Color firstColor, Color secondColor);
}