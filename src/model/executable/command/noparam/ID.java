package model.executable.command.noparam;

import model.Environment;

public class ID extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().activeID();
	}

}
