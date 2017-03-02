package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns 1 if test is 0 and 0 if test is non-zero
 * @author zhuangbihan
 *
 */
public class Not extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal((getParamValue(0, env) == 0) ? 1 : 0);
	}

}
