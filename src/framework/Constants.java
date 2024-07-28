package framework;

public enum Constants {

	GRAVITY(9.81); // m/s/s

	private final double constant;
	
	Constants(double constant) {
		this.constant = constant;
	}
	
	public double getConstant() {
		return constant;
	}
}
