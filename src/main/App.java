package main;

import java.awt.Graphics2D;

import framework.Handler;
import framework.ObjectId;
import objects.Projectile;
import vectors.Vector2D;
import vectors.VectorHandler;

// be able to move mouse and have a momentum vector follow
// left click to launch projectiles in that direction

public class App {

	private AppFrame frame;
	protected AppPanel panel;
	private Handler handler;
	private VectorHandler vectorHandler;
	
	private final static int TILES_DEFAULT_SIZE = 20;
	private final static float SCALE = 1.0f;
	private final static int TILES_IN_WIDTH = 40;
	private final static int TILES_IN_HEIGHT = 40;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int APP_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int APP_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	
	public App() {

		initializeClasses();
		panel.setFocusable(true);
		panel.requestFocus();
		
	}
	
	// public methods
	
	public void render(Graphics2D g2d) {
		handler.render(g2d);
		vectorHandler.render(g2d);
	}
	
	public void update() {
		handler.update();
	}
	
	// private methods

	private void initializeClasses() {
		
		handler = new Handler();
		vectorHandler = new VectorHandler();
		panel = new AppPanel(this);
		frame = new AppFrame(panel);
		
		handler.addObject(new Projectile(100, 100, ObjectId.Projectile));
	}
	
}
