package model.executable.command.multiplecommand;

import model.Environment;
import model.executable.command.MultipleCommand;

/**
 * Turns turtle to an absolute heading
 * @author zhuangbihan
 *
 */
public class SetHeading extends MultipleCommand {

	public SetHeading() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.setHeading(-1*getParamValue(0, env)));
	}

}
