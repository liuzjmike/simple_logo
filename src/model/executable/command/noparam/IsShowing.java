package model.executable.command.noparam;

import model.Environment;

/**
 * Returns 1 if turtle is showing, 0 if it is hiding
 * @author zhuangbihan
 *
 */
public class IsShowing extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.isVisible()) ? 1 : 0;
	}

}
