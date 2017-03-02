package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns the turtle's heading in degrees
 * @author zhuangbihan
 *
 */
public class Heading extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().getHeading());
	}

}
