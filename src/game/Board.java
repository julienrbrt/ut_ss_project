package game;

import game.player.*;

/**
 * Calculates the majority of the functionality behind the game.
 * <p>
 * Contains all information on what the game board is filled with.
 * @author Richard
 * @author Julien
 */

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
	
	/**
	 * Constructor for Board.
	 * @param amount Number of players joining the current game
	 * @param player Set of Players joining the current game
	 */
	
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
		//10 for 2 players, 10 for 3 players, 5 for 4 players
    	slots = playerRings.length / playerAmount;
    	
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
	
	/**
	 * Adds a ring (or a base) to the board.
	 * @param x X coordinate of the ring to be placed
	 * @param y Y coordinate of the ring to be placed
	 * @param base Boolean that, if true,
	 * 	tells the function to place a base instead, regardless of size value
	 * @param size Size of the ring, with 0 being the smallest and 3 being the biggest
	 * @param e Color of the ring to be placed
	 */

	public void addRing(int x, int y, boolean base, int size, Color e) {
		board[x][y].change(base, size, e);
	}
	
	/**
	 * Adds the home/starting base.
	 * <p>
	 * Is only to be called once.
	 * @param x
	 * @param y
	 */
	
	public void addHome(int x, int y) {
		board[x][y].change(true, 1, Color.SBASE);
	}
	
	/**
	 * Checks whether or not all arguments are in a valid range and
	 * 	valid for placement on the board, in accordance with the game's rules.
	 * @param x X coordinate from place to check
	 * @param y Y coordinate from place to check
	 * @param base Boolean that, if true, tells the function to place a base instead,
	 * 	regardless of size value
	 * @param size Size of the ring, with 0 being the smallest and 3 being the biggest
	 * @param color Color of the ring for which to check the place
	 * @param currentPlayer ID of the player currently being checked. To be passed to hasRing() only
	 * @return Boolean that says whether or not the current ring configuration is place-able
	 * @see hasRing
	 */
	
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
	
	/**
	 * Different type of canPlace() that does not update the ring counter.
	 * <p>
	 * Used for generating a set of possible placement positions.
	 * @param x X coordinate from place to check
	 * @param y Y coordinate from place to check
	 * @param base Boolean that, if true,
	 * 	tells the function to place a base instead, regardless of size value
	 * @param size Size of the ring, with 0 being the smallest and 3 being the biggest
	 * @param color Color of the ring for which to check the place
	 * @param currentPlayer ID of the player currently being checked. To be passed to hasRing() only
	 * @return Boolean that says whether or not the current ring configuration is place-able
	 * @see canPlace
	 */
	
	public boolean canPlaceCheck(int x, int y,
			boolean base, int size, Color color, int currentPlayer) {
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
	
	/**
	 * Getter for board size.
	 * @return Size of the board in 1 dimension (5 for this board)
	 */
	
	public int getSize() {
		return SIZE;
	}
	
	/**
	 * Getter for a tile from the board that corresponds with the coordinates given.
	 * @param x X coordinate of tile to return
	 * @param y Y coordinate of tile to return
	 * @return Tile corresponding to coordinates given
	 */
		
	public Tile getTile(int x, int y) {
		return board[x][y];
	}

	/**
	 * Creates a array of all possible moves for the given color.
	 * @param color Color to find moves for
	 * @param playerNumber ID of the player currently being checked. To be passed only
	 * @return Array of int of 3 digits, where the first one means x,
	 * 	the second one means y and the third one means ring size (ring size 4 = base).
	 * 	Each int stands for a individual possible move
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
	 * Generates an array of possible moves for a particular color and particular ring size.
	 * @param color Color to find moves for
	 * @param base Boolean that, if true,
	 * 	tells the function to place a base instead, regardless of size value
	 * @param size Size of ring being checked
	 * @param playerNumber ID of the player currently being checked. To be passed only
	 * @return Array of int of 2 digits, where the first one means x and the second one means y
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
	
	/**
	 * Checks whether or not the game is over yet, based on amount of players being skipped.
	 * @param skipped Array of skipped players
	 *  Size of array corresponds to amount of players in the current game
	 * @return true when all players are being skipped
	 */
	
	public boolean gameOver(boolean[] skipped) {
		for (int i = 0; i < skipped.length; i++) {
			if (!skipped[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if the current ring size can be placed,
	 * based on amount of current ring size is left to place for the current player.
	 * @param base Boolean that specifies whether or not a base is being checked
	 * @param size Size of ring to be checked
	 * @param c Color of ring to be checked
	 * @param currentPlayer ID of player being checked
	 * @param change Boolean that indicates whether or not the values in the ring counter
	 * 	should be modified during this check
	 * @return true if current player still has a ring of the current type and color left to place
	 */
	
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
	
	/**
	 * Calculate and broadcast the name of the winner or,
	 * 	in case of a tie, broadcast that the result was a tie.
	 * @param scores Array of scores per color.
	 * 	Position 0 = Red, 1 = Blue, 2 = Green, and 3 = Yellow
	 * @return ID of winner. In case of a tie, returns 5
	 */
	
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
			int player1 = scores[players[0].getColor()[0].getIntFromColor()] +
					 scores[players[0].getColor()[1].getIntFromColor()];
			int player2 = scores[players[1].getColor()[0].getIntFromColor()] +
					 scores[players[1].getColor()[1].getIntFromColor()];
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
