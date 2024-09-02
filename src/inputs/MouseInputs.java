package inputs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.SimulationPanel;
import states.SimulationState;

public class MouseInputs extends MouseAdapter implements MouseListener, MouseMotionListener {

	private SimulationPanel panel;
	
	public MouseInputs(SimulationPanel panel) {
		this.panel = panel;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		switch(SimulationState.state) {
		case SIMULATING:
			panel.getSimulation().getSimulating().mouseClicked(e);
			break;		
		case SIMULATION_SETTINGS_OVERLAY:
			panel.getSimulation().getSimulating().getSimulationSettings().mouseClicked(e);
			break;
		default:
			break;
		
		}
		
	}

	
}
