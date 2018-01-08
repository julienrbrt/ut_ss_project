package game.player;

import game.*;

/**
 *  Abstract class for keeping a player in the Ringzz game.
 * @author Theo Ruys en Arend Rensink
 * @author Richard
 * @author Julien
 * @version 0.1
 */


public abstract class Player {

    // -- Instance variables -----------------------------------------

    private String name;
    private Color firstColor;
    private Color secondColor;

    // -- Constructors -----------------------------------------------

    /*@
       requires name != null;
       requires firstColor == Color.GREEN || color== Color.PURPL || color== Color.REDDD || color== Color.YELLOW;
       requires secondColor == Color.GREEN || color== Color.PURPL || color== Color.REDDD || color== Color.YELLOW || null;;
       ensures this.getName() == name;
//       ensures this.getColor() == firstColor || secondColor;
     */
    /**
     * Creates a new Player object.
     * 
     */
    public Player(String name, Color firstColor, Color secondColor) {
        this.name = name;
        this.firstColor = firstColor;
        if (secondColor != null) {
        	this.secondColor = secondColor;
        }
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    /*@ pure */ public String getName() {
        return name;
    }

    /**
     * Returns the color of the player.
     */
    /*@ pure */ public Color getColor(int colors) {
        if (colors == 1) {
        	return firstColor;
        } else if (colors == 2) {
        	return secondColor;
        } else {
        	return null;
        }
    }

    /*@
       requires board != null & !board.isFull();
     */
    /**
     * Determines the field for the next move.
     * 
     * @param board
     *            the current game board
     * @return the player's choice
     */
    public abstract int[] determineBase(Board board);
    
    public abstract Object[] determineMove(Board board);
    

    // -- Commands ---------------------------------------------------

    /*@
       requires board != null & !board.isFull();
     */
    /**
     * Makes a move on the board. <br>
     * 
     * @param board the current board
     */
    public void makeMove(Board board, int colors) {
        Object[] choice = determineMove(board);
        board.addRing((Integer) choice[0], (Integer) choice[1], (Boolean) choice[2], (Integer) choice[3], getColor(colors));
    }
    
    /*@
    requires board != null & !board.isFull();
     */
	 /**
	  * Makes a base move on the board. <br>
	  * 
	  * @param board the current board
	  */
    public void makeFirstMove(Board board) {
        int[] choice = determineBase(board);
        board.addHome(choice[0], choice[1]);
    }
    
}