package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

/**
 * Moves turtle to an absolute screen position, where (0, 0) is the center of the screen
 * Returns the distance turtle moved
 * @author Bihan Zhuang
 *
 */
public class SetXY extends MultipleCommand {

	public SetXY() {
		super(2);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.setXY(getParamValue(env, 0), getParamValue(env, 1));
	}
}
