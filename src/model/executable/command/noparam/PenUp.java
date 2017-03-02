package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Puts pen up such that when the turtle moves, it does not leave a trail
 * @author zhuangbihan
 *
 */
public class PenUp extends NoParamCommand {

	/**
	 * returns 0
	 */
	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(0);
	}

}
