package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if test1 or test2 are non-zero, otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class Or extends AbstractCommand {

	public Or() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		return (getParamValue(env, 0) != 0 || getParamValue(env, 1) != 0) ? 1 : 0;
	}

}
