package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Moves turtle to an absolute screen position, where (0, 0) is the center of the screen
 * Returns the distance turtle moved
 * @author zhuangbihan
 *
 */
public class SetXY extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(env.getTurtlePool().setTurtleXY(getParamValue(0, env), getParamValue(1, env)));
	}

}
