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
    public static final int MAXPLAYER = 4;
	
	/*@
    	private invariant board != null;
	*/
	/**
	 * The board.
	 */
    private Board board;
    
	/*@
		private invariant players.length == MAXPLAYER;
		private invariant (\forall int i; 0 <= i && i < MAXPLAYER; players[i] != null); 
	*/
	/**
	* The four players of the game.
	*/
    private Player[] players;
    
    /*@
    	private invariant 0 <= currentPlayer  && currentPlayer < MAXPLAYER;
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
    * @param p1 he second player
    */
    public Game(Player p0, Player p1, Player p2, Player p3) {
    	board = new Board();
        players = new Player[MAXPLAYER];
        players[0] = p0;
        players[1] = p1;
        
        if (p2 != null) {
        	players[2] = p2;
        }
        
        if (p3 != null) {
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
    // FIX COLOR HANDLING
    private void play() {
    	int colors = 1;
    	while (!board.gameOver()) {
    		update();    		
    		currentPlayer = (currentPlayer + 1) % MAXPLAYER;
    		if (players[2] == null || players[3] == null) {
    			currentPlayer = currentPlayer + 1;
    			colors = 1;
    		} else if (players[2] == null && players[4] == null) {
    			currentPlayer = currentPlayer + 2;
    			colors = 1;
    		}
    		players[currentPlayer].makeFirstMove(board);
    		players[currentPlayer].makeMove(board, colors);
    	}
    	printResult();
    }
    
    /**
     * Prints the game situation.
     */
    private void update() {
        System.out.println("\nSituation: \n\n" + board.toString() + "\n");
    }
    
    /*@
    	requires this.board.gameOver();
    */
    /**
     * Prints the result of the last game.
    */
    
    
    // FIX COLOR HANDLING
    
    private void printResult() {
    	update();
    	Player winner;

    	if (board.isWinner(players[0].getColor(1))) {
    		winner = players[0];
    	} else if (board.isWinner(players[1].getColor(1))) {
    		winner = players[1];
    	} else if (board.isWinner(players[2].getColor(1))) {
    		winner = players[2];
    	} else if (board.isWinner(players[3].getColor(1))) {
    		winner = players[3];
    	} else {
    		winner = null;
    	}
        System.out.println("Player " + winner.getName()
        	+ " (" + winner.getColor(1).toString() + ") has won!");
    }
    
    // ------------- Queries (getters) -------------------------------------
    
    public int getPlayers() {
    	return MAXPLAYER;
    }
}