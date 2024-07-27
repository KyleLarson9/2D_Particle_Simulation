package vectors;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class VectorHandler {

	public LinkedList<Vector2D> vectors = new LinkedList<>();
	
	private Vector2D tempVector;
	
	public VectorHandler() {

	}
	
	public void render(Graphics2D g2d) {
		for(Vector2D vector : vectors) {
			vector.render(g2d);
		}
	}
	
	public void update() {
		for(Vector2D vector : vectors) {
			vector.update(vectors);
		}
	}
	
	public void updateVectorToMousePosition(int mouseX, int mouseY) {
		for(int i = 0; i < vectors.size(); i++) {
			tempVector = vectors.get(i).normalizeToMouse(mouseX, mouseY).mutpliyByScalar(20);
			vectors.set(i, tempVector);
		}
	}
	
	public void addVector(Vector2D vector) {	
		this.vectors.add(vector);
	}
	
	public void removeVector(Vector2D vector) {
		this.vectors.remove(vector);
	}
	
}
