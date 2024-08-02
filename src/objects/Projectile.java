package objects;

import java.awt.Color;
import java.awt.Graphics2D;
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
	
	private double vel = 200; // pixels/s
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

	}
	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}
	
	// private methods
	
	private void collision(LinkedList<AppObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			AppObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					moving = false;
				}
			}
			
		}
		
	}
	
	private void updateVectorPosition() {

		if(!launched) { // get initial direction
			Vector2D directionVector = new Vector2D(x + (width/2), y + (height/2), MouseInputs.getX(), MouseInputs.getY());
			directionVector = directionVector.normalize().multiplyByScalar(20);
			setVector(vector, directionVector);
		} else if(launched && moving) { // velocity vector
			Vector2D velocityVector = new Vector2D(x + (width/2), y + (height/2), x + (width/2) + (xVel), y + (height/2) + (yVel));
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
	
}

