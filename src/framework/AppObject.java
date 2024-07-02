package framework;

import java.awt.Graphics2D;
import java.util.LinkedList;

public abstract class AppObject {

	protected float x, y;
	protected float velX = 0, velY = 0;
	
	protected ObjectId id;
	
	public AppObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void update(LinkedList<AppObject> object);
	public abstract void render(Graphics2D g2d);
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ObjectId getId() {
		return id;
	}

}
