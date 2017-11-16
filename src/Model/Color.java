package Model;

public enum Color {
	
	NONEE(Group.NONE,ColGroup.NONE),
	REDDD(Group.RING,ColGroup.RED),
	PURPL(Group.RING,ColGroup.PURPLE),
	GREEN(Group.RING,ColGroup.GREEN),
	YELLO(Group.RING,ColGroup.YELLOW),
	RBASE(Group.BASE,ColGroup.RED),
	PBASE(Group.BASE,ColGroup.PURPLE),
	GBASE(Group.BASE,ColGroup.GREEN),
	YBASE(Group.BASE,ColGroup.YELLOW),
	SBASE(Group.BASE,ColGroup.NONE); //starting base
	
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
		PURPLE,
		GREEN,
		YELLOW,;
	}

}
