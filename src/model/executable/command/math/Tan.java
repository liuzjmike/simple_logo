package model.executable.command.math;

/**
 * Returns tangent of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Tan extends TrigCommand {

	public Tan() {
		super(1);
	}

	@Override
	protected double trig(double arg) {
		return Math.tan(arg);
	}

}
