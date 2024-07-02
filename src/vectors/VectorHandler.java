package vectors;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class VectorHandler {

	public LinkedList<Vector2D> vectors = new LinkedList<>();
	
	private Vector2D tempVector;
	
	public VectorHandler() {
		addVectorsInCircle();
	}
	
	public void render(Graphics2D g2d) {
		for(int i = 0; i < vectors.size(); i++) {
			tempVector = vectors.get(i);
			tempVector.render(g2d);
		}
	}
	
	public void addVectorsInCircle() {
		int totalVectors = 20;
		double centerX = 300;
		double centerY = 400;
		int radius = 100;
		
		for(int i = 0; i < totalVectors; i++) {
			double angle = Math.toRadians((i * 360) / totalVectors);
			double x = centerX + radius * Math.cos(angle);
			double y = centerY + radius * Math.sin(angle);
			
			Vector2D currentVector = new Vector2D(centerX, centerY, x, y);
			
			this.vectors.add(currentVector);
		}
		
	}
	
	public void addVector(Vector2D vector) {
		
		this.vectors.add(vector);
	}
	
}
