package model.executable.command.math;

/**
 * Returns arctangent of degrees
 * 
 * @author zhuangbihan
 *
 */
public class ATan extends TrigCommand {

	public ATan() {
		super(1);
	}

	@Override
	protected double trig(double arg) {
		return Math.atan(arg);
	}
}
