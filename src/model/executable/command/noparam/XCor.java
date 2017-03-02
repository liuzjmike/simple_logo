package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns the turtle's X coordinate from the center of the screen
 * @author zhuangbihan
 *
 */
public class XCor extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().xCor());
	}

}
