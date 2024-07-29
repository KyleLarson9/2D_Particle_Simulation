package vectors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import framework.ObjectId;

public class VectorHandler {

	public LinkedList<Vector2D> vectors = new LinkedList<>();
	
	private Vector2D tempVector;
	
	public VectorHandler() {

	}
	
	public void render(Graphics2D g2d) {
		for(Vector2D vector : vectors) {
			g2d.setColor(Color.white);
			g2d.draw(new Line2D.Double(vector.x1, vector.y1, vector.x2, vector.y2));		}
	}

	public void addVector(Vector2D vector) {	
		this.vectors.add(vector);
	}
	
	public void removeVector(Vector2D vector) {
		this.vectors.remove(vector);
	}
	
}
