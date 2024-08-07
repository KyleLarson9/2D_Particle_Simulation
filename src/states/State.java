package states;

import main.App;

public class State { // super class
	
	protected App app;
	
	public State(App app) {
		this.app = app;
	}
	
	public App getApp() {
		return app;
	}

}
