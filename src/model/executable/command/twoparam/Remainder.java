package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns remainder on dividing the values of expr1 by expr2
 * Throws exception if expr2 is equal to 0
 * @author zhuangbihan
 *
 */
public class Remainder extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		if (getParamValue(1, env) == 0) {
			throw new RuntimeException();
		}
		return new Literal(getParamValue(0, env)%getParamValue(1, env));
	}

}
