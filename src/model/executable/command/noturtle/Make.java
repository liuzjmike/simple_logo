package model.executable.command.noturtle;

import model.Environment;
import model.executable.Literal;
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
	public Literal execute(Environment env) {
	    checkParamsLength();
	    double value = getParamValue(env, lastParamIndex());
	    env.getVariablePool().add(((Variable)getParam(0)).getName(), value);
	    return new Literal(value);
	}
}
