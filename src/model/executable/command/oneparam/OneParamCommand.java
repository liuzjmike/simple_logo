package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;
import model.executable.command.AbstractCommand;

public abstract class OneParamCommand extends AbstractCommand {

	@Override
	public int numParams() {
		return 1;
	}

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		concreteExecute(getParamValue(0, env), env);
		return new Literal(getParamValue(0, env));
	}
	
	protected abstract void concreteExecute(double param, Environment env);
}
