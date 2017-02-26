package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns quotient of the values of expr1 and expr2
 * Throws exception if expr2 is equal to 0
 * @author zhuangbihan
 *
 */
public class Quotient extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		if (getParamValue(1, env) == 0) {
			// TODO
		}
		return new Literal(getParamValue(0, env)/getParamValue(1, env));
	}

}