package vectors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class VectorHandler {

	public LinkedList<Vector> vectors = new LinkedList<>();
	
	
	public VectorHandler() {

	}
	
	public void render(Graphics2D g2d) {
		for(Vector vector : vectors) {
			g2d.setColor(Color.white);
			g2d.draw(new Line2D.Double(vector.x1, vector.y1, vector.x2, vector.y2)); // for now	
		}
	}

	public void addVector(Vector vector) {	
		this.vectors.add(vector);
	}
	
	public void removeVector(Vector vector) {
		this.vectors.remove(vector);
	}
	
}
        