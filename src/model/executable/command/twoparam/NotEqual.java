package model.executable.command.twoparam;

import model.Environment;

public class NotEqual extends TwoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) != getParamValue(1, env)) ? 1 : 0;
	}

}
