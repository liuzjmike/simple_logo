package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Forward extends MultipleCommand {

	public Forward() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.move(getParamValue(env, 0), env.getWidth(), env.getHeight());
	}

}
