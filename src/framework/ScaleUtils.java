package framework;

public class ScaleUtils {

	private static final float SCALE_FACTOR = 10f; // 1 pixels per meter
	
	// conversion methods
	
	public static double metersToPixels(double meters) {
		return meters * SCALE_FACTOR;
	}
	
	public static float pixelsToMeters(float pixels) {
		return pixels / SCALE_FACTOR;
	}
	
	public static float getScaleFactor() {
		return SCALE_FACTOR;
	}
}
