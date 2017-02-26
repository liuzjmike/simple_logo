package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Moves turtle backward in its current heading by pixels distance
 * Returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Back extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		env.getTurtlePool().moveTurtle(-1*getParamValue(0, env));
		return new Literal(getParamValue(0, env));
	}

}
