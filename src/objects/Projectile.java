package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import framework.AppObject;
import framework.Constants;
import framework.Handler;
import framework.ObjectId;
import framework.ScaleUtils;
import inputs.MouseInputs;
import vectors.Vector2D;

// add elastic collisions

public class Projectile extends AppObject {
	
	private Handler handler;
	
	private final int width = 8;
	private final int height = 8;

	private boolean launched = false;
	private boolean moving = false;
	
	private double vel = 250; // pixels/s
	private double xVel, yVel;
	
	private double GRAVITY = Constants.GRAVITY.getConstant();
	
	private float dt = .00833f; // 1 / FPS
		
	public Projectile(double x, double y, double mass, Vector2D vector, Handler handler, ObjectId id) {
		super(x, y, mass, vector, id);
		this.handler = handler;
		
	}
	
	// public methods
	
	public void update(LinkedList<AppObject> object) {
				
		if(MouseInputs.clicked && !launched) {					
			launched = true;
			moving = true;
			double radians = vector.getMouseDirection(MouseInputs.getX(), MouseInputs.getY());
 			xVel = ScaleUtils.pixelsToMeters(vel) * Math.cos(radians);
			yVel = ScaleUtils.pixelsToMeters(vel) * Math.sin(radians);			
		} 
		if(launched && moving) {
			
			yVel += GRAVITY * dt;
			
			x+=ScaleUtils.metersToPixels(xVel) * dt;
			y+=ScaleUtils.metersToPixels(yVel) * dt;

		}
		updateVectorPosition();

		collision(object);
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fill(new Rectangle2D.Double(x, y, width, height));
		
		g2d.setColor(Color.red);

	}
	
	// private methods
	
	private void collision(LinkedList<AppObject> object) {
		
		double coefficientOfRestitution = .5;
		double coefficientOfKineticFriction = .7;
		// now add needs friction
		
		for(int i = 0; i < handler.object.size(); i++) {
			AppObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
								
				if(getTopBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height;
					yVel *=-1 * coefficientOfRestitution;
				}
				
				if(getBottomBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					yVel *= -1 * coefficientOfRestitution;
					
					// just for testing -- not official
					xVel *= coefficientOfKineticFriction;
					
					// the way it is calculating, it will just keep approaching 0
					if(xVel == 0 && yVel == 0) 
						moving = false;
					
				}
				
				if(getRightBounds().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					xVel *= -1 * coefficientOfRestitution;
				} 
				
				if(getLeftBounds().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + width;
					xVel *= -1 * coefficientOfRestitution;
				}
				

			}
		 	
		}
		
	}
	
	private void updateVectorPosition() { // put this in vector class
		
		double middleX = x + (width/2);
		double middleY = y + (height/2);
		
		if(!launched) { // get initial direction
			Vector2D directionVector = new Vector2D(middleX, middleY, MouseInputs.getX(), MouseInputs.getY());
			directionVector = directionVector.normalize().multiplyByScalar(20);
			setVector(vector, directionVector);
		} else if(launched && moving) { // velocity vector
			Vector2D velocityVector = new Vector2D(middleX, middleY, middleX + (xVel), middleY + (yVel));
			velocityVector = velocityVector.normalize().multiplyByScalar(20);
            setVector(vector, velocityVector);
        } else if(launched && !moving) {
			vector.x1 = 0;
			vector.x2 = 0;
			vector.y1 = 0;
			vector.y2 = 0;
		}
		
	}
	
	public void setVector(Vector2D vec1, Vector2D vec2) {
		vec1.x1 = vec2.x1;
		vec1.y1 = vec2.y1;
		vec1.x2 = vec2.x2;
		vec1.y2 = vec2.y2;
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

