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
import gui.HumanUI;
import tools.Tools;

/**
 * Controls gameplay.
 * @author Richard
 * @author Julien
 */

public class Game implements Runnable {

	// -- Instance variables -----------------------------------------
    
	/*@
    	private invariant board != null;
	*/
	
	/**
	 * The board.
	 */
    private Board board;
    
	/**
	* The (up to) four players of the game.
	*/
    private Player[] players;
    
	/**
	 * Index of the current player.
	*/
    private int currentPlayer;

  	/**
  	 * Game GUI Handling.
  	*/
	HumanUI gui;
	Image buttonImage;
	Image emptyButton;
	Image merged;
	ColorUI colorUI;
	
	/**
	 * Player skipping handling / Game Over.
	 */
	private boolean[] gotSkipped;
	
    // -- Constructors -----------------------------------------------

   /*@
   	requires players[0] != null;
    requires players[1] != null;
   */
	
	/**
	 * Constructor for Game.
	 * @param players Array of players joining the current game
	 * @param gui UI to display everything regarding the game on
	 */
	
    public Game(Player[] players, HumanUI gui) {
        this.players = players;
        board = new Board(players.length, players);
    	currentPlayer = 0;
    	
    	gotSkipped = new boolean[players.length];
    	// Nobody skipped at first
    	for (int i = 0; i < gotSkipped.length; i++) {
    		gotSkipped[i] = false;
    	}
    	
    	this.gui = gui;
    }

    // -- Commands ---------------------------------------------------

    /*@
    requires board != null && !board.gameOver();
     */
    
    /**
     * Starts a game.
     * <p>
     * First the (still empty) board is shown. Then the game is played until it
     * is over. Players can make a move one after the other. After each move,
     * the changed game situation is printed.
     */
    
    public void play() {
     	
    	int colorAmount = 1;
    	
    	if (players.length < 4) {
    		colorAmount = 2;
    	}
    	
    	boolean firstPlayer = true;
    	while (!board.gameOver(gotSkipped)) {
    		
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
				// System.out.println(board.toString());
    			firstPlayer = false;
    		}
    		currentPlayer = (currentPlayer + 1) % players.length;
    		
           	// Reset previous button images
			merged = emptyButton;
			buttonImage = emptyButton;
    		
    		// Manage skipped players
    		if (!gotSkipped[currentPlayer]) {
    			// show which turn it is
	    		JOptionPane.showMessageDialog(null, players[currentPlayer].getName() + " turn");
	    		Object[] choice = new Object[0];
	    		int checkNo = 1;
	    		if (players[currentPlayer].getType() == "Human") {
	    			checkNo = (players[currentPlayer].getColor()[1] != null) ?
	    					board.getPossibleMoves(players[currentPlayer].getColor()[0],
	    							currentPlayer).length +
	    					board.getPossibleMoves(players[currentPlayer].getColor()[1],
	    							currentPlayer).length : 
	    					board.getPossibleMoves(players[currentPlayer].getColor()[0],
	    							currentPlayer).length;
	    		} else {
	    			try {
	    				// Simulate thinking time
	    				Thread.sleep(2000);
	    			} catch (InterruptedException e) {
	    				// Nothing to do
	    			}
	    		}
	    		
	    		if (checkNo != 0) {
	    			choice = players[currentPlayer].determineMove(board, colorAmount);
	    		}
	    		
	    		// Skip player if no possible choices
	    		if (choice.length != 0) {
		    		board.addRing((Integer) choice[0],
		            		(Integer) choice[1],
		            		(Boolean) choice[2],
		            		(Integer) choice[3],
		            		(Color) choice[4]);
		           	for (int i = 0; i < 4; i++) {
		           		Color color = board.getTile((Integer) choice[0],
		           				(Integer) choice[1]).getColor(i); 		
		           		if (color != Color.NONEE && color != null) {
		           			colorUI = new ColorUI(color, (Boolean) choice[2], i);
		           			buttonImage = colorUI.getColorUI();
		           		}
		           		merged = Tools.mergeImg(merged, buttonImage);		     
		           	}
					gui.updateButton((Integer) choice[0], (Integer) choice[1], merged);
					// System.out.println(board.toString());
	    		} else {
	    			if (!gotSkipped[currentPlayer]) {
	    				JOptionPane.showMessageDialog(null,
	    						players[currentPlayer].getName() + " cannot move again.");
	    			}
	    			System.out.println(players[currentPlayer].getName() + " has been skipped.");
	    			gotSkipped[currentPlayer] = true;
	    		}
    		}
        }
    	// add info about winner
    	WinConditions win = new WinConditions(board);
    	win.calculate();
    	int[] scores = win.getScores();
    	int winner = board.getWinner(scores);
    	if (winner < 4) {
    		JOptionPane.showMessageDialog(null, "The winner is " + players[winner].getName());
    	} else {
    		JOptionPane.showMessageDialog(null, "It is a tie");
    	}
    	
    }
    
    /**
     * Starts a new Game thread, if called through start().
     */
    
    public void run() {
    	Thread waitGUI = new Thread(gui);
    	waitGUI.start();
    	try {
    		waitGUI.join();
    	} catch (InterruptedException ie) {
    		
    	}
    	play();
    }
}