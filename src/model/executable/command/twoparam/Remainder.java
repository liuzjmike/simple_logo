package model.executable.command.twoparam;

import model.Environment;

/**
 * Returns remainder on dividing the values of expr1 by expr2
 * Throws exception if expr2 is equal to 0
 * @author zhuangbihan
 *
 */
public class Remainder extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		if (getParamValue(1, env) == 0) {
			throw new RuntimeException();
		}
		return getParamValue(0, env)%getParamValue(1, env);
	}

}
