package game.player;

/**
 * Represents the color in the Ringgz game.
 * There is four colors available and one empty field, Color.REDDD (Red), Color.BLUEE (Blue),
 * Color.GREEN (Green), Color.YELLO (Yellow) and Color.NONEE (Empty) 
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public enum Color {
	
	NONEE(Group.NONE, ColGroup.NONE),
	REDDD(Group.RING, ColGroup.RED),
	BLUEE(Group.RING, ColGroup.BLUE),
	GREEN(Group.RING, ColGroup.GREEN),
	YELLO(Group.RING, ColGroup.YELLOW),
	RBASE(Group.BASE, ColGroup.RED),
	BBASE(Group.BASE, ColGroup.BLUE),
	GBASE(Group.BASE, ColGroup.GREEN),
	YBASE(Group.BASE, ColGroup.YELLOW),
	SBASE(Group.BASE, ColGroup.NONE); //starting base
	
	private Group group;
	private ColGroup colGroup;
	
	/**
	 * Constructor for Color
	 * @param group Group type
	 * @param colGroup
	 */
	
	Color(Group group, ColGroup colGroup) {
		this.group = group;
		this.colGroup = colGroup;
	}
	
	/**
	 * Getter for group type of this Color
	 * @return type of this Color. Can be NONE, RING, or BASE
	 */
	
	public Group getGroup() {
		return group;
	}
	
	/**
	 * Getter for color group of this Color
	 * @return color group of this Color. Can be NONE, RED, BLUE, GREEN, or YELLOW
	 */
	
	public ColGroup getColGroup() {
		return colGroup;
	}
	
	public enum Group {
		NONE,
		RING,
		BASE;
	}
	
	public enum ColGroup {
		NONE,
		RED,
		BLUE,
		GREEN,
		YELLOW;
	}
	
	/**
	 * Simple number to Color converter
	 * @param i Number to be converted
	 * @return Color corresponding to the number given
	 */
	
	public Color getColor(int i) {
		switch (i) {
			case 0:
				return REDDD;
			case 1:
				return BLUEE;
			case 2:
				return GREEN;
			case 3:
				return YELLO;
			default:
				return NONEE;
		}
	}
	
	/**
	 * simple Color to number converter
	 * @return int value of this Color
	 */
	
	public int getIntFromColor() {
		switch (getColGroup()) {	//NONE and default should never be reached
			case RED:
				return 0;
			case BLUE:
				return 1;
			case GREEN:
				return 2;
			case YELLOW:
				return 3;
			case NONE:
				return 4;
			default:
				return 5;
		}
	}
}
