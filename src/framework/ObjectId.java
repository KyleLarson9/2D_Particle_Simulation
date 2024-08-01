package framework;

public enum ObjectId {

	Projectile(1),
	Block(2);
	
	private final int id;
	
	ObjectId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
