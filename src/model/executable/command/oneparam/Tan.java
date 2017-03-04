package model.executable.command.oneparam;

import model.Environment;
import util.Constants;

/**
 * Returns tangent of degrees
 * @author zhuangbihan
 *
 */
public class Tan extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.tan(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE));
	}

}
