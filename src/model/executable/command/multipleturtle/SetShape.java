package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

public class SetShape extends MultipleCommand {

	public SetShape() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.setShape((int)getParamValue(env, 0));
	}

}
