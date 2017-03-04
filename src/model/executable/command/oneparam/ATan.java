package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.Constants;

/**
 * Returns arctangent of degrees
 * @author zhuangbihan
 *
 */
public class ATan extends AbstractCommand {

	public ATan() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.atan(getParamValue(0, env)) * Constants.DEGREES_PER_RADIAN);
	}

}
