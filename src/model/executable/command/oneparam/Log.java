package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns natural log of expr
 * @author zhuangbihan
 *
 */
public class Log extends AbstractCommand {

	public Log() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Math.log(getParamValue(0, env));
	}

}
