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
	
	Color(Group group, ColGroup colGroup) {
		this.group = group;
		this.colGroup = colGroup;
	}
	
	public Group getGroup() {
		return group;
	}
	
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
	
	public Color getColor(int i) {	//simple number to color converter
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
	
}
