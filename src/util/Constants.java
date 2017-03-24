package util;

/**
 * Constant values used in SLogo.
 * @author Mike Liu
 *
 */
public class Constants {
	
	public static final double DEGREES_PER_RADIAN = 180 / Math.PI;
	public static final double RADIAN_PER_DEGREE = Math.PI / 180;
	public static final double ROUND_ANGLE = 360;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public static final String NEWLINE = "\n";

	private Constants(){
		
	}
	
	public static double resolveNaN(double value) {
		if(Double.isNaN(value)) {
			return 0;
		}
		return value;
	}
}
