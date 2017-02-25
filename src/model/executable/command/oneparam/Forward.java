package model.executable.command.oneparam;

import model.Environment;

/**
 * Moves turtle forward in its current heading by pixels distance
 * @author zhuangbihan
 *
 */
public class Forward extends OneParamCommand {

	/**
	 * returns the value of pixels
	 */
	@Override
	protected void concreteExecute(double param, Environment env)  {
		env.getPool().moveTurtle(param);
	}

}
