package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Forward extends AbstractCommand {

	public Forward() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.move(getParamValue(0, env), env.getWidth(), env.getHeight()));
	}

}
