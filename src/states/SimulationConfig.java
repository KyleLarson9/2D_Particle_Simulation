package states;

public class SimulationConfig {

	// default values
	
	private static double gravity = 9.81; 
	private static boolean gravityEnabled = true;
	
	private static double coeffRestitution = 1;
	private static boolean coeffRestitutionEnabled = true;
	
	private static double initialVelocity = 100;
	
	// gravity methods
	public static double getGravity() {
		return gravity;
	}
	
	public static void setGravity(double newGravity) {
		gravity = newGravity;
	}

	public static boolean isGravityEnabled() {
		return gravityEnabled;
	}
	
	public static void setGravityEnabled(boolean enabled) {
		gravityEnabled = enabled;
	}
	
	// coefficient of restitution methods
	public static double getCoeffRestitution() {
		return coeffRestitution;
	}
	
	public static void setCoeffRestitution(double newCoeffRestitution) {
		coeffRestitution = newCoeffRestitution;
	}
	
	public static boolean isCoeffRestitutionEnabled() {
		return coeffRestitutionEnabled;
	}
	
	public static void setCoeffRestitution(boolean enabled) {
		coeffRestitutionEnabled = enabled;
	}
}

