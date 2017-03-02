package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;
import model.executable.Variable;

/**
 * Assigns the value of expr to variable, creating the variable if necessary
 * Returns expr
 * @author zhuangbihan
 *
 */
public class Make extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		env.getVariablePool().add(((Variable)getParam(0).get(0)).getName(), getParamValue(1, env));
		return new Literal(getParamValue(1, env));
	}
}
