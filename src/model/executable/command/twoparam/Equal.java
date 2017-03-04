package model.executable.command.twoparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0
 * @author zhuangbihan
 *
 */
public class Equal extends AbstractCommand {

	public Equal() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) == getParamValue(1, env)) ? 1 : 0;
	}

}
