package model.executable.command.oneparam;

import model.Environment;

/**
 * Returns natural log of expr
 * @author zhuangbihan
 *
 */
public class Log extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Math.log(getParamValue(0, env));
	}

}
