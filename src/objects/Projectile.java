package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D.Double;
import java.util.LinkedList;

import framework.AppObject;
import framework.ObjectId;
import vectors.Vector2D;

public class Projectile extends AppObject {
			
	public Projectile(float x, float y, float mass, Vector2D momentumVector, ObjectId id) {
		super(x, y, mass, momentumVector, id);
		momentumVector.x1 = x;
		momentumVector.y1 = y;
		
		
		momentumVector.x2 = x+50;
		momentumVector.y2 = y+50;
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

