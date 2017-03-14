package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns negative of the values of expr
 * 
 * @author zhuangbihan
 *
 */
public class Minus extends ActionCommand {

	public Minus() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return -1 * getParamValue(env, 0);
	}

}
