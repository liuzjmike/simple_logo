package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns negative of the values of expr
 * @author zhuangbihan
 *
 */
public class Minus extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(-1*getParamValue(0, env));
	}

}
