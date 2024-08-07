package states;

import java.awt.Color;
import java.awt.Graphics2D;

import main.App;

// be able to click on a particle and then change its individual properties
// place particles where you want to
// add pause/resume button
// change number of particles and their attributes
// turn on and off and change constants -- gravity, friction coef of restitution etc

public class SimulationSettings {

	private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
	
	public SimulationSettings() {
		loadBackground();
	}
	
	public void update() {
		
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(backgroundX, backgroundY, backgroundWidth, backgroundHeight);
	}
	
	private void loadBackground() {
		backgroundWidth = (int) (100 * App.SCALE);
		backgroundHeight = (int) (200 * App.SCALE);
		backgroundX = App.APP_WIDTH - backgroundWidth - (int) (10 * App.SCALE);
		backgroundY = (int) (10 * App.SCALE);
	}
}
