package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns difference of the values of expr1 and expr2
 * 
 * @author zhuangbihan
 *
 */
public class Difference extends ActionCommand {

	public Difference() {
		super(2);
	}
	
	@Override
	protected double run(Environment env) {
		return getParamValue(env, 0) - getParamValue(env, 1);
	}

}
