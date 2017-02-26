package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;
import util.Constants;

/**
 * Returns sine of degrees
 * @author zhuangbihan
 *
 */
public class Sin extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(Constants.resolveNaN(Math.sin(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE)));
	}

}
