package game;

import game.player.*;

public class Tile {
	
	//The smaller the number, the smaller the ring (0=smallest, 3=largest, 4=base), for GUI purposes
	
	final int RINGS = 4;
	Color[] tile = new Color[RINGS];	//array for storing colors (4 positions for the 4 possible ring sizes)
	
	public Tile() {
		for(int i = 0; i < RINGS; i++) {
			tile[i] = Color.NONEE;
		}
	}
	
	public void change(boolean base, int size, Color e) {
		if(tile[0].getGroup() != Color.Group.BASE && tile[size] == Color.NONEE) {
			if(base && isTileEmpty()) {
				Color color = Color.RBASE;
				switch(e) {
				case REDDD:
					color = Color.RBASE;
					break;
				case PURPL:
					color = Color.PBASE;
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
				for(int i = 0; i < RINGS; i++) {
					tile[i] = color;
				}
			}
			else if(base) {
				System.out.println("Tile is not empty");
			}
			else {
				tile[size] = e;
			}
		}
		else {
			System.out.println("Spot already occupied!");
		}
	}
	
	public String toString() {
		String output = "";
		for(int i = 0; i < RINGS; i++) {
			output += tile[i].toString() + " ";
		}
		return output;
	}
	
	public Color getColor(int size) {
		return tile[size];
	}
	
	public boolean isTileEmpty() {
		for(int i = 0; i < RINGS; i++) {
			if(tile[i] != Color.NONEE) {
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(Color.ColGroup e) {
		for(int i = 0; i < RINGS; i++) {
			if(tile[i].getColGroup() == e||tile[i] == Color.SBASE) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSpotEmpty(int size) {
		return (tile[size] == Color.NONEE);
	}

}
