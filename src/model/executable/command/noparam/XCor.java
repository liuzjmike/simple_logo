package model.executable.command.noparam;

import model.Environment;

/**
 * Returns the turtle's X coordinate from the center of the screen
 * @author zhuangbihan
 *
 */
public class XCor extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.getX());
	}

}
