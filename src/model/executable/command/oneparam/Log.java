package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns natural log of expr
 * @author zhuangbihan
 *
 */
public class Log extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(Math.log(getParamValue(0, env)));
	}

}
