package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if the value of expr1 is strictly greater than the value of expr2,
 * otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class Greater extends AbstractCommand {

	public Greater() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		return (getParamValue(env, 0) > getParamValue(env, 1)) ? 1 : 0;
	}

}