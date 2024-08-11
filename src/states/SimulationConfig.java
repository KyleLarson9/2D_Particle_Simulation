package states;

public class SimulationConfig {

	private static double gravity = 9.81; // default
	private static double coeffRestitution = .05; // default
	
	public static double getGravity() {
		return gravity;
	}
	
	public static void setGravity(double newGravity) {
		gravity = newGravity;
	}

	public static double getCoeffRestitution() {
		return coeffRestitution;
	}

	public static void setCoeffRestitution(double newCoeffRestitution) {
		coeffRestitution = newCoeffRestitution;
	}
	
}

