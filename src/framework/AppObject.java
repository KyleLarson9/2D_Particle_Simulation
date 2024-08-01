package framework;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import vectors.Vector2D;

// Holds methods needed for object

public abstract class AppObject {

	protected double x, y;
	protected double velX = 0, velY = 0;
	protected double mass;
	protected Vector2D vector;

	protected ObjectId id;
	
	// default
	public AppObject() {
		
	}
	
	// basic object
	public AppObject(double x, double y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	// projectile
	public AppObject(double x, double y, double mass, Vector2D vector, ObjectId id) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.vector = vector;
		this.id = id;
	}
	
	public abstract void update(LinkedList<AppObject> object);
	public abstract void render(Graphics2D g2d);
	public abstract Rectangle2D getBounds();


	public double getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}
	
	public Vector2D getVector() {
		return vector;
	}
	
	public void setVector(Vector2D vector) {
		this.vector = vector;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x2) {
		this.x = x2;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
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
