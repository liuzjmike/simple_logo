package model.executable.command.noparam;

import model.Environment;

/**
 * Moves turtle to the center of the screen (0 0)
 * Returns the distance turtle moved
 * @author zhuangbihan
 *
 */
public class Home extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.home());
	}

}
