package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

public class Tan extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		// TODO: 
		// if ()
		return new Literal(Math.tan(getParamValue(0, env)));
	}

}
