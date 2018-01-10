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
    public int maxPlayer = 4;
	
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
    public Game(Player p0, Player p1, Player p2, Player p3) {
    	board = new Board();
    	    	
        if (p2 == null) {
        	maxPlayer--;
        }
        
        if (p3 == null) {
        	maxPlayer--;
        }
        
        players = new Player[maxPlayer];
        players[0] = p0;
        players[1] = p1;
        
        if (maxPlayer >=  3) {
        	players[2] = p2;
        }
        
        if (maxPlayer >= 4) {
        	players[3] = p3;
        }
        
    	currentPlayer = 0;
    }

    // -- Commands ---------------------------------------------------

    /**
     * Starts the Ringgz.
     */
    public void start() {
    	reset();
        play();
    }
    
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
    private void play() {
    	int colors = 1;
    	int firstPlayer = 0;
    	while (!board.gameOver()) {
    		update();
    		if (firstPlayer == 0) {
    			players[currentPlayer].makeBaseMove(board);
        		update();
    			firstPlayer++;
    		}
    		currentPlayer = (currentPlayer + 1) % maxPlayer;
    		players[currentPlayer].makeMove(board, colors);
    	}
    }
    
    /**
     * Prints the game situation.
     */
    private void update() {
        System.out.println("\nSituation: \n\n" + board.toString() + "\n");
    }
}