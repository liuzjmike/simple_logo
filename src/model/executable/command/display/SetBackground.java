package model.executable.command.display;

import model.Environment;
import model.executable.command.ActionCommand;

public class SetBackground extends ActionCommand{

	public SetBackground() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		env.getTurtlePool().setBackground((int)getParamValue(env, 0));
		return getParamValue(env, 0);
	}

}
