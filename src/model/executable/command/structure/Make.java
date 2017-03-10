package model.executable.command.structure;

import model.Environment;
import model.executable.Variable;
import model.executable.command.AbstractCommand;

/**
 * Assigns the value of expr to variable, creating the variable if necessary
 * Returns expr
 * 
 * @author zhuangbihan
 *
 */
public class Make extends AbstractCommand {

	public Make() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		env.getVariablePool().add(((Variable) getParam(0)).getName(), getParamValue(env, 1));
		return getParamValue(env, 1);
	}
}
