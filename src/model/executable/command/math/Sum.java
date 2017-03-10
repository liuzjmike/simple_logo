package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns sum of the values of expr1 and expr2
 * 
 * @author zhuangbihan
 *
 */
public class Sum extends AbstractCommand {

	public Sum() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		// TODO Auto-generated method stub
		return getParamValue(env, 0) + getParamValue(env, 1);
	}
}
