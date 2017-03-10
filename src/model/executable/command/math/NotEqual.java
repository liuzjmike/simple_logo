package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;

public class NotEqual extends AbstractCommand {

	public NotEqual() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		return (getParamValue(env, 0) != getParamValue(env, 1)) ? 1 : 0;
	}

}