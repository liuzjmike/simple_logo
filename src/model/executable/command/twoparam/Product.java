package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns product of the values of expr1 and expr2
 * @author zhuangbihan
 *
 */
public class Product extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(getParamValue(0, env) * getParamValue(1, env));
	}

}
