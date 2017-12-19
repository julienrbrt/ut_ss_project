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
    private Color color;

    // -- Constructors -----------------------------------------------

    /*@
       requires name != null;
       requires color == Color.GREEN || color== Color.PURPL || color== Color.REDDD || color== Color.YELLOW;
       ensures this.getName() == name;
       ensures this.getcolor() == color;
     */
    /**
     * Creates a new Player object.
     * 
     */
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
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
    /*@ pure */ public Color getColor() {
        return color;
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
    public abstract int[] determineMove(Board board);

    // -- Commands ---------------------------------------------------

    /*@
       requires board != null & !board.isFull();
     */
    /**
     * Makes a move on the board. <br>
     * 
     * @param board the current board
     */
    public void makeMove(Board board) {
        int[] choice = determineMove(board);
        board.addRing(choice[0], choice[1], getColor());
    }

}
