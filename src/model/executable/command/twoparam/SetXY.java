package model.executable.command.twoparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Moves turtle to an absolute screen position, where (0, 0) is the center of the screen
 * Returns the distance turtle moved
 * @author zhuangbihan
 *
 */
public class SetXY extends AbstractCommand {

	public SetXY() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.setXY(getParamValue(0, env), getParamValue(1, env)));
	}

}
