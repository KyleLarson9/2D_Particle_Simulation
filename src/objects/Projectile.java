package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import framework.Constants;
import framework.Handler;
import framework.ObjectId;
import framework.ScaleUtils;
import framework.SimulationObject;
import main.Simulation;
import states.Simulating;
import states.SimulationSettings;
import vectors.Vector2D;


public class Projectile extends SimulationObject {
	
	private Handler handler;
	
	private final int width = (int) (12 * Simulation.SCALE);
	private final int height = (int) (12 * Simulation.SCALE);

	private boolean launched = false;
	private boolean moving = false;
	
	private double vel = 100; // pixels/s
	private double xVel, yVel;
	
	private double GRAVITY;
	
	private float dt = .00833f; // 1 / FPS
		
	public Projectile(double x, double y, double mass, Vector2D vector, Handler handler, ObjectId id) {
		super(x, y, mass, vector, handler, id);
		this.handler = handler;
		
	}
	
	// public methods
	
	public void update(LinkedList<SimulationObject> object) {
				
		toggleGravity();
		
		if(Simulating.clicked && !launched) {					
			launched = true;
			moving = true;
			double radians = vector.getMouseDirection(Simulating.getX(), Simulating.getY());
 			xVel = ScaleUtils.pixelsToMeters(vel) * Math.cos(radians);
			yVel = ScaleUtils.pixelsToMeters(vel) * Math.sin(radians);			
		} 
		if(launched && moving) {
			
			yVel += GRAVITY * dt;
			
			x+=ScaleUtils.metersToPixels(xVel) * dt * Simulation.SCALE;
			y+=ScaleUtils.metersToPixels(yVel) * dt * Simulation.SCALE;

		}
		
		double middleX = x + (width/2);
		double middleY = y + (height/2);
		
		Vector2D.updateVectorPositionToParticle(vector, middleX, middleY, xVel, yVel, launched, moving);

		collision(object);
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fill(new Ellipse2D.Double(x, y, width, height));
		
		g2d.setColor(Color.red);

	}
	
	// private methods
	
	private void toggleGravity() {
		if(SimulationSettings.getGravityState() == true) 
			GRAVITY = 9.81;
		else if(SimulationSettings.getGravityState() == false)
			GRAVITY = 0;
	}
	
	private void collision(LinkedList<SimulationObject> object) {
		
		// come back to this ball wall collision logic later
		// sometimes the ball will bounce back the wrong way -- won't reflect

		double velocityThreshold = 0.03; 
		for(int i = 0; i < handler.object.size(); i++) {
			SimulationObject tempObject = handler.object.get(i);			
			                                                       
			if(tempObject.getId() == ObjectId.Block) {
				
				Rectangle2D blockBounds = tempObject.getBounds();
				
				if(getTopBounds().intersects(blockBounds)) {
					y = tempObject.getY() + blockBounds.getHeight();
					yVel *= - 1;
				}
				
				if(getBottomBounds().intersects(blockBounds)) {
					y = tempObject.getY() - height;
					yVel *= -1;
					if(Math.abs(yVel) < velocityThreshold) {
					    moving = false;
					}
					
				}
				
				if(getRightBounds().intersects(blockBounds)) {                                                              
					x = tempObject.getX() - width;
					xVel *= - 1;
				} 
				
				if(getLeftBounds().intersects(blockBounds)) {
					x = tempObject.getX() + blockBounds.getWidth();
					xVel *= - 1;  
				}
				

			}
		 	
		}
		
	}
	
	// Override methods

	@Override
	public Rectangle2D getBounds() {
	    return new Rectangle2D.Double(x, y, width, height);
	}

	// math for these are written down
	@Override
	public Rectangle2D getBottomBounds() {
	    return new Rectangle2D.Double(x + (width/3), y + (height/2), width - (2*(width/3)), (height/2));
	}

	@Override
	public Rectangle2D getTopBounds() {
	    return new Rectangle2D.Double(x + (width/3), y, width - (2*(width/3)), (height/2));
	}

	@Override
	public Rectangle2D getRightBounds() {
	    return new Rectangle2D.Double(x + (width - (width/6)), y + (height/6), width/6, height - (2*(height/6)));
	}

	@Override
	public Rectangle2D getLeftBounds() {
	    return new Rectangle2D.Double(x, y + (height/6), width/6, height - (2*(height/6)));
	}
	
}

