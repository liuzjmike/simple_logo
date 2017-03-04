package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Turns turtle clockwise by degrees angle
 * Returns the value of degrees
 * @author zhuangbihan
 *
 */
public class Right extends AbstractCommand {
	
	public Right() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.turn(-1*getParamValue(0, env)));
	}

}
