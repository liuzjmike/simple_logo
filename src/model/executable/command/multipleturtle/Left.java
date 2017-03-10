package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Turns turtle counterclockwise by degrees angle
 * @author zhuangbihan
 *
 */
public class Left extends MultipleCommand {

	public Left() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.turn(getParamValue(env, 0));
	}

}
