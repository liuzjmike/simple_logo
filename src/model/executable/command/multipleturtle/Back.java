package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

/**
 * Moves turtle backward in its current heading by pixels distance
 * Returns the value of pixels
 * @author Bihan Zhuang
 *
 */
public class Back extends MultipleCommand {

	public Back() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.move(-1*getParamValue(env, 0), env.getPoolWidth(), env.getPoolHeight());
	}

}
