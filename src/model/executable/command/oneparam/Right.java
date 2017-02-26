package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Turns turtle clockwise by degrees angle
 * Returns the value of degrees
 * @author zhuangbihan
 *
 */
public class Right extends OneParamCommand {
	
	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		env.getPool().turnTutle(-1*getParamValue(0, env));
		return new Literal(getParamValue(0, env));
	}

}
