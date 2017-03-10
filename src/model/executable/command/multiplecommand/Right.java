package model.executable.command.multiplecommand;

import model.Environment;
import model.executable.command.MultipleCommand;

/**
 * Turns turtle clockwise by degrees angle
 * Returns the value of degrees
 * @author zhuangbihan
 *
 */
public class Right extends MultipleCommand {
	
	public Right() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().applyAll(turtle -> turtle.turn(-1*getParamValue(0, env)));
	}

}
