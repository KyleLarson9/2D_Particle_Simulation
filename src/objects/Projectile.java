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
		
		updateVectorPosition();
		
		if(MouseInputs.clicked && !launched) {
			launched = true;
			double radians = vector.getMouseDirection(MouseInputs.getX(), MouseInputs.getY());
 			xVel = ScaleUtils.pixelsToMeters(vel) * Math.cos(radians);
			yVel = ScaleUtils.pixelsToMeters(vel) * Math.sin(radians);		
			
		} 

		if(launched) {
			
			yVel += GRAVITY * dt;
			
			x+=ScaleUtils.metersToPixels(xVel) * dt;
			y+=ScaleUtils.metersToPixels(yVel) * dt;

		}
		
		collision(object);
		
	}
	
	private void collision(LinkedList<AppObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			AppObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					// change this to a moving boolean
					y = tempObject.getY() - height;
					yVel = 0;
					xVel = 0;
					GRAVITY = 0;
				}
			}
			
		}
		
	}
	
	public void updateVectorPosition() {
		
		vector.x1 = x + 4;
		vector.x2 = MouseInputs.getX();
		vector.y1 = y + 4;
		vector.y2 = MouseInputs.getY();
		
		Vector2D normalizedVector = vector.normalize().multiplyByScalar(20);
		vector.x1 = normalizedVector.x1;
		vector.x2 = normalizedVector.x2;
		vector.y1 = normalizedVector.y1;
		vector.y2 = normalizedVector.y2;
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fill(new Rectangle2D.Double(x, y, width, height));

	}

	@Override
	public Rectangle2D getBounds() {

		return new Rectangle2D.Double(x, y, width, height);
	}


	
	
}

