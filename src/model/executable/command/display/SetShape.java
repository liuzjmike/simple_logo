package model.executable.command.display;

import model.Environment;
import model.executable.command.AbstractCommand;

public class SetShape extends AbstractCommand {

	public SetShape() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		env.getTurtlePool().applyShape();
		return getParamValue(env, 0);
	}

}
