package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Moves turtle to the center of the screen (0 0)
 * Returns the distance turtle moved
 * @author zhuangbihan
 *
 */
public class Home extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().home());
	}

}
