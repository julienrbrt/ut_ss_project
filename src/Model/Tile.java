package Model;

public class Tile {
	
	final int RINGS = 4;
	Color[] tile = new Color[RINGS];	//array for storing colors (4 positions for the 4 possible ring sizes)
	
	public Tile() {
		for(int i = 0; i < RINGS; i++) {
			tile[i] = Color.NONEE;
		}
	}
	
	public void change(boolean base, int size, Color e) {
		if(tile[0].getGroup() != Color.Group.BASE && tile[size] == Color.NONEE) {
			if(base) {
				switch(e) {
				case REDDD:
					tile[0] = Color.RBASE;
					break;
				case PURPL:
					tile[0] = Color.PBASE;
					break;
				case GREEN:
					tile[0] = Color.GBASE;
					break;
				case YELLO:
					tile[0] = Color.YBASE;
					break;
				default:
					System.out.println("Coloring went wrong!");
					break;
				}
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

}
