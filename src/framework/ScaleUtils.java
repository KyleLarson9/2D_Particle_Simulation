package framework;

public class ScaleUtils {

	private static final int SCALE_FACTOR = 10; // 10 pixels per meter
	
	// conversion methods
	
	public static int metersToPixels(int meters) {
		return meters * SCALE_FACTOR;
	}
	
	public static float pixelsToMeters(float pixels) {
		return pixels / SCALE_FACTOR;
	}
	
	public static int getScaleFactor() {
		return SCALE_FACTOR;
	}
}
