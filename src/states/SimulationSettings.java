package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Simulation;

public class SimulationSettings extends State implements StateMethods {

	private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
	
	public Rectangle gravityCheckbox;
	public Rectangle coeffRestitutionCheckbox;
	
	// initial settings
	public static boolean gravityEnabled = true;
	public boolean coeffRestitutionEnabled = false;
	
	public SimulationSettings(Simulation simulation) {
		super(simulation);
		loadBackground();
	
	}
	
	public void update() {
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);
		
		renderComponents(g2d);
		
	}
	
	private void renderComponents(Graphics2D g2d) {
		
		// Gravity
		if(gravityEnabled) g2d.setColor(Color.green);
		else g2d.setColor(Color.red);
			
		gravityCheckbox = new Rectangle(backgroundX + 20, backgroundY + 20, 10, 10);
		g2d.fill(gravityCheckbox);
				
		// Coefficient of Restitution
		if(coeffRestitutionEnabled) g2d.setColor(Color.green);
		else g2d.setColor(Color.red);
		
		coeffRestitutionCheckbox = new Rectangle(backgroundX + 20, backgroundY + 40, 10, 10);
		g2d.fill(coeffRestitutionCheckbox);
	}
	
	private void loadBackground() {
		backgroundWidth = (int) (100 * Simulation.SCALE);
		backgroundHeight = (int) (200 * Simulation.SCALE);
		backgroundX = Simulation.APP_WIDTH - backgroundWidth - (int) (10 * Simulation.SCALE);
		backgroundY = (int) (10 * Simulation.SCALE);
	}
	

	public static boolean getGravityState() {
		return gravityEnabled;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getBackgroundX() {
		return backgroundX;
	}

	public int getBackgroundY() {
		return backgroundY;
	}
	
	public int getBackgroundWidth() {
		return backgroundWidth;
	}

	public int getBackgroundHeight() {
		return backgroundHeight;
	}
}
