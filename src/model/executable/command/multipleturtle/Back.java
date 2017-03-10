package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Moves turtle backward in its current heading by pixels distance
 * Returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Back extends MultipleCommand {

	public Back() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.move(-1*getParamValue(env, 0), env.getWidth(), env.getHeight());
	}

}
