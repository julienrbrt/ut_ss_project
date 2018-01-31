package game;

import game.player.*;

/** Class generation the tiles of the board.
 * 
 * @author Richard
 * @author Julien
 */

public class Tile {
	
	//The smaller the number, the smaller the ring (0=smallest, 3=largest, 4=base), for GUI purposes
	final int rings = 4;
	//array for storing colors (4 positions for the 4 possible ring sizes)
	Color[] tile = new Color[rings];
	
	/**
	 * Constructor for Tile.
	 */
	public Tile() {
		for (int i = 0; i < rings; i++) {
			tile[i] = Color.NONEE;
		}
	}
	
	/**
	 * Places a ring or a base in the current tile.
	 * @param base Boolean that specifies whether or not the change should take up the whole tile
	 * @param size Size of the ring to be placed, if base = false
	 * @param e Color of the piece to be added
	 */
	//@ requires size >= 0 & e != null;
	//@ requires isTileEmpty() == true;
	public void change(boolean base, int size, Color e) {
		if (tile[0].getGroup() != Color.Group.BASE && tile[size] == Color.NONEE) {
			if (base && isTileEmpty()) {
				Color color = Color.RBASE;
				switch (e) {
					case REDDD:
						color = Color.RBASE;
						break;
					case BLUEE:
						color = Color.BBASE;
						break;
					case GREEN:
						color = Color.GBASE;
						break;
					case YELLO:
						color = Color.YBASE;
						break;
					case SBASE:
						color = Color.SBASE;
						break;
					default:
						System.out.println("Coloring went wrong!");
						break;
				}
				for (int i = 0; i < rings; i++) {
					tile[i] = color;
				}
			} else if (base) {
				System.out.println("Tile is not empty");
			} else {
				tile[size] = e;
			}
		} else {
			System.out.println("Spot already occupied!");
		}
	}
	
	/**
	 * Checks whether or not this tile is completely empty.
	 * @return true if this Tile is completely empty
	 */
	//@ pure;
	public boolean isTileEmpty() {
		for (int i = 0; i < rings; i++) {
			if (tile[i] != Color.NONEE) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks whether or not the specified position in this Tile is empty.
	 * <p>
	 * Other positions do not matter for this function.
	 * @param size Position to check in
	 * @return true if the spot is empty
	 */
	//@ requires size >= 0;
	//@ ensures \result == false || \result == true;
	public boolean isSpotEmpty(int size) {
		return tile[size] == Color.NONEE;
	}
	
	/**
	 * Checks whether or not a this Tile contains any piece of
	 * 	the ColGroup specified, or the starting base.
	 * @param e The specified ColGroup
	 * @return true if this Tile contains any piece of the ColGroup specified,
	 * 	or the starting base (as it is a universal color)
	 */
	//@ requires e != null;
	public boolean contains(Color.ColGroup e) {
		for (int i = 0; i < rings; i++) {
			if (tile[i].getColGroup() == e || tile[i] == Color.SBASE) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether or not this Tile contains a base of the given Color.
	 * @param c Color to check for
	 * @return true if this Tile contains a base of the given Color
	 */
	//@ requires c != null; 
	//@ ensures \result == false || \result == true;
	public boolean hasBase(Color c) {
		return tile[0].getGroup() == Color.Group.BASE && tile[0].getColGroup() == c.getColGroup();
	}
	
	/**
	 * Getter for color.
	 * @param size Position of the color to return. Corresponds to ring size
	 * @return Color stored in the specified position in this Tile
	 */
	//@ requires size >= 0;
	public Color getColor(int size) {
		return tile[size];
	}
	
	//@ ensures \result instanceof String;
	public String toString() {
		String output = "";
		for (int i = 0; i < rings; i++) {
			output += tile[i].toString() + " ";
		}
		return output;
	}
}
