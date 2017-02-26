package util;

public class Constants {
	
	public static final double DEGREES_PER_RADIAN = 180 / Math.PI;
	public static final double RADIAN_PER_DEGREE = Math.PI / 180;

	private Constants(){
		
	}
	
	public static double resolveNaN(double value) {
		if(Double.isNaN(value)) {
			return 0;
		}
		return value;
	}
}
