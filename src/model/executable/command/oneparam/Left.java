package model.executable.command.oneparam;

import model.Environment;

/**
 * Turns turtle counterclockwise by degrees angle
 * @author zhuangbihan
 *
 */
public class Left extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.turn(getParamValue(0, env)));
	}

}
