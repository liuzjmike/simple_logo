package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if test1 and test2 are non-zero, otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class And extends AbstractCommand {

	public And() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) != 0 && getParamValue(1, env) != 0) ? 1 : 0;
	}

}
