package model.executable.command.oneparam;

import model.Environment;

/**
 * Moves turtle backward in its current heading by pixels distance
 * Returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Back extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.move(-1*getParamValue(0, env), env.getWidth()/2, env.getHeight()/2));
	}

}
