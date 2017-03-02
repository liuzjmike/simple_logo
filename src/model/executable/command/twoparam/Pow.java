package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns base raised to the power of the exponent
 * @author zhuangbihan
 *
 */
public class Pow extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(Math.pow(getParamValue(0, env), getParamValue(1, env)));
	}

}
