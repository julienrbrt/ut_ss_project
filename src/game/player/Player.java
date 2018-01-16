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
    
	// GUI Handling
	HumanUI gui = new HumanUI();

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
    
    public abstract Object[] determineMove(Board board);
    

    // -- Commands ---------------------------------------------------

    /*@
    requires board != null & !board.gameOver();
     */
	 /**
	  * Makes a base move on the board. <br>
	  * 
	  * @param board the current board
	  */
    public void makeBaseMove(Board board) {
        int[] choice = determineBase(board);
        board.addHome(choice[0], choice[1]);
        gui.updateButton(choice[0], choice[1], true, 0, null);
    }
    
    /*@
       requires board != null & !board.gameOver();
     */
    /**
     * Makes a move on the board. <br>
     * 
     * @param board the current board
     */
    public void makeMove(Board board, int colors) {
        Object[] choice = determineMove(board);
        board.addRing((Integer) choice[0], (Integer) choice[1], (Boolean) choice[2], (Integer) choice[3], getColor()[colors]);
        gui.updateButton((Integer) choice[0], (Integer) choice[1], (Boolean) choice[2], (Integer) choice[3], getColor()[colors]);
    }

}