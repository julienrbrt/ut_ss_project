package network;

import java.awt.Image;

import game.*;
import game.player.*;
import gui.*;
import tools.Tools;

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

	public ClientGame(String name, int playerNum, int amountPlayer, HumanUI gui) {
		this.name = name;
		this.playerNum = playerNum;
		this.amountPlayer = amountPlayer;
		this.gui = gui;
	}
	
	public void initiate() {
		
		// Generate player - Color fixed
		if (name.equals("RANDOM")) {
			if (amountPlayer > 2) {
				players[0] = new ComputerPlayer(new RandomStrategy(playerNum),
						getColor(playerNum, 1), getColor(playerNum, 2), playerNum);
			} else {
				players[0] = new ComputerPlayer(new RandomStrategy(playerNum),
						getColor(playerNum, 1), playerNum);
			}
		} else if (name.equals("SMART")) {
			if (amountPlayer > 2) {
				players[0] = new ComputerPlayer(new SmartStrategy(playerNum),
						getColor(playerNum, 1), getColor(playerNum, 2), playerNum);
			} else {
				players[0] = new ComputerPlayer(new SmartStrategy(playerNum),
						getColor(playerNum, 1), playerNum);
			}
		} else {
			if (amountPlayer > 2) {
				players[0] = new HumanPlayerUI(name, getColor(playerNum, 1), getColor(playerNum, 2), playerNum, gui);
			} else {
				players[0] = new HumanPlayerUI(name, getColor(playerNum, 1), playerNum, gui);
			}
		}
		    	
    	if (amountPlayer < 4) {
    		colorAmount = 2;
    	}
		
		// Create board
		board = new Board(amountPlayer, players);

	}
	
	public Object[] makeMove() {
	 	// Reset previous button images
		emptyButton = new ColorUI(null, false, 0).getColorUI();
		merged = emptyButton;
		buttonImage = emptyButton;
		
		if (playerNum == 0) {
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
			if (size == 5) {
				base = true;
			   	board.addRing(x, y, base, 0, getColor(playerNum, color));
			} else {
			   	board.addRing(x, y, base, 0, getColor(playerNum, color));

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
	
	public Color getColor(int playerPosition, int colorChoosen) {
		if (amountPlayer > 2) {
			if (colorChoosen > 1) {
				return Color.YELLO;
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
		} else {
			if (colorChoosen > 1) {
				switch (playerPosition) {
					case 1:
						return Color.REDDD;
					case 2:
						return Color.YELLO;
				}
			} else {
				switch (playerPosition) {
					case 1:
						return Color.BLUEE;
					case 2:
						return Color.GREEN;
				}
			}
		}
		return Color.NONEE;
	}
	
    public void run() {
    	Thread waitGUI = new Thread(gui);
    	waitGUI.start();
    	initiate();
    	try {
    		waitGUI.join();
    	} catch (InterruptedException ie) {
    		
    	}
    }
}
