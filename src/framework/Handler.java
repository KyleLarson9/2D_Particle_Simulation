package framework;

import java.awt.Graphics2D;
import java.util.LinkedList;

import main.Simulation;
import objects.Block;

// Handles the rendering and updating of all objects 

public class Handler {

	public LinkedList<SimulationObject> object = new LinkedList<SimulationObject>();
	
	private SimulationObject tempObject;
	
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
	
	public void addObject(SimulationObject object) {
		this.object.add(object);
		if(object.getId() == ObjectId.Projectile)
			System.out.println("Added");
	}
	
	public void removeObject(SimulationObject object) {
		this.object.remove(object);
	}
	

	
	public void createBounds() {
		
		int blockSize = (int) (8 * Simulation.SCALE);
		
		for(int i = 0; i < Simulation.APP_WIDTH - blockSize; i+=blockSize) 
			addObject(new Block(i, 0, ObjectId.Block));
		
		for(int i = 0; i < Simulation.APP_WIDTH - blockSize; i+= blockSize) 
			addObject(new Block(i, Simulation.APP_HEIGHT - blockSize, ObjectId.Block));
		
		for(int i = 0; i < Simulation.APP_HEIGHT  - blockSize; i+=blockSize)
			addObject(new Block(0, i, ObjectId.Block));
		
		for(int i = 0; i < Simulation.APP_HEIGHT - blockSize; i+= blockSize)
			addObject(new Block(Simulation.APP_WIDTH - blockSize, i, ObjectId.Block));
	}
	
	
}