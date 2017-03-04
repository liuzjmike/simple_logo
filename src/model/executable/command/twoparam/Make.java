package model.executable.command.twoparam;

import model.Environment;
import model.executable.Variable;

/**
 * Assigns the value of expr to variable, creating the variable if necessary
 * Returns expr
 * @author zhuangbihan
 *
 */
public class Make extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		env.getVariablePool().add(((Variable)getParam(0)).getName(), getParamValue(1, env));
		return getParamValue(1, env);
	}
}
