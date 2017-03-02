package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;
import util.Constants;

/**
 * Returns arctangent of degrees
 * @author zhuangbihan
 *
 */
public class ATan extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(Constants.resolveNaN(Math.atan(getParamValue(0, env)) * Constants.DEGREES_PER_RADIAN));
	}

}
