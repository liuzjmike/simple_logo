package model.executable.command.noparam;

import model.Environment;

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
	protected double concreteExecute(Environment env) {
		return 1;
	}

}
