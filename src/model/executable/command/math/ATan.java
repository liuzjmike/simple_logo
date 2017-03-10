package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.Constants;

/**
 * Returns arctangent of degrees
 * 
 * @author zhuangbihan
 *
 */
public class ATan extends AbstractCommand {

	public ATan() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Constants.resolveNaN(Math.atan(getParamValue(env, 0)) * Constants.DEGREES_PER_RADIAN);
	}

}
