package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Forward extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		env.getTurtlePool().moveTurtle(getParamValue(0, env));
		return new Literal(getParamValue(0, env));
	}

}
