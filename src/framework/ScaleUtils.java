package framework;

public class ScaleUtils {

	private static final double SCALE_FACTOR = 10; // 10 pixels per meter
	
	// conversion methods
	
	public static double metersToPixels(double meters) {
		return meters * SCALE_FACTOR;
	}
	
	public static double pixelsToMeters(double GRAVITY) {
		return GRAVITY / SCALE_FACTOR;
	}
	 
	public static double getScaleFactor() {
		return SCALE_FACTOR;
	}
}
