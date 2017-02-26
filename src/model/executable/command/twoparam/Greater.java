package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0
 * @author zhuangbihan
 *
 */
public class Greater extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal((getParamValue(0, env) > getParamValue(1, env)) ? 1 : 0);
	}

}
