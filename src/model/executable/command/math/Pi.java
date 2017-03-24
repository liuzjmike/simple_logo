package model.executable.command.math;

import model.Environment;

/**
 * Reports the number Pi
 * 
 * @author zhuangbihan
 *
 */
public class Pi extends MathCommand {

	public Pi() {
		super(0);
	}

	@Override
	protected double run(Environment env) {
		return Math.PI;
	}

}
