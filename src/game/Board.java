package game;

//import game.*;
import game.player.*;
//import gui.Tools;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;

// MVC (model)
public class Board {

	// Tile representing the board
	public static final int SIZE = 5;
	private Tile[][] board;
	int posCount = 0;
	
	// ------------- Constructor ------------------------------------------
	public Board() {
		board = new Tile[SIZE][SIZE];	//set up 2D matrix for the board
		for (int i = 0; i < SIZE; i++) {	//fill board with empty tiles
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = new Tile();
			}
		}
	}
	
	// ------------- Commands ----------------------------------------------
	/**
	 * Sets all the fields of the instance to empty.
	 */
	
	public void addRing(int x, int y, boolean base, int size, Color e) {
		if (canPlace(x, y, base, size, e)) {
			board[x][y].change(base, size, e);
		} else {
			System.out.println("Can not place");
		}
	}
	
	public void addHome(int x, int y) {
		board[x][y].change(true, 1, Color.SBASE);
	}
	
	public boolean canPlace(int x, int y, boolean base, int size, Color e) {
		int[] xs = new int[25];
		int[] ys = new int[25];
		int lowX;
		int highX;
		int lowY;
		int highY;
		int posCount = 0;
		
		for (int i = 0; i < SIZE; i++) {	//fill xs and ys with positions the ring/base can be placed in
			for (int j = 0; j < SIZE; j++) {
				lowX = (i-1) < 0 ? 0 : i-1;
				highX = (i+1)>SIZE-1 ? SIZE-1 : i+1;
				lowY = (j-1)<0 ? 0 : j-1;
				highY = (j+1)>SIZE-1 ? SIZE-1 : j+1;
				if ((board[i][j].contains(e.getColGroup())||board[lowX][j].contains(e.getColGroup())||board[highX][j].contains(e.getColGroup())||board[i][lowY].contains(e.getColGroup())||board[i][highY].contains(e.getColGroup()))&&board[i][j].isSpotEmpty(size)) {
					if (base&&!board[i][j].isTileEmpty()) {
						//don't add
					} else {
						xs[posCount] = i;
						ys[posCount] = j;
						posCount++;
					}
				}
			}
		}
		
		for (int i = 0; i < posCount; i++) {
			if ((xs[i] == x) && (ys[i] == y)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public boolean isWinner(Color color) {
		return false;
	}
	
	public boolean hasWinner() {
		return false;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public boolean gameOver() {
		return this.isFull() || this.hasWinner();
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				output += board[j][i].toString() + "\t";
			}
			output += "\n";
		}
		return output;
	}
	
	// TO DELETE
	public static void main(String[] args) {
		if(args.length >= Game.MAXPLAYER-2) {
			Player p0, p1, p2, p3;
			
		    if (args[0].equals("-D")) {
		    	if(args.length == 2) {
		    		p0 = new ComputerPlayer(Color.REDDD, Color.YELLO);
		    	} else {
				    p0 = new ComputerPlayer(Color.REDDD, null);
		    	}
		    } else if (args[0].equals("-R")) {
		    	if(args.length == 2) {
		    		p0 = new ComputerPlayer(new RandomStrategy(), Color.REDDD, Color.YELLO);
		    	} else {
		    		p0 = new ComputerPlayer(new RandomStrategy(), Color.REDDD, null);
		    	}
		    } else {
		    	if(args.length == 2) {
				    p0 = new HumanPlayer(args[0], Color.REDDD, Color.YELLO);
		    	} else {
				    p0 = new HumanPlayer(args[0], Color.REDDD, null);
		    	}
		    }
			  
		    if (args[1].equals("-D")) {
		    	if(args.length == 2) {
		    		p1 = new ComputerPlayer(Color.GREEN, Color.BLUEE);
		    	} else {
			    	p1 = new ComputerPlayer(Color.GREEN, null);
		    	}
		    } else if (args[1].equals("-R")) {
		    	if(args.length == 2) {
		    		p1 = new ComputerPlayer(new RandomStrategy(), Color.GREEN, Color.BLUEE);
		    	} else {
		    		p1 = new ComputerPlayer(new RandomStrategy(), Color.GREEN, null);
		    	}
		    } else {
		    	if(args.length == 2) {
				    p1 = new HumanPlayer(args[0], Color.GREEN, Color.BLUEE);
		    	} else {
				    p1 = new HumanPlayer(args[0], Color.GREEN, null);
		    	}
		    }
		    
		    if(args.length >= Game.MAXPLAYER-1) {
		    	if (args[2].equals("-D")) {
			    	p2 = new ComputerPlayer(Color.YELLO, null);
			    } else if (args[2].equals("-R")) {
		    		p2 = new ComputerPlayer(new RandomStrategy(), Color.YELLO, null);
			    } else {
			    	p2 = new HumanPlayer(args[2], Color.YELLO, null);
			    }
		    } else {
		    	p2 = null;
		    }
		    
		    if(args.length == Game.MAXPLAYER) {
			    if (args[3].equals("-D")) {
			    	p3 = new ComputerPlayer(Color.BLUEE, null);
			    } else if (args[3].equals("-R")) {
		    		p3 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE, null);
			    } else {
			    	p3 = new HumanPlayer(args[3], Color.BLUEE, null);
			    }
		    } else {
		    	p3 = null;
		    }
			  
		    Game game = new Game(p0, p1, p2, p3);
		    game.start();
		} else {
			System.out.println("You have to play this game with a minimum of two players. Try again.");
		}
	}
	
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
}
