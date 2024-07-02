package framework;

import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler {

	public LinkedList<AppObject> object = new LinkedList<AppObject>();
	
	private AppObject tempObject;
	
	public void update() {	
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.update(object);
		}		
	}
	
	public void render(Graphics2D g2d) {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g2d);
		}
	}
	
	public void addObject(AppObject object) {
		this.object.add(object);
	}
	
	public void removeObject(AppObject object) {
		this.object.remove(object);
	}
	
}