package main;

import java.awt.Graphics2D;
import java.util.Random;

import framework.Handler;
import framework.ObjectId;
import inputs.MouseInputs;
import objects.Projectile;
import vectors.Vector2D;
import vectors.VectorHandler;

public class App implements Runnable {

	private AppFrame frame;
	protected AppPanel panel;
	private MouseInputs mouseInputs;
	private Handler handler;
	private VectorHandler vectorHandler;
	private Random rand = new Random();
	
	private Thread thread;
	private boolean running = true;
	
	private final int FPS = 120;
	private final int UPS = 200;
	
	private final static int TILES_DEFAULT_SIZE = 20;
	private final static float SCALE = 1.0f;
	private final static int TILES_IN_WIDTH = 40;
	private final static int TILES_IN_HEIGHT = 40;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int APP_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int APP_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
		
	int updateCounter = 0;
	
	public App() {

		initializeClasses();
		panel.setFocusable(true);
		panel.requestFocus();
		
		startAppLoop();
	}
	
	// public methods
	
	public void render(Graphics2D g2d) {
		vectorHandler.render(g2d);
		handler.render(g2d);
	}
	
	public void update() {
		handler.update();
		
	}
	
	// private methods

	private void startAppLoop() {
		thread = new Thread(this);
		thread.start();
	} 
	 
	
	private void initializeClasses() {

		handler = new Handler();
		vectorHandler = new VectorHandler();
		panel = new AppPanel(this);
		frame = new AppFrame(panel);
		mouseInputs = new MouseInputs(this);
		
		panel.addMouseListener(mouseInputs);  
		panel.addMouseMotionListener(mouseInputs);
	
		initializeObjectsAndVectors();
		
	}

	private void initializeObjectsAndVectors() {
		
		Vector2D momentumVector = new Vector2D(0, 0, 0, 0);
		vectorHandler.addVector(momentumVector);
		Projectile projectile = new Projectile((APP_WIDTH/2), (APP_HEIGHT/2), 10, momentumVector, ObjectId.Projectile);
		handler.addObject(projectile);
	}
	
	@Override
	public void run() {
		double timePerFrame = 1_000_000_000.0 / FPS; // how long each from will last, 1 second
		double timePerUpdate = 1_000_000_000.0 / UPS;
		
		long previousTime = System.nanoTime();
			
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(running) {
			long currentTime = System.nanoTime();
			 
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				frames = 0;
				updates = 0;
			}

		}		
	}
	
}
