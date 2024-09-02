package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import framework.ObjectId;
import framework.SimulationObject;
import main.Simulation;

public class Block extends SimulationObject {

	public double width = 8 * Simulation.SCALE, height = 8 * Simulation.SCALE;
	
	public Block(double x, double y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void update(LinkedList<SimulationObject> object) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.white);
		g2d.draw(new Rectangle2D.Double(x, y, width, height));
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, width, height);
	}

}
