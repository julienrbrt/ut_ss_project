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
	
	
	/// base counting by colors + check for 3 players
		
	public boolean canPlace(int x, int y, boolean base, int size, Color color) {
		
		int highX;
		int lowX;
		int highY;
		int lowY;
		
		// check if numbers are correct
		if (x > 5 || y > 5 || x < 0 || y < 0 || size < 0) {
			return false;
		}
			
		// base placement check
		if (base) {
			// start base placement check
			if ((x > 0 && x < 4) && isBoardEmpty()) {
				return true;
			} else if (board[x][y].isTileEmpty() && !isBoardEmpty()) {
				return true;
			}
		}
		
		// normal ring check
		highX = x + 1;
		lowX = x - 1;
		highY = y + 1;
		lowY = y - 1;
		
		if (highX > 4) {
			highX = 4;
		} else if (highY > 4) {
			highY = 4;
		} else if (lowX < 0) {
			lowX = 0;
		} else if (lowY < 0) {
			lowY = 0;
		}
		
		if ((board[x][y].contains(color.getColGroup()) || board[lowX][y].contains(color.getColGroup()) || board[highX][y].contains(color.getColGroup()) || board[x][lowY].contains(color.getColGroup()) || board[x][highY].contains(color.getColGroup())) && board[x][y].isSpotEmpty(size)) {
			return true;
		}
		
		return false;
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public boolean isBoardEmpty() {
		for (int x = 0; x < (Board.SIZE); x++) {
			for (int y = 0; y < (Board.SIZE); y++) {
				if (!board[x][y].isTileEmpty()) {
					return false;
				}
			}
		}
		return true;
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
