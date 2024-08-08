package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.SimulationPanel;
import states.SimulationState;

public class KeyboardInputs implements KeyListener {

	private SimulationPanel panel;

	public KeyboardInputs(SimulationPanel panel) {
		this.panel = panel;
	}

	@Override 
	public void keyTyped(KeyEvent e) {

	}
  
	@Override
	public void keyReleased(KeyEvent e) {
		switch(SimulationState.state) {
		case START_MENU:
			panel.getSimulation().getMenu().keyReleased(e);
			break;
		case SIMULATING:
			panel.getSimulation().getSimulating().keyReleased(e);
			break;
		default:
			break;	
		}
	} 

	@Override
	public void keyPressed(KeyEvent e) {
		switch(SimulationState.state) {
		case START_MENU:
			panel.getSimulation().getMenu().keyPressed(e);
			break;
		case SIMULATING:
			panel.getSimulation().getSimulating().keyPressed(e);
			break;
		default:
			break;	
		}
	}
}