package inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.App;
import main.AppPanel;
import states.AppState;

public class MouseInputs extends MouseAdapter implements MouseListener, MouseMotionListener {

	
	private AppPanel panel;
	
	public MouseInputs(AppPanel panel) {
		this.panel = panel;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		switch(AppState.state) {
		case START_MENU:
			panel.getApp().getMenu().mouseClicked(e);
			break;
		case SIMULATING:
			panel.getApp().getSimulating().mouseClicked(e);
			break;
		default:
			break;
		
		}
		
	}

	
}
