package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Turns turtle to face the point (x, y), where (0, 0) is the center of the screen
 * Returns the number of degrees turtle turned
 * @author zhuangbihan
 *
 */
public class Towards extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().turtleTowards(getParamValue(0, env), getParamValue(1, env)));
	}

}
