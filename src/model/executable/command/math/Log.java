package model.executable.command.math;

import model.Environment;
import model.executable.command.MathCommand;

/**
 * Returns natural log of expr
 * 
 * @author zhuangbihan
 *
 */
public class Log extends MathCommand {

	public Log() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Math.log(getParamValue(env, lastParamIndex()));
	}

}
