package network;

import java.awt.Image;

import game.*;
import game.player.*;
import gui.*;
import tools.Tools;

/**
 * Class managing the game for a client connected to a server. 
 * @author Richard
 * @author Julien
 *
 */
public class ClientGame implements Runnable {

	private Board board;
	private HumanUI gui;

	private Player[] players = new Player[1];
	
	private String name;
	private int playerNum;
	private int amountPlayer;
	private Image buttonImage;
	private Image emptyButton;
	private Image merged;
	private ColorUI colorUI;
	private int colorAmount = 1;
	private boolean start = true;

	/**
	 * Constructor of the ClientGame.
	 * @param name, name of the user
	 * @param playerNum, it's position in the game
	 * @param amountPlayer, the total amount of players in the game
	 * @param gui, the HumanUI to interact with
	 */
	public ClientGame(String name, int playerNum, int amountPlayer, HumanUI gui) {
		this.name = name;
		this.playerNum = playerNum;
		this.amountPlayer = amountPlayer;
		this.gui = gui;
	}
	
	/**
	 * Transform the user as a Player.
	 */
	public void initiate() {
		
		// Generate player - Color fixed
		if (name.equals("RANDOM")) {
			if (amountPlayer < 4) {
				players[0] = new ComputerPlayer(new RandomStrategy(playerNum),
						getColor(playerNum, 1, amountPlayer),
						getColor(playerNum, 2, amountPlayer), 0);
			} else {
				players[0] = new ComputerPlayer(new RandomStrategy(playerNum),
						getColor(playerNum, 0, amountPlayer), 0);
			}
		} else if (name.equals("SMART")) {
			if (amountPlayer < 4) {
				players[0] = new ComputerPlayer(new SmartStrategy(playerNum),
						getColor(playerNum, 1, amountPlayer),
						getColor(playerNum, 2, amountPlayer), 0);
			} else {
				players[0] = new ComputerPlayer(new SmartStrategy(playerNum),
						getColor(playerNum, 0, amountPlayer), 0);
			}
		} else {
			if (amountPlayer < 4) {
				players[0] = new HumanPlayerUI(name,
						getColor(playerNum, 1, amountPlayer),
						getColor(playerNum, 2, amountPlayer), 0, gui);
			} else {
				players[0] = new HumanPlayerUI(name,
						getColor(playerNum, 0, amountPlayer), 0, gui);
			}
		}
		    	
    	if (amountPlayer < 4) {
    		colorAmount = 2;
    	}
		
		// Create board
		board = new Board(amountPlayer, players);

	}
	/**
	 * Permit to the user to make a move.
	 * @return the position of the ring chosen.
	 */
	public Object[] makeMove() {
	 	// Reset previous button images
		emptyButton = new ColorUI(null, false, 0).getColorUI();
		merged = emptyButton;
		buttonImage = emptyButton;
		
		if (playerNum == 1 && start) {
    		int[] choice = players[0].determineBase(board);
    	    board.addHome(choice[0], choice[1]);
    	    // Empty Board Image
    	    colorUI = new ColorUI(null, false, 0);
        	emptyButton = colorUI.getColorUI();
        	// SBase Button Image
        	colorUI = new ColorUI(null, true, 0);
           	buttonImage = colorUI.getColorUI();
        	merged = Tools.mergeImg(emptyButton, buttonImage);
        	gui.updateButton(choice[0], choice[1], merged);
        	Integer[] con = {(Integer) choice[0], (Integer) choice[1]};
        	Object[] conv = (Object[]) con;
        	start = false;
    		return conv;
		} else {			
			Object[] choice = players[0].determineMove(board, colorAmount);
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
	    	return choice;
		}	
	}
	/**
	 * Update the board with a move received from the server.
	 * @param x, the x position
	 * @param y, the y position of the rings
	 * @param size, it's size
	 * @param color, it's color
	 * @param playerPosition, the position of the player having made that move.
	 */
	public void setMove(int x, int y, int size, int color, int playerPosition) {
				
	 	// Reset previous button images
		emptyButton = new ColorUI(null, false, 0).getColorUI();
		merged = emptyButton;
		buttonImage = emptyButton;
		
		if (size == 0 && color == 0) {
			board.addHome(x, y);
    	    // Empty Board Image
    	    colorUI = new ColorUI(null, false, 0);
        	emptyButton = colorUI.getColorUI();
        	// SBase Button Image
        	colorUI = new ColorUI(null, true, 0);
           	buttonImage = colorUI.getColorUI();
        	merged = Tools.mergeImg(emptyButton, buttonImage);
        	gui.updateButton(x, y, merged);
		} else {
			boolean base = false;
			if (size == 0) {
				base = true;
			   	board.addRing(x, y, base, 0, getColor(playerPosition, color, amountPlayer));
			} else {
			   	board.addRing(x, y, base, size - 1, getColor(playerPosition, color, amountPlayer));
			}
		    for (int i = 0; i < 4; i++) {
		    	Color colored = board.getTile((Integer) x,
		    			(Integer) y).getColor(i); 		
		        if (colored != Color.NONEE && colored != null) {
		        	colorUI = new ColorUI(colored, base, i);
		      		buttonImage = colorUI.getColorUI();
		        }
		        merged = Tools.mergeImg(merged, buttonImage);		     
		    }
		    
		    gui.updateButton((Integer) x, (Integer) y, merged);
			
		}
	}
	/**
	 * Setup the color of the opponents (Client-Side).
	 * @param playerPosition, the player position.
	 * @param colorChoosen, the amount of color of the player
	 * @return the color generated.
	 */
	public Color getColor(int playerPosition, int colorChoosen, int amountPlay) {
		if (amountPlay == 3) {
			if (colorChoosen == 2) {
				return Color.YELLO;
			} else {
				switch (playerPosition) {
					case 1:
						return Color.BLUEE;
					case 2:
						return Color.GREEN;
					case 3:
						return Color.REDDD;
				}
			}
		} else if (amountPlay == 2) {
			System.out.println(playerPosition + " " + colorChoosen);
			if (colorChoosen == 2) {
				switch (playerPosition) {
					case 1:
						return Color.BLUEE;
					case 2:
						return Color.YELLO;
				}
			} else {
				switch (playerPosition) {
					case 1:
						return Color.REDDD;
					case 2:
						return Color.GREEN;
				}
			} 
		} else {
			switch (playerPosition) {
				case 1:
					return Color.BLUEE;
				case 2:
					return Color.GREEN;
				case 3:
					return Color.REDDD;
				case 4:
					return Color.YELLO;
			}
		}
		return Color.NONEE;
	}
	
    public void run() {
    	Thread waitGUI = new Thread(gui);
    	waitGUI.start();
    	try {
    		waitGUI.join();
    	} catch (InterruptedException ie) {
    		
    	}
    }
}
