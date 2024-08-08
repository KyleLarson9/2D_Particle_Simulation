package states;

import main.Simulation;

public class State { // super class for all states
	
	protected Simulation simulation;
	
	public State(Simulation simulation) {
		this.simulation = simulation;
	}
	
	public Simulation getApp() {
		return simulation;
	}

}
