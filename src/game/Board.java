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
		
		//fill xs and ys with positions the ring/base can be placed in
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				lowX = (i - 1) < 0 ? 0 : i - 1;
				highX = (i + 1) > SIZE - 1 ? SIZE - 1 : i + 1;
				lowY = (j - 1) < 0 ? 0 : j - 1;
				highY = (j + 1) > SIZE - 1 ? SIZE - 1 : j + 1;
				if ((board[i][j].contains(e.getColGroup())||board[lowX][j].contains(e.getColGroup())||board[highX][j].contains(e.getColGroup())||board[i][lowY].contains(e.getColGroup())||board[i][highY].contains(e.getColGroup()))&&board[i][j].isSpotEmpty(size)) {
					if (!(base && !board[i][j].isTileEmpty())) {
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
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				output += board[j][i].toString() + "\t";
			}
			output += "\n";
		}
		return output;
	}
	
	// ------------- Queries -----------------------------------------------
	
	public int getSize() {
		return SIZE;
	}
	
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
}
