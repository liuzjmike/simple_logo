package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

public class ATan extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		// TODO since this function is defined continuously, 
		// take appropriate action (i.e., divide-by-zero might silently return zero)
		return new Literal(Math.atan(getParamValue(0, env)));
	}

}
