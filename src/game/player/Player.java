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
     */
    /**
     * Creates a new Player object.
     * 
     */
    public Player(String name, Color firstColor) {
        this.name = name;
        this.firstColor = firstColor;
    }
    
    public Player(String name, Color firstColor, Color secondColor) {
        this.name = name;
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    // -- Queries ----------------------------------------------------

    /**
     * Returns the name of the player.
     */
    /*@ pure */
    public String getName() {
        return name;
    }

    /**
     * Returns the color of the player.
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
     * Determines the field for the next move.
     * 
     * @param board the current game board
     * @return the player's choice
     */
    public abstract int[] determineBase(Board board);
    
    public abstract Object[] determineMove(Board board, int colorsAmount);    
     
}