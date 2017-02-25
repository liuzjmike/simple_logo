package model.executable.command.oneparam;

import model.Environment;

/**
 * Turns turtle counterclockwise by degrees angle
 * @author zhuangbihan
 *
 */
public class Left extends OneParamCommand {

	@Override
	protected void concreteExecute(double param, Environment env) {
		env.getPool().turnTutle(param);
	}

}
