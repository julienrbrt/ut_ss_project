package game.player;

import game.*;

/**
 * Abstract class for keeping/simulating a player in the Ringzz game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public abstract class Player {

    // -- Instance variables -----------------------------------------

    private String name;
    private Color firstColor;
    private Color secondColor;
    private final String type = "none";

    // -- Constructors -----------------------------------------------

    /*@
       requires name != null;
       requires firstColor == Color.GREEN || color== Color.PURPL ||
       	color== Color.REDDD || color== Color.YELLOW;
       requires secondColor == Color.GREEN || color== Color.PURPL ||
       	color== Color.REDDD || color== Color.YELLOW || null;;
       ensures this.getName() == name;
     */
    
    /**
     * Primary constructor for Player.
     * @param name Name of the Player
     * @param firstColor Primary Color of this Player
     * @param playerNumber ID of this Player
     */
    
    public Player(String name, Color firstColor, int playerNumber) {
        this.name = name;
        this.firstColor = firstColor;
    }
    
    /**
     * Secondary constructor for Player.
     * @param name Name of the Player
     * @param firstColor Primary Color of this Player
     * @param secondColor Secondary Color of this Player
     * @param playerNumber ID of this Player
     */
    
    public Player(String name, Color firstColor, Color secondColor, int playerNumber) {
        this.name = name;
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    // -- Queries ----------------------------------------------------

    /**
     * Getter for name.
     * @return Name of this Player
     */
    
    /*@ pure */
    public String getName() {
        return name;
    }

    /**
     * Getter for the Color of this player. Multiple Colors if applicable.
     * @return Array of Colors, corresponding to the Colors assigned to this Player
     */
    
    /*@ pure */
    public Color[] getColor() {
        Color[] colors = {firstColor, secondColor};
        return colors;
    }

    /*@
       requires board != null & !board.gameOver();
     */
    
    /**
     * Generates coordinates for the starting base.
	 * <p>
	 * Should only be called once per game, at the beginning of the game.
	 * @param board Board to determine the base off of
	 * @return Array of coordinates to place the starting base on
	 * @see Strategy#determineBase(Board)
     */
    
    public abstract int[] determineBase(Board board);
    
    /**
	 * Generates a valid move, or an empty array if no moves are possible.
	 * @param board Board to determine move on
	 * @param colorAmount Amount of Colors this Player possesses
	 * @return Array of possible moves
	 * @see Strategy#determineMove(Board, int, Color, Color)
	 */
    
    public abstract Object[] determineMove(Board board, int colorsAmount);
    
    /**
	 * Getter for strategy.
	 * @return Type of this Player. Can be Human, Smart, or Random
	 */
    
    public String getType() {
    	return type;
    }
     
}