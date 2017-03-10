package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Turns turtle clockwise by degrees angle
 * Returns the value of degrees
 * @author zhuangbihan
 *
 */
public class Right extends MultipleCommand {
	
	public Right() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.turn(-1*getParamValue(env, 0));
	}

}
