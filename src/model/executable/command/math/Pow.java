package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns base raised to the power of the exponent
 * 
 * @author zhuangbihan
 *
 */
public class Pow extends ActionCommand {

	public Pow() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		return Math.pow(getParamValue(env, 0), getParamValue(env, 1));
	}

}
