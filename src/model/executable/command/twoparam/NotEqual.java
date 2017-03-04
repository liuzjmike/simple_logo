package model.executable.command.twoparam;

import model.Environment;
import model.executable.command.AbstractCommand;

public class NotEqual extends AbstractCommand {

	public NotEqual() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return (getParamValue(0, env) != getParamValue(1, env)) ? 1 : 0;
	}

}
