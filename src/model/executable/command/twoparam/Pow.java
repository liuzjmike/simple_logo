package model.executable.command.twoparam;

import model.Environment;

/**
 * Returns base raised to the power of the exponent
 * @author zhuangbihan
 *
 */
public class Pow extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Math.pow(getParamValue(0, env), getParamValue(1, env));
	}

}
