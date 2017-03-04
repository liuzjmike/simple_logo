package model.executable.command.noparam;

import model.Environment;

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
	protected double concreteExecute(Environment env) {
		return 0;
	}

}
