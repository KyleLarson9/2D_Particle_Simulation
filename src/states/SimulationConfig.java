package states;

public class SimulationConfig {

	// default values
	
	private static double gravity = 9.81; 
	private static boolean gravityEnabled = false;
	
	private static double coeffRestitution = .05;
	
	public static double getGravity() {
		return gravity;
	}
	
	public static void setGravity(double newGravity) {
		gravity = newGravity;
	}

	public static double getCoeffRestitution() {
		return coeffRestitution;
	}

	public static boolean isGravityEnabled() {
		return gravityEnabled;
	}
	
	public static void setGravityEnabled(boolean enabled) {
		gravityEnabled = enabled;
	}
	
	public static void setCoeffRestitution(double newCoeffRestitution) {
		coeffRestitution = newCoeffRestitution;
	}
	
}

