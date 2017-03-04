package model.executable.command.oneparam;

import model.Environment;

/**
 * Returns random non-negative number strictly less than max
 * @author zhuangbihan
 *
 */
public class Random extends OneParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Math.abs(getParamValue(0, env) * Math.random());
	}

}
