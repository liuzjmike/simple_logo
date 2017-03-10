package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if test is 0 and 0 if test is non-zero
 * 
 * @author zhuangbihan
 *
 */
public class Not extends AbstractCommand {

	public Not() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return (getParamValue(env, 0) == 0) ? 1 : 0;
	}

}
