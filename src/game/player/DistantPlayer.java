package game.player;

import game.Board;
import network.*;

/**
 * Class permitting to define a distant player (ie. playing from network).
 * @author Richard
 * @author Julien
 *
 */
public class DistantPlayer extends Player {

	private final String type = "Distant";
	private ServerPeer player;

	/**
	 *  Constructor for one color.
	 * @param name the name of the user
	 * @param firstColor, it's first color
	 * @param playerNumber, his turn number in the game
	 */
	public DistantPlayer(String name, Color firstColor, int playerNumber, ServerPeer player) {
		super(name, firstColor, playerNumber);
		this.player = player;
	}
	
	/**
	 *  Constructor for one color.
	 * @param name the name of the user
	 * @param firstColor, it's first color
	 * @param secondColor, it's second color
	 * @param playerNumber, his turn number in the game
	 */
	public DistantPlayer(String name, Color firstColor, Color secondColor,
			int playerNumber, ServerPeer player) {
		super(name, firstColor, secondColor, playerNumber);
		this.player = player;
	}
		
	/**
	 * Getter for the ServerPeer.
	 * @return the server peer
	 */
	public ServerPeer getServerPeer() {
		return player;
	}
	
	/**
	 * Determine the first base placement and check of the distant user. Useless Online
	 * @param board, the board of the game
	 */
	public int[] determineBase(Board board) {
		return null;
	}

	/**
	 * Determine the ring placement of the user. Useless Online
	 * @param board, the board of the game
	 */
	public Object[] determineMove(Board board, int colorsAmount) {
		return null;
	}

	/**
	 * Getter for strategy.
	 * @return Type of that Distant player. Useless Online
	 */
	public String getType() {
		return type;
	}
}
