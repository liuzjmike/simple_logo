package model.executable.command.math;

/**
 * Return cosine of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Cos extends TrigCommand {

	public Cos() {
		super(1);
	}

	@Override
	protected double trig(double arg) {
		return Math.cos(arg);
	}

}
