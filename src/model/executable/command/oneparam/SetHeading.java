package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Turns turtle to an absolute heading
 * @author zhuangbihan
 *
 */
public class SetHeading extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(env.getTurtlePool().setTurtleHeading(getParamValue(0, env)));
	}

}
