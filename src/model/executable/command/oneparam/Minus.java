package model.executable.command.oneparam;

import model.Environment;

/**
 * Returns negative of the values of expr
 * @author zhuangbihan
 *
 */
public class Minus extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return -1*getParamValue(0, env);
	}

}
