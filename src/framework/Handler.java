package framework;

import java.awt.Graphics2D;
import java.util.LinkedList;

import main.App;
import objects.Block;

// Handles the rendering and updating of all objects 

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
	
	// create seperate class for this later and make it much better
	// testing purposes
	public void createLevel() {
		for(int i = 0; i < App.APP_WIDTH + 8; i+=8) {
			addObject(new Block(i, App.APP_HEIGHT/2 + 8, ObjectId.Block));			
		}
		
		for(int i = 0; i < App.APP_HEIGHT/2 + 8; i+=8) {
			addObject(new Block(App.APP_WIDTH/2 - 80, i, ObjectId.Block));			
		}
		
		for(int i = 0; i < App.APP_HEIGHT/2 + 8; i+=8) {
			addObject(new Block(App.APP_WIDTH/2 - 150, i + 208, ObjectId.Block));		
		}
		
		for(int i = 0; i < 48; i+=8) {
			addObject(new Block(i, App.APP_HEIGHT/2 - 100, ObjectId.Block));
		}
	}
	
}