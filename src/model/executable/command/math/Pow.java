package model.executable.command.math;

import model.Environment;
import model.executable.command.MathCommand;

/**
 * Returns base raised to the power of the exponent
 * 
 * @author zhuangbihan
 *
 */
public class Pow extends MathCommand {

	public Pow() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		return Math.pow(getParamValue(env, lastParamIndex()-1), getParamValue(env, lastParamIndex()));
	}
}
