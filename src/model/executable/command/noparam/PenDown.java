package model.executable.command.noparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Puts pen down such that when the turtle moves, it leaves a trail
 * @author zhuangbihan
 *
 */
public class PenDown extends AbstractCommand {

	public PenDown() {
		super(0);
	}

	/**
	 * returns 1
	 */
	@Override
	protected double concreteExecute(Environment env) {
		return 1;
	}

}
