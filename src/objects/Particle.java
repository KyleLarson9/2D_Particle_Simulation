package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import framework.Handler;
import framework.ObjectId;
import framework.SimulationObject;
import states.Simulating;
import vectors.Vector;


public class Particle extends SimulationObject {
			
	public boolean launched = false, moving = false;
	// something is fucking up the radius
	public Particle(double x, double y, double radius, double mass, Vector vector, Handler handler, ObjectId id) {
		super(x, y, radius, mass, vector, handler, id);
		this.handler = handler;
		this.r = radius;
	}
	
	// public methods
	
	public void update(LinkedList<SimulationObject> objects) {
				
		PhysicsLogic.togglePhysicsSettings(this);
		
		if(!Simulating.clicked && !launched) {					
			launched = true;
			moving = true;
			double radians = vector.getMouseDirection(Simulating.getX(), Simulating.getY());

			PhysicsLogic.setParticleVelocityComponents(this, radians);

		} 
		
		if(launched && moving) {
		
	       PhysicsLogic.applyGravity(this);
	        
	       PhysicsLogic.moveParticle(this);
	        
	    }
		
		double middleX = x + (r);
		double middleY = y + (r);
		
		Vector.updateVectorPositionToParticle(vector, middleX, middleY, xVel, yVel, launched, moving);

		PhysicsLogic.particleBlockCollision(objects, this);
		PhysicsLogic.particleParticleCollision(objects);
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
	    g2d.fill(new Ellipse2D.Double(x, y, r*2, r*2));

	}
	
	// Override methods

	@Override
	public Rectangle2D getBounds() {
	    return new Rectangle2D.Double(x, y, r, r);
	}
	
	// getters
	
	// math for these are written down
	public Rectangle2D getBottomBounds() {
		return new Rectangle2D.Double(x + (r/3), y + (r/2), r - (2*(r/3)), (r/2));
	}

	public Rectangle2D getTopBounds() {
		return new Rectangle2D.Double(x + (r/3), y, r - (2*(r/3)), (r/2));
	}

	public Rectangle2D getRightBounds() {
		return new Rectangle2D.Double(x + (r - (r/6)), y + (r/6), r/6, r - (2*(r/6)));
	}

	public Rectangle2D getLeftBounds() {
		return new Rectangle2D.Double(x, y + (r/6), r/6, r - (2*(r/6)));
	}
	
}

