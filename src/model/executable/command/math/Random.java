package model.executable.command.math;

import model.Environment;

/**
 * Returns random non-negative number strictly less than max
 * 
 * @author zhuangbihan
 *
 */
public class Random extends MathCommand {

	public Random() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Math.abs(getParamValue(env, lastParamIndex()) * Math.random());
	}

}
