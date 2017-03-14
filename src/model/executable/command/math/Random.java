package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns random non-negative number strictly less than max
 * 
 * @author zhuangbihan
 *
 */
public class Random extends ActionCommand {

	public Random() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Math.abs(getParamValue(env, 0) * Math.random());
	}

}
