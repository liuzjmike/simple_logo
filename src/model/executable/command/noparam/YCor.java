package model.executable.command.noparam;

import model.Environment;

/**
 * Returns the turtle's Y coordinate from the center of the screen
 * @author zhuangbihan
 *
 */
public class YCor extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.getY());
	}

}
