package model.executable.command.twoparam;

import model.Environment;

/**
 * Returns 1 if test1 or test2 are non-zero, otherwise 0
 * @author zhuangbihan
 *
 */
public class Or extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env)!=0 || getParamValue(1, env)!=0) ? 1 : 0;
	}

}
