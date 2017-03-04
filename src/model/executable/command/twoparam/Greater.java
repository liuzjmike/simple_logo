package model.executable.command.twoparam;

import model.Environment;

/**
 * Returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0
 * @author zhuangbihan
 *
 */
public class Greater extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) > getParamValue(1, env)) ? 1 : 0;
	}

}
