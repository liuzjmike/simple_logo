package model.executable.command.math;

import model.Environment;
import model.executable.command.MathCommand;

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
