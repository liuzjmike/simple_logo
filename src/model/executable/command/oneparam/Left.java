package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Turns turtle counterclockwise by degrees angle
 * @author zhuangbihan
 *
 */
public class Left extends AbstractCommand {

	public Left() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.turn(getParamValue(0, env)));
	}

}
