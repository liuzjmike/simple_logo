package model.executable.command.noparam;

import model.Environment;

/**
 * Erases turtle's trails and sends it to the home position
 * Returns the distance turtle moved
 * @author zhuangbihan
 *
 */
public class ClearScreen extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.reset());
	}

}
