package model.executable.command.oneparam;

import model.Environment;
import util.Constants;

/**
 * Returns arctangent of degrees
 * @author zhuangbihan
 *
 */
public class ATan extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.atan(getParamValue(0, env)) * Constants.DEGREES_PER_RADIAN);
	}

}
