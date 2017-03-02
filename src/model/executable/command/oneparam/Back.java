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
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().moveTurtle(-1*getParamValue(0, env)));
	}

}
