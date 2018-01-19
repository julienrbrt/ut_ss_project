/**
 * This class for maintaining the Ringgz game.
 * @author Theo Ruys en Arend Rensink
 * @author Richard
 * @author Julien
 * @version 0.1
 */

package game;

import java.awt.Image;

import javax.swing.JOptionPane;

import game.player.*;
import tools.Tools;

public class Game {

	// -- Instance variables -----------------------------------------
    
	// Four players maximum by design
    private static int maxPlayer = 4;
	
	/*@
    	private invariant board != null;
	*/
	/**
	 * The board.
	 */
    private Board board;
    
	/*@
		requires players.length <= maxPlayer;
		requires (\forall int i; 0 <= i && i < maxPlayer; players[i] != null); 
	*/
	/**
	* The four players of the game.
	*/
    private Player[] players;
    
    /*@
    	requires 0 <= currentPlayer  && currentPlayer <= maxPlayer;
    */
	/**
	 * Index of the current player.
	*/
    private int currentPlayer;

  	/**
  	 * Game GUI Handling.
  	*/
	HumanUI gui = new HumanUI();
	Image buttonImage;
	Image emptyButton;
	Image merged;
	ColorUI colorUI;
	
    // -- Constructors -----------------------------------------------

   /*@
   	requires p0 != null;
    requires p1 != null;
   */
   /**
    * Creates a new Game object.
    * 
    * @param p0 the first player
    * @param p1 the second player
    * @param p2 the third possible player
    * @param p3 he fourth possible player
    */    
    public Game(Player[] players) {
    	board = new Board();
    	maxPlayer = players.length;
        this.players = players;
    	currentPlayer = 0;
    }

    // -- Commands ---------------------------------------------------
    
    /**
     * Resets the game.
     * The board is emptied and player[0] becomes the current player.
     */
    private void reset() {
        currentPlayer = 0;
        board = new Board();
    }
    
    /**
     * Plays the Ringgz game.
     * First the (still empty) board is shown. Then the game is played until it
     * is over. Players can make a move one after the other. After each move,
     * the changed game situation is printed.
     */
    /*@
    requires board != null & !board.gameOver();
     */
    public void play() {
    	/// TO FIX COLOR HANDLING
    	int colors = 1;
    	boolean firstPlayer = true;
    	while (!board.gameOver()) {
    		if (firstPlayer) {
    			int[] choice = players[currentPlayer].determineBase(board);
    	        board.addHome(choice[0], choice[1]);
    	        // Empty Board Image
    	        colorUI = new ColorUI(null, false, 0);
        		emptyButton = colorUI.getColorUI();
        		// SBase Button Image
        		colorUI = new ColorUI(null, true, 0);
           		buttonImage = colorUI.getColorUI();
        		merged = Tools.mergeImg(emptyButton, buttonImage);
    	        gui.updateButton(choice[0], choice[1], merged);
    			firstPlayer = false;
    		}
    		currentPlayer = (currentPlayer + 1) % maxPlayer;
    		JOptionPane.showMessageDialog(null, "Player " + (currentPlayer + 1) + " turn");
    		Object[] choice = players[currentPlayer].determineMove(board);
    		
    		board.addRing((Integer) choice[0],
            		(Integer) choice[1],
            		(Boolean) choice[2],
            		(Integer) choice[3],
            		players[currentPlayer].getColor()[colors]);
    		
           	// Get previous button images
			merged = emptyButton;
           	for (int i = 3; i >= 0; i--) {
           		Color color = board.getTile((Integer) choice[0], (Integer) choice[1]).getColor(i);
           		            		
           		if (color != Color.NONEE && color != null) {
           			colorUI = new ColorUI(color, (Boolean) choice[2], i);
           			buttonImage = colorUI.getColorUI();
           		}
            		
           		merged = Tools.mergeImg(merged, buttonImage);
           		
           	}
			gui.updateButton((Integer) choice[0], (Integer) choice[1], merged);
        }
    	reset();
    }
    
}