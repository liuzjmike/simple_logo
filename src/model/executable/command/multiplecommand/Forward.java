package model.executable.command.multiplecommand;

import model.Environment;
import model.executable.command.MultipleCommand;

/**
 * Moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Forward extends MultipleCommand {

	public Forward() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
	    System.out.println(getParamValue(0, env));
		return env.getTurtlePool().applyAll(turtle -> turtle.move(getParamValue(0, env), env.getWidth(), env.getHeight()));
	}

}
