package model.executable.command.noparam;

import model.Environment;

/**
 * Returns the turtle's heading in degrees
 * @author zhuangbihan
 *
 */
public class Heading extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.getHeading());
	}

}
