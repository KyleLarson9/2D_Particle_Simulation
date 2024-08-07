package framework;

public enum ObjectId {

	Projectile(1),
	Ball(2),
	Block(3);
	
	private final int id;
	
	ObjectId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
