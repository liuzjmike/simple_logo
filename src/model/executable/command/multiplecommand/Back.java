package model.executable.command.multiplecommand;

import model.Environment;
import model.executable.command.MultipleCommand;

/**
 * Moves turtle backward in its current heading by pixels distance
 * Returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Back extends MultipleCommand {

	public Back() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().applyAll(turtle -> turtle.move(-1*getParamValue(0, env), env.getWidth()/2, env.getHeight()/2));
	}

}
