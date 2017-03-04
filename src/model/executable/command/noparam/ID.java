package model.executable.command.noparam;

import model.Environment;
import model.executable.command.AbstractCommand;

public class ID extends AbstractCommand {

	public ID() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().activeID();
	}

}
