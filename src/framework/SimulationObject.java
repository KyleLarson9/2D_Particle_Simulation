package framework;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import vectors.Vector2D;

// Holds methods needed for object

public abstract class SimulationObject {

	protected double x, y;
	protected double velX = 0, velY = 0;
	protected double mass;
	protected double r;
	protected Vector2D vector;
	protected Handler handler;
	
	protected ObjectId id;
	
	// default
	public SimulationObject() {
		
	}
	
	// basic object
	public SimulationObject(double x, double y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	// ball
	public SimulationObject(double x, double y, double r, double mass, Vector2D vector, Handler handler, ObjectId id) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.mass = mass;
		this.vector = vector;
		this.id = id;
		this.handler = handler;
	}

	// projectile
	public SimulationObject(double x, double y, double mass, Vector2D vector, Handler handler, ObjectId id) {
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.vector = vector;
		this.id = id;
		this.handler = handler;
	}
	
	public abstract void update(LinkedList<SimulationObject> object);
	public abstract void render(Graphics2D g2d);
	public abstract Rectangle2D getBounds();
	public abstract Rectangle2D getBottomBounds();
	public abstract Rectangle2D getTopBounds();
	public abstract Rectangle2D getRightBounds();
	public abstract Rectangle2D getLeftBounds();
	
	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}
	
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
