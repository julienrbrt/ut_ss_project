package game;

import java.util.Arrays;

import game.player.*;

// MVC (model)
public class Board {

	// Tile representing the board
	public static final int SIZE = 5;
	private Tile[][] board;
	int posCount = 0;
	boolean start = true;
	Player[] players;
	int playerAmount;
	private int[] playerRings;	//keeps track of amount of rings left per player
	private final int slots;	//keeps track of amount of slots per player in playerRings
	
	// ------------- Constructor ------------------------------------------
	public Board(int amount, Player[] player) {
		board = new Tile[SIZE][SIZE];	//set up 2D matrix for the board
		for (int i = 0; i < SIZE; i++) {	//fill board with empty tiles
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = new Tile();
			}
		}
		players = player;
		playerAmount = amount;
		
		if (playerAmount == 3) {
    		playerRings = new int[30];
    	} else {
    		playerRings = new int[20];
    	}
    	slots = playerRings.length / playerAmount;	//10 for 2 players, 10 for 3 players, 5 for 4 players
    	
    	//fill playerRings with right values, depending on amount of players
    	for (int i = 0; i < playerRings.length; i++) {
    		if (playerAmount == 3 && (i % 10 > 4)) {
    			playerRings[i] = 1;
    		} else {
    			playerRings[i] = 3;
    		}
    	}
	}
	
	// ------------- Commands ----------------------------------------------

	public void addRing(int x, int y, boolean base, int size, Color e) {
		board[x][y].change(base, size, e);
	}
	
	public void addHome(int x, int y) {
		board[x][y].change(true, 1, Color.SBASE);
	}
			
	public boolean canPlace(int x, int y, boolean base, int size, Color color, int currentPlayer) {
		
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
				
				if (board[x][y].hasBase(color) ||
						board[lowX][y].hasBase(color) ||
						board[highX][y].hasBase(color) ||
						board[x][lowY].hasBase(color) ||
						board[x][highY].hasBase(color)) {
					return false;
				}
				
				if (board[x][y].contains(color.getColGroup()) ||
						board[lowX][y].contains(color.getColGroup()) || 
						board[highX][y].contains(color.getColGroup()) || 
						board[x][lowY].contains(color.getColGroup()) || 
						board[x][highY].contains(color.getColGroup())) {
					return hasRing(base, size, color, currentPlayer, true);
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
				return hasRing(base, size, color, currentPlayer, true);
			} else {
				return false;
			}
		}
	}
	
	public boolean canPlaceCheck(int x, int y, boolean base, int size, Color color, int currentPlayer) {
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
				
				if (board[x][y].hasBase(color) ||
						board[lowX][y].hasBase(color) ||
						board[highX][y].hasBase(color) ||
						board[x][lowY].hasBase(color) ||
						board[x][highY].hasBase(color)) {
					return false;
				}
				
				if (board[x][y].contains(color.getColGroup()) ||
						board[lowX][y].contains(color.getColGroup()) || 
						board[highX][y].contains(color.getColGroup()) || 
						board[x][lowY].contains(color.getColGroup()) || 
						board[x][highY].contains(color.getColGroup())) {
					return hasRing(base, size, color, currentPlayer, false);
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
				return hasRing(base, size, color, currentPlayer, false);
			} else {
				return false;
			}
		}
	}
	
	public int getSize() {
		return SIZE;
	}
		
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
	
	/**
	 * @return array of int of 3 digits, where the first one means x, the second one means y, and the third one means ring size (ring size 4 = base)
	 */
	
	/*
	 * returns an array of possible moves in the form x*100+y*10+size
	 * with size 4 being a base, for a specified color
	 */
	public int[] getPossibleMoves(Color color, int playerNumber) {
		int[] result = new int[SIZE * SIZE * SIZE];
		int count = 0;
		int[] actResult;
		for (int size = 0; size < 4; size++) {
			actResult = getPossibleMoves(color, false, size, playerNumber);
			if (actResult.length > 0) {
				for (int i = 0; i < actResult.length; i++) {
					result[count] = (actResult[i] * 10) + size;
					count++;
				}
			}
		}
		
		actResult = getPossibleMoves(color, true, 0, playerNumber);
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
	
	/**
	 * @return array of ints of 2 digits, where the first one means x and the second one means y
	 */
	
	//returns an array of possible moves in the form x*10+y, for the given size and color
	public int[] getPossibleMoves(Color color, boolean base, int size, int playerNumber) {
		int[] result = new int[SIZE * SIZE];
		int count = 0;
		
		for (int x = 0; x <= SIZE; x++) {
			for (int y = 0; y <= SIZE; y++) {
				if (canPlaceCheck(x, y, base, size, color, playerNumber)) {
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
	
	public boolean gameOver(boolean[] skipped) {
		for (int i = 0; i < skipped.length; i++) {
			if (!skipped[i]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasRing(boolean base, int size, Color c, int currentPlayer, boolean change) {
    	boolean firstColor = players[currentPlayer].getColor()[0] == c;
    	if (base && firstColor && playerRings[slots * currentPlayer + 4] > 0) {
    		if (change) { 
    			playerRings[slots * currentPlayer + 4]--;
    		}
    		return true;
    	} else if (base && !firstColor && playerRings[slots * currentPlayer + 9] > 0) {
    		if (change) {
    			playerRings[slots * currentPlayer + 9]--;
    		}
    		return true;
    	} else if (!base && firstColor && playerRings[slots * currentPlayer + size] > 0) {
    		if (change) {
    			playerRings[slots * currentPlayer + size]--;
    		}
    		return true;
    	} else if (!base && !firstColor && playerRings[slots * currentPlayer + size + 5] > 0) {
    		if (change) {
    			playerRings[slots * currentPlayer + size + 5]--;
    		}
    		return true;
    	} else {
    		return false;
    	}
    }
	
	public int getWinner(int[] scores) {
		int winner = 0;
		int highScore = scores[0];
		boolean draw = false;
		if (playerAmount == 4) {
			for (int i = 1; i < 4; i++) {
				if (scores[i] == highScore) {
					draw = true;
				} else if (scores[i] > highScore) {
					draw = false;
					highScore = scores[i];
					winner = i;
				}
			}
		} else if (playerAmount == 3) {
			for (int i = 1; i < 3; i++) {	//Yellow's score does not matter
				if (scores[i] == highScore) {
					draw = true;
				} else if (scores[i] > highScore) {
					draw = false;
					highScore = scores[i];
					winner = i;
				}
			}
		} else {
			//add up scores of the player's colors
			int player1 = scores[players[0].getColor()[0].getIntFromColor()] + scores[players[0].getColor()[1].getIntFromColor()];
			int player2 = scores[players[1].getColor()[0].getIntFromColor()] + scores[players[1].getColor()[1].getIntFromColor()];
			if (player1 == player2) {
				draw = true;
			} else if (player1 > player2) {
				draw = false;
				winner = 0;
			} else {
				draw = false;
				winner = 1;
			}
		}
		
		if (draw) {
			return 5;	//max is 3, but clearly beyond to be sure 
		} else {
			return winner;
		}
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
