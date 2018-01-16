/**
 * This class for maintaining the Ringgz game.
 * @author Theo Ruys en Arend Rensink
 * @author Richard
 * @author Julien
 * @version 0.1
 */

package game;

import game.player.*;

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
    	int colors = 1;
    	boolean firstPlayer = true;
    	while (!board.gameOver()) {
    		if (firstPlayer) {
    			int[] choice = players[currentPlayer].determineBase(board);
    	        board.addHome(choice[0], choice[1]);
    	        gui.updateButton(choice[0], choice[1], true, 0, null);
    			firstPlayer = false;
    		}
    		currentPlayer = (currentPlayer + 1) % maxPlayer;
    		Object[] choice = players[currentPlayer].determineMove(board);
            board.addRing((Integer) choice[0], (Integer) choice[1], (Boolean) choice[2], (Integer) choice[3], players[currentPlayer].getColor()[colors]);
            gui.updateButton((Integer) choice[0], (Integer) choice[1], (Boolean) choice[2], (Integer) choice[3], players[currentPlayer].getColor()[colors]);
    	}
    	reset();
    }
    
}