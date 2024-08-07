package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import framework.AppObject;
import framework.ObjectId;

public class Block extends AppObject {

	private double width = 8, height = 8;
	
	public Block(double x, double y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void update(LinkedList<AppObject> object) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.draw(new Rectangle2D.Double(x, y, width, height));
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}

	@Override
	public Rectangle2D getBottomBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getTopBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getRightBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getLeftBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
