package model.executable.command.multiplecommand;

import model.Environment;
import model.executable.command.MultipleCommand;

/**
 * Turns turtle counterclockwise by degrees angle
 * @author zhuangbihan
 *
 */
public class Left extends MultipleCommand {

	public Left() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.turn(getParamValue(0, env)));
	}

}
