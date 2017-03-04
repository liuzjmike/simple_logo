package model.executable.command.oneparam;

import model.Environment;

/**
 * Moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Forward extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.move(getParamValue(0, env), env.getWidth(), env.getHeight()));
	}

}
