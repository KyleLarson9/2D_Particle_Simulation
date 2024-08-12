package states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import framework.Handler;
import framework.ObjectId;
import main.Simulation;
import objects.Projectile;
import vectors.Vector2D;
import vectors.VectorHandler;

public class Simulating extends State implements StateMethods {

	private Handler handler;
	private VectorHandler vectorHandler;
	private Projectile newProjectile;
	private Vector2D vector;
	
	// states
	private SimulationSettings simulationSettings;
	
	public static int x, y;
	public static boolean clicked = false;
	    
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
		
		// create new projectile object
		if(clicked) { 
						
			double startX = 20;
		    double startY = Simulation.APP_HEIGHT / 2.0;
		    double targetX = getX();
		    double targetY = getY();
		    
		    vector = new Vector2D(startX, startY, targetX, targetY);
		    vectorHandler.addVector(vector);
		    newProjectile = new Projectile(startX, startY, 12 * Simulation.SCALE, 10, vector, handler, ObjectId.Projectile);
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
			SimulationConfig.setGravityEnabled(!SimulationConfig.isGravityEnabled());
		}
		
		// check if mouse is in the gravity check box		
		if(x > simulationSettings.gravityTextBox.getX() && x < simulationSettings.gravityTextBox.getX() + simulationSettings.gravityTextBox.getWidth() 
		   && y > simulationSettings.gravityTextBox.getY() && y < simulationSettings.gravityTextBox.getY() + simulationSettings.gravityTextBox.getHeight()) {
			simulationSettings.gravityTextBoxActive = true;
			simulationSettings.gravityInput = ""; // clear
		} else {
			simulationSettings.gravityTextBoxActive = false;
		}
		
		if(x > simulationSettings.coeffRestitutionTextbox.getX() && x < simulationSettings.coeffRestitutionTextbox.getX() + simulationSettings.coeffRestitutionTextbox.getWidth() 
		   && y > simulationSettings.coeffRestitutionTextbox.getY() && y < simulationSettings.coeffRestitutionTextbox.getY() + simulationSettings.coeffRestitutionTextbox.getHeight()) {
			simulationSettings.coeffRestitutionTextBoxActive = true;
			simulationSettings.coeffRestitutionInput = ""; // clear
		} else {
			simulationSettings.coeffRestitutionTextBoxActive = false;
		}
		
		if(x > simulationSettings.perfectyInelasticCollisionCheckbox.getX() && x < simulationSettings.perfectyInelasticCollisionCheckbox.getX() + simulationSettings.perfectyInelasticCollisionCheckbox.getWidth() 
		   && y > simulationSettings.perfectyInelasticCollisionCheckbox.getY() && y < simulationSettings.perfectyInelasticCollisionCheckbox.getY() + simulationSettings.perfectyInelasticCollisionCheckbox.getHeight()) {
			SimulationConfig.setIsPerfectlyInelastic(!SimulationConfig.getIsPerfectlyInelastic());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (simulationSettings.gravityTextBoxActive) {
            if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.') {
            	simulationSettings.gravityInput += e.getKeyChar(); // append character to input
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && simulationSettings.gravityInput.length() > 0) {
            	simulationSettings.gravityInput = simulationSettings.gravityInput.substring(0, simulationSettings.gravityInput.length() - 1); // handle backspace
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            	double newGravity = Double.parseDouble(simulationSettings.gravityInput);
            	SimulationConfig.setGravity(newGravity);
                simulationSettings.gravityTextBoxActive = false;
            }
        }
		
		// make it 0 <= value <= 1
		if (simulationSettings.coeffRestitutionTextBoxActive) {
            if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '.') {
            	simulationSettings.coeffRestitutionInput += e.getKeyChar(); // append character to input
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && simulationSettings.coeffRestitutionInput.length() > 0) {
            	simulationSettings.coeffRestitutionInput = simulationSettings.coeffRestitutionInput.substring(0, simulationSettings.coeffRestitutionInput.length() - 1); // handle backspace
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            	double newCoeffRestitution = Double.parseDouble(simulationSettings.coeffRestitutionInput);
            	SimulationConfig.setCoeffRestitution(newCoeffRestitution);
                simulationSettings.coeffRestitutionTextBoxActive = false;
            }
        }
		
	}

	
	@Override
	public void keyReleased(KeyEvent e) { 
		
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
	
	public SimulationSettings getSimulationSettings() {
		return simulationSettings;
	}
}
