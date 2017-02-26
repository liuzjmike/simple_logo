package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Assigns the value of expr to variable, creating the variable if necessary
 * Returns expr
 * @author zhuangbihan
 *
 */
public class Make extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		
		return new Literal(getParamValue(1, env));
	}

}
