package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Puts pen down such that when the turtle moves, it leaves a trail
 * @author zhuangbihan
 *
 */
public class PenDown extends NoParamCommand {

	/**
	 * returns 1
	 */
	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(1);
	}

}
