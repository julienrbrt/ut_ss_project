package game;

import game.player.*;

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
		
		// check if numbers are correct
		if (x > 4 || y > 4) {
			return false;
		} else {
			
			// starting base placement check
			if (base) {
				if ((x < 1 || x > 4) && (y < 1 || y > 4)) {
					return false;
				} else {
					if (board[x][y].isTileEmpty()) {
						return true;
					}
				}
			}
			
			// normal ring check
			
			// TO-FIX
			if (board[x][y].isTileEmpty()) {
				return true;
			}
				
		}
		return false;
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
	
	public boolean gameOver() {
		return false;
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				output += board[j][i].toString() + "\t";
			}
			output += "\n";
		}
		return output;
	}
}
