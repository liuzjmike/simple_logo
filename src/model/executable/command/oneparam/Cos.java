package model.executable.command.oneparam;

import model.Environment;
import util.Constants;

/**
 * Return cosine of degrees
 * @author zhuangbihan
 *
 */
public class Cos extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.cos(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE));
	}

}
