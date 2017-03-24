package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

/**
 * Sets shape of turtle to that represented by index
 * Returns given index
 * @author Bihan Zhuang
 *
 */
public class SetShape extends MultipleCommand {

	public SetShape() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.setShape((int)getParamValue(env, 0));
	}

}
