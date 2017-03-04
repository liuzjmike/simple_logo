package model.executable.command.twoparam;

import model.Environment;

/**
 * Turns turtle to face the point (x, y), where (0, 0) is the center of the screen
 * Returns the number of degrees turtle turned
 * @author zhuangbihan
 *
 */
public class Towards extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.towards(getParamValue(0, env), getParamValue(1, env)));
	}

}
