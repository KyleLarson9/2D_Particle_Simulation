package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D.Double;
import java.util.LinkedList;

import framework.AppObject;
import framework.ObjectId;
import framework.ScaleUtils;
import vectors.Vector2D;

// give velocity components

public class Projectile extends AppObject {
	
	public Projectile(float xPixels, float yPixels, float mass, Vector2D momentumVector, ObjectId id) {
		super(xPixels, yPixels, mass, momentumVector, id);
		momentumVector.x1 = xPixels;
		momentumVector.y1 = yPixels;
				
		momentumVector.x2 = xPixels+50;
		momentumVector.y2 = yPixels+50;
		
		float xPositionInMeters = ScaleUtils.pixelsToMeters(xPixels);
		float yPositionInMeters = ScaleUtils.pixelsToMeters(yPixels);
		
		System.out.println("Projectile posisiton in meters: (" + xPositionInMeters + "," + yPositionInMeters + ")");
	}
	
	// public methods
	
	public void update(LinkedList<AppObject> object) {

	}

	public void render(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fill(new Rectangle2D.Float(x, y, 8, 8));
		g2d.draw(new Line2D.Double(momentumVector.x1, momentumVector.y1, momentumVector.x2, momentumVector.y2));
		
	}

}

