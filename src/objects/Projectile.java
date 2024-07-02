package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import framework.AppObject;
import framework.ObjectId;

public class Projectile extends AppObject {

	public Projectile(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	
	public void update(LinkedList<AppObject> object) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.fillRect((int) x, (int) y, 8, 8);
	}



}
