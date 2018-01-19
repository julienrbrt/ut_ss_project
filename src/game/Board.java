package game;

import game.player.*;

// MVC (model)
public class Board {

	// Tile representing the board
	public static final int SIZE = 5;
	private Tile[][] board;
	int posCount = 0;
	boolean start = true;
	
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
			// Should never happen
			System.out.println("Can not place");
		}
	}
	
	public void addHome(int x, int y) {
		board[x][y].change(true, 1, Color.SBASE);
	}
			
	public boolean canPlace(int x, int y, boolean base, int size, Color color) {
		
		int highX;
		int lowX;
		int highY;
		int lowY;
		
		// check if numbers are correct and if color exists, just in case
		if (x > 4 || y > 4 || x < 0 || y < 0 || size < 0 || color == null) {
			return false;
		}
			
		// base placement check
		if (base) {
			// start base placement check
			if (start && x > 0 && x < 4 && y > 0 && y < 4) {
				start = false;
				return true;
			} else if (!start && board[x][y].isTileEmpty()) {
				highX = ((x + 1) > 4) ? 4 : x + 1;
				lowX = ((x - 1) < 0) ? 0 : x - 1;
				highY = ((y + 1) > 4) ? 4 : y + 1;
				lowY = ((y - 1) < 0) ? 0 : y - 1;
				
				if (board[x][y].contains(color.getColGroup()) ||
						board[lowX][y].contains(color.getColGroup()) || 
						board[highX][y].contains(color.getColGroup()) || 
						board[x][lowY].contains(color.getColGroup()) || 
						board[x][highY].contains(color.getColGroup())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			// normal ring check
			highX = ((x + 1) > 4) ? 4 : x + 1;
			lowX = ((x - 1) < 0) ? 0 : x - 1;
			highY = ((y + 1) > 4) ? 4 : y + 1;
			lowY = ((y - 1) < 0) ? 0 : y - 1;
			
			if (board[x][y].isSpotEmpty(size) && 
					(board[x][y].contains(color.getColGroup()) ||
					board[lowX][y].contains(color.getColGroup()) || 
					board[highX][y].contains(color.getColGroup()) || 
					board[x][lowY].contains(color.getColGroup()) || 
					board[x][highY].contains(color.getColGroup()))) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public int getSize() {
		return SIZE;
	}
	
//	public boolean isBoardEmpty() {
//		for (int x = 0; x < (Board.SIZE); x++) {
//			for (int y = 0; y < (Board.SIZE); y++) {
//				if (!board[x][y].isTileEmpty()) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}	
	
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
	
	/*
	 * returns an array of possible moves in the form x*100+y*10+size
	 * with size 4 being a base, for a specified color
	 */
	public int[] getPossibleMoves(Color color) {
		int[] result = new int[SIZE * SIZE * SIZE];
		int count = 0;
		int[] actResult;
		for (int size = 0; size < 4; size++) {
			actResult = getPossibleMoves(color, false, size);
			if (actResult.length > 0) {
				for (int i = 0; i < actResult.length; i++) {
					result[count] = (actResult[i] * 10) + size;
					count++;
				}
			}
		}
		
		actResult = getPossibleMoves(color, true, 0);
		if (actResult.length > 0) {
			for (int i = 0; i < actResult.length; i++) {
				result[count] = (actResult[i] * 10) + 4;
				count++;
			}
		}
		
		actResult = new int[count];
		for (int i = 0; i < count; i++) {
			actResult[i] = result[i];
		}
		
		return actResult;
	}
	
	//returns an array of possible moves in the form x*10+y, for the given size and color
	public int[] getPossibleMoves(Color color, boolean base, int size) {
		int[] result = new int[SIZE * SIZE];
		int count = 0;
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				if (canPlace(x, y, base, size, color)) {
					result[count] = (x * 10) + y;
					count++;
				}
			}
		}
		int[] actResult = new int[count];
		for (int i = 0; i < count; i++) {
			actResult[i] = result[i];
		}
		return actResult;
	}
	
	public boolean gameOver() {
		return (getPossibleMoves(Color.REDDD).length == 0 && 
				getPossibleMoves(Color.BLUEE).length == 0 && 
				getPossibleMoves(Color.GREEN).length == 0 && 
				getPossibleMoves(Color.YELLO).length == 0);
	}
		
	public String toString() {
		String output = "";
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				output += board[y][x].toString() + "\t";
			}
			output += "\n";
		}
		return output;
	}
}
