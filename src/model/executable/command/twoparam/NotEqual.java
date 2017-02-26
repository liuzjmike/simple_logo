package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

public class NotEqual extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal((getParamValue(0, env) != getParamValue(1, env)) ? 1 : 0);
	}

}
