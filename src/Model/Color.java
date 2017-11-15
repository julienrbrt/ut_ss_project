package Model;

public enum Color {
	
	NONEE(Group.NONE),
	REDDD(Group.RING),
	PURPL(Group.RING),
	GREEN(Group.RING),
	YELLO(Group.RING),
	RBASE(Group.BASE),
	PBASE(Group.BASE),
	GBASE(Group.BASE),
	YBASE(Group.BASE),
	SBASE(Group.BASE); //starting base
	
	private Group group;
	
	Color(Group group) {
		this.group = group;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public enum Group {
		NONE,
		RING,
		BASE;
	}

}
