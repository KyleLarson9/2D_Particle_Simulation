package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Simulation;

public class SimulationSettings extends State implements StateMethods {

	private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
	
	public Rectangle gravityCheckbox;
	public Rectangle gravityTextBox;
	public Rectangle coeffRestitutionCheckbox;
	public Rectangle coeffRestitutionTextbox;
	
	public boolean gravityTextBoxActive = false;
	public boolean coeffRestitutionTextBoxActive = false;
	public String gravityInput = Double.toString(SimulationConfig.getGravity());
	public String coeffRestitutionInput = Double.toString(SimulationConfig.getCoeffRestitution());
	
	public SimulationSettings(Simulation simulation) {
		super(simulation);
		loadBackground();

	}
	
	// public methods
	
	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);
		
		renderComponents(g2d);
		
	}
	
	// private methods
	
	private void renderComponents(Graphics2D g2d) { // need to do this better
		
		// Gravity
		if(SimulationConfig.isGravityEnabled()) g2d.setColor(Color.green);
		else g2d.setColor(Color.red);
			
		gravityCheckbox = new Rectangle((int) (backgroundX + (10 * Simulation.SCALE)) , (int) (backgroundY + (10 * Simulation.SCALE)), (int) (5 * Simulation.SCALE), (int) (5 * Simulation.SCALE));
		g2d.fill(gravityCheckbox);
		
		// text box to change gravity amount
		gravityTextBox = new Rectangle((int) (backgroundX + (46.5 * Simulation.SCALE)), (int) (backgroundY + (9 * Simulation.SCALE)), (int) (13.5 * Simulation.SCALE), (int) (6 * Simulation.SCALE));
		g2d.setColor(Color.black);
		g2d.drawRect((int) (backgroundX + (46 * Simulation.SCALE)), (int) (backgroundY + (8.5 * Simulation.SCALE)), (int) (14 * Simulation.SCALE), (int) (6.5 * Simulation.SCALE));
		g2d.setColor(Color.white);
		g2d.fill(gravityTextBox);
		
		g2d.setColor(Color.black);
        Font font = new Font("Serif", Font.BOLD, (int) (7 * Simulation.SCALE));
        g2d.setFont(font);
		g2d.drawString("Gravity: " + gravityInput, (int) (backgroundX + (20 * Simulation.SCALE)), (int) (backgroundY + (14.5 * Simulation.SCALE)));

		// Coefficient of Restitution
		
		if(SimulationConfig.isCoeffRestitutionEnabled()) g2d.setColor(Color.green);
		else g2d.setColor(Color.red);
		
		coeffRestitutionCheckbox = new Rectangle((int) (backgroundX + (10 * Simulation.SCALE)) , (int) (backgroundY + (20 * Simulation.SCALE)), (int) (5 * Simulation.SCALE), (int) (5 * Simulation.SCALE));
		g2d.fill(coeffRestitutionCheckbox);
		
		coeffRestitutionTextbox = new Rectangle((int) (backgroundX + (77 * Simulation.SCALE)), (int) (backgroundY + (20 * Simulation.SCALE)), (int) (13.5 * Simulation.SCALE), (int) (6 * Simulation.SCALE));
		g2d.setColor(Color.black);
		g2d.drawRect((int) (backgroundX + (76.5 * Simulation.SCALE)), (int) (backgroundY + (19.5 * Simulation.SCALE)), (int) (14 * Simulation.SCALE), (int) (6.5 * Simulation.SCALE));
		g2d.setColor(Color.white);
		g2d.fill(coeffRestitutionTextbox);
		
		g2d.setColor(Color.black);
        g2d.setFont(font);
		g2d.drawString("Coeff Restitution: " + coeffRestitutionInput, (int) (backgroundX + (20 * Simulation.SCALE)), (int) (backgroundY + (25 * Simulation.SCALE)));
	}
	
	private void loadBackground() {
		backgroundWidth = (int) (100 * Simulation.SCALE);
		backgroundHeight = (int) (200 * Simulation.SCALE);
		backgroundX = Simulation.APP_WIDTH - backgroundWidth - (int) (10 * Simulation.SCALE);
		backgroundY = (int) (10 * Simulation.SCALE);
	}
	
	// override methods
	
	@Override
	public void mouseClicked(MouseEvent e) {
				
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
	}

	// getters
	
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
