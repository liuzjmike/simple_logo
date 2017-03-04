package model.executable.command.noparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Puts pen up such that when the turtle moves, it does not leave a trail
 * @author zhuangbihan
 *
 */
public class PenUp extends AbstractCommand {

	public PenUp() {
		super(0);
	}

	/**
	 * returns 0
	 */
	@Override
	protected double concreteExecute(Environment env) {
		return 0;
	}

}
