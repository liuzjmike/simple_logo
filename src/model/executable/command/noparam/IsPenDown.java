package model.executable.command.noparam;

import model.Environment;

/**
 * Returns 1 if turtle's pen is down, 0 if it is up
 * @author zhuangbihan
 *
 */
public class IsPenDown extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.penDown()) ? 1 : 0;
	}

}
