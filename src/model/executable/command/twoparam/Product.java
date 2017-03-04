package model.executable.command.twoparam;

import model.Environment;

/**
 * Returns product of the values of expr1 and expr2
 * @author zhuangbihan
 *
 */
public class Product extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return getParamValue(0, env) * getParamValue(1, env);
	}

}
