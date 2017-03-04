package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Turns turtle to an absolute heading
 * @author zhuangbihan
 *
 */
public class SetHeading extends AbstractCommand {

	public SetHeading() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.setHeading(-1*getParamValue(0, env)));
	}

}
