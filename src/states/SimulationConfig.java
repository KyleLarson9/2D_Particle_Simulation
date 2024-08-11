package states;

public class SimulationConfig {

	private static double gravity = 9.81; // default

	public static double getGravity() {
		return gravity;
	}
	
	public static void setGravity(double newGravity) {
		gravity = newGravity;
	}
	
}

