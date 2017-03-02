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
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().turnTutle(getParamValue(0, env)));
	}

}
