package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if test is 0 and 0 if test is non-zero
 * @author zhuangbihan
 *
 */
public class Not extends AbstractCommand {

	public Not() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) == 0) ? 1 : 0;
	}

}
