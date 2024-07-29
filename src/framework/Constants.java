package framework;

public enum Constants {

	GRAVITY(9.81), // m/s^2
	AIR_DENSITY(1.225); // kg/m^3
	
	private final double constant;
	
	Constants(double constant) {
		this.constant = constant;
	}
	
	public double getConstant() {
		return constant;
	}
}

