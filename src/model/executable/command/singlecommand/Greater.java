package model.executable.command.singlecommand;

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
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) > getParamValue(1, env)) ? 1 : 0;
	}

}
