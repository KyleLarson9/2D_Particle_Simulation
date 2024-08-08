package main;

import java.awt.Graphics2D;
import states.Menu;
import states.Simulating;
import states.SimulationSettings;
import states.SimulationState;

public class Simulation implements Runnable {

	private SimulationFrame frame;
	public SimulationPanel panel;
	private Thread thread;
	
	// states
	private Simulating simulating;
	private Menu menu;
	private SimulationSettings simulationSettings;
	
	private final int FPS = 120;
	private final int UPS = 200;
	
	private final static int TILES_DEFAULT_SIZE = 32;
	public  final static float SCALE = 2.0f;
	private final static int TILES_IN_WIDTH = 14;
	private final static int TILES_IN_HEIGHT = 14;
	private final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int APP_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int APP_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
		
	public Simulation() {

		initializeClasses();
		
		panel = new SimulationPanel(this);
		frame = new SimulationFrame(panel);
		panel.setFocusable(true);
		panel.requestFocus();
		
		startAppLoop();
		
	}
	
	// public methods
	
	public void render(Graphics2D g2d) {
		
		switch(SimulationState.state) {
		case START_MENU:
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
	
		switch(SimulationState.state) {
		case START_MENU:
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
