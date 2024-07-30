package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.Path2D.Float;
import java.util.LinkedList;

import framework.AppObject;
import framework.Constants;
import framework.ObjectId;
import framework.ScaleUtils;
import inputs.MouseInputs;
import vectors.Vector2D;

public class Projectile extends AppObject {
	
	private final int WIDTH_PIXELS = 8;
	private final int HEIGHT_PIXELS = 8;

	private Vector2D normalizedVector;

	private boolean launched = false;
	private boolean moving = false;
	
	private double vel = 2;
	private double xVel, yVel;
	
	public Projectile(double x, double y, double mass, Vector2D vector, ObjectId id) {
		super(x, y, mass, vector, id);

	}
	
	// public methods
	
	public void update(LinkedList<AppObject> object) {
				
		updateVectorPosition();
		
		
		if(MouseInputs.clicked && !launched) {
			launched = true;
					
			double radians = vector.getTestDirection(MouseInputs.getX(), MouseInputs.getY());
			xVel = vel * Math.cos(radians);
			yVel = vel * Math.sin(radians);		
		} 
		
		if(launched) {
			x+=xVel;
			y+=yVel;
		}
		
	}
	
	public void updateVectorPosition() {
		vector.x1 = x + 4;
		vector.x2 = MouseInputs.getX();
		vector.y1 = y + 4;
		vector.y2 = MouseInputs.getY();
		
		normalizedVector = vector.normalize().multiplyByScalar(20);
		vector.x1 = normalizedVector.x1;
		vector.x2 = normalizedVector.x2;
		vector.y1 = normalizedVector.y1;
		vector.y2 = normalizedVector.y2;
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.red);
		g2d.fill(new Rectangle2D.Double(x, y, WIDTH_PIXELS, HEIGHT_PIXELS));

	}
	
	
}

