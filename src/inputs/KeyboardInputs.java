package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.AppPanel;
import states.AppState;

public class KeyboardInputs implements KeyListener {

	private AppPanel appPanel;

	public KeyboardInputs(AppPanel appPanel) {
		this.appPanel = appPanel;
	}

	@Override 
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
  
	@Override
	public void keyReleased(KeyEvent e) {
		switch(AppState.state) {
		case MENU:
			appPanel.getApp().getMenu().keyReleased(e);
			break;
		case SIMULATING:
			appPanel.getApp().getSimulating().keyReleased(e);
			break;
		default:
			break;
		
		}
	} 

	@Override
	public void keyPressed(KeyEvent e) {
		switch(AppState.state) {
		case MENU:
			appPanel.getApp().getMenu().keyPressed(e);
			break;
		case SIMULATING:
			appPanel.getApp().getSimulating().keyPressed(e);
			break;
		default:
			break;
		
		}
	}
}