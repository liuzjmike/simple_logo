package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Turns turtle counterclockwise by degrees angle
 * @author zhuangbihan
 *
 */
public class Left extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		env.getPool().turnTutle(-1*getParamValue(0, env));
		return new Literal(getParamValue(0, env));
	}

}
