package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import framework.Handler;
import framework.ObjectId;
import inputs.MouseInputs;
import main.Simulation;
import objects.Projectile;
import vectors.Vector2D;
import vectors.VectorHandler;

// add specific launch button instead of clicking 

public class Simulating extends State implements StateMethods {

	private Handler handler;
	private VectorHandler vectorHandler;
	private Projectile newProjectile;
	private Vector2D vector;
	// states
	private SimulationSettings simulationSettings;
	
	public static int x, y;
	public static boolean clicked = false;
	
	double startX = 10;
    double startY = Simulation.APP_HEIGHT / 2.0;
    double targetX = getX();
    double targetY = getY();
	    
	public Simulating(Simulation simulation) {
		super(simulation);
		initializeClasses();
		
		handler.createBounds();
		
	}
	
	public void initializeClasses() {
		handler = new Handler();
		vectorHandler = new VectorHandler();
		simulationSettings = new SimulationSettings(simulation);
	}

	@Override
	public void update() {
		handler.update();
		
		if(clicked) {
						
			double startX = 10;
		    double startY = Simulation.APP_HEIGHT / 2.0;
		    double targetX = getX();
		    double targetY = getY();
		    
		    vector = new Vector2D(startX, startY, targetX, targetY);
		    vectorHandler.addVector(vector);
		    newProjectile = new Projectile(startX, startY, 10, vector, handler, ObjectId.Projectile);
		    handler.addObject(newProjectile);
		    
		    resetClick(); 
		} 

		
	}

	@Override
	public void render(Graphics2D g2d) {
		vectorHandler.render(g2d);
		handler.render(g2d);
		simulationSettings.render(g2d);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		clicked = true;

		x = e.getX();
		y = e.getY();
		
		// check if mouse is in bounds of the settings state - temporary
		if(x > simulationSettings.getBackgroundX() && x < simulationSettings.getBackgroundX() + simulationSettings.getBackgroundWidth()
		   && y > simulationSettings.getBackgroundY() && y < simulationSettings.getBackgroundY() + simulationSettings.getBackgroundHeight()) 
			clicked = false;
		
		// ***********************FOR NOW***********************
		// click check boxes
		if(x > simulationSettings.gravityCheckbox.getX() && x < simulationSettings.gravityCheckbox.getX() + simulationSettings.gravityCheckbox.getWidth()
		   && y > simulationSettings.gravityCheckbox.getY() && y < simulationSettings.gravityCheckbox.getY() + simulationSettings.gravityCheckbox.getHeight()) {
			// check or uncheck
			simulationSettings.gravityEnabled = !simulationSettings.gravityEnabled;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) { 
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
			SimulationState.state = SimulationState.START_MENU;
	}

	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	public void resetClick() {
	clicked = false;
	}
}
