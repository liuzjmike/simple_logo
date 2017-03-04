package model.executable.command.oneparam;

import model.Environment;

/**
 * Turns turtle to an absolute heading
 * @author zhuangbihan
 *
 */
public class SetHeading extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.setHeading(-1*getParamValue(0, env)));
	}

}
