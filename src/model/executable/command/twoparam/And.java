package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns 1 if test1 and test2 are non-zero, otherwise 0
 * @author zhuangbihan
 *
 */
public class And extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal((getParamValue(0, env)!=0 && getParamValue(1, env)!=0) ? 1 : 0);
	}

}
