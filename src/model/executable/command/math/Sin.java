package model.executable.command.math;

/**
 * Returns sine of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Sin extends TrigCommand {

	public Sin() {
		super(1);
	}

	@Override
	protected double trig(double arg) {
		return Math.sin(arg);
	}

}
