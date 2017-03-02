package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Erases turtle's trails and sends it to the home position
 * Returns the distance turtle moved
 * @author zhuangbihan
 *
 */
public class ClearScreen extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(env.getTurtlePool().reset());
	}

}
