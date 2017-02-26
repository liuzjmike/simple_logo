package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Return cosine of degrees
 * @author zhuangbihan
 *
 */
public class Cos extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal(Math.cos(getParamValue(0, env)));
	}

}
