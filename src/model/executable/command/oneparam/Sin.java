package model.executable.command.oneparam;

import model.Environment;
import util.Constants;

/**
 * Returns sine of degrees
 * @author zhuangbihan
 *
 */
public class Sin extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.sin(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE));
	}

}
