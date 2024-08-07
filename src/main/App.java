package main;

import java.awt.Graphics2D;

import framework.Handler;
import states.AppState;
import states.Menu;
import states.Simulating;

public class App implements Runnable {

	private AppFrame frame;
	public AppPanel panel;
	private Thread thread;
	private Handler handler;
	
	// states
	private Simulating simulating;
	private Menu menu;
		
	private final int FPS = 120;
	private final int UPS = 200;
	
	private final static int TILES_DEFAULT_SIZE = 20;
	private final static float SCALE = 1.0f;
	private final static int TILES_IN_WIDTH = 40;
	private final static int TILES_IN_HEIGHT = 40;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int APP_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int APP_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
		
	public App() {

		initializeClasses();
		
		panel = new AppPanel(this);
		frame = new AppFrame(panel);
		panel.setFocusable(true);
		panel.requestFocus();
		
		startAppLoop();
		
	}
	
	// public methods
	
	public void render(Graphics2D g2d) {
		
		switch(AppState.state) {
		case MENU:
			menu.render(g2d);
			break;
		case SIMULATING:
			simulating.render(g2d);
			break;
		default:
			break;
		}
		
	}
	
	public void update() {
	
		switch(AppState.state) {
		case MENU:
			menu.update();
			break;
		case SIMULATING:
			simulating.update();
			break;
		default:
			break;
		}
	}
	
	// private methods

	private void startAppLoop() {
		thread = new Thread(this);
		thread.start();
	} 
	 
	private void initializeClasses() {

		simulating = new Simulating(this);
		menu = new Menu(this);
	
		
	}
	
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				panel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				frames = 0;
				updates = 0;

			}
		}

	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Simulating getSimulating() {
		return simulating;
	}
	
}
