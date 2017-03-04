package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns random non-negative number strictly less than max
 * @author zhuangbihan
 *
 */
public class Random extends AbstractCommand {

	public Random() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Math.abs(getParamValue(0, env) * Math.random());
	}

}
