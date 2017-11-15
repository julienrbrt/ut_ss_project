package Model;

public class Board {
	
	final int SIZE = 5;
	Tile[][] board;
	
	public Board() {
		board = new Tile[SIZE][SIZE];	//set up 2D matrix for the board
		for(int i = 0; i < SIZE; i++) {	//fill board with empty tiles
			for(int j = 0; j < SIZE; j++) {
				board[i][j] = new Tile();
			}
		}
	}
	
	public void addRing(int x, int y, boolean base, int size, Color e) {
		board[x][y].change(base, size, e);
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
	
	public int getSize() {
		return SIZE;
	}
}
