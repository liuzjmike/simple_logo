package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns negative of the values of expr
 * 
 * @author zhuangbihan
 *
 */
public class Minus extends AbstractCommand {

	public Minus() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return -1 * getParamValue(env, 0);
	}

}
