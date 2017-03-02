package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns random non-negative number strictly less than max
 * @author zhuangbihan
 *
 */
public class Random extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(Math.abs(getParamValue(0, env) * Math.random()));
	}

}
