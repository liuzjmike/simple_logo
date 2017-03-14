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
	    checkParams();
	    double ret = 0;
	    for(int i = 0; i < paramsLength(); i += numParams()) {
	    	ret = getParamValue(env, i+1);
	    	env.getVariablePool().add(((Variable)getParam(i)).getName(), ret);
	    }
	    return new Literal(ret);
	}
}
