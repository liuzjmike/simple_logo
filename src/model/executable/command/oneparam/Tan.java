package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;
import util.Constants;

/**
 * Returns tangent of degrees
 * @author zhuangbihan
 *
 */
public class Tan extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(Constants.resolveNaN(Math.tan(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE)));
	}

}
