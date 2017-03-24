package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

/**
 * Turns turtle to face the point (x, y), where (0, 0) is the center of the screen
 * Returns the number of degrees turtle turned
 * @author zhuangbihan
 *
 */
public class Towards extends MultipleCommand {

	public Towards() {
		super(2);
	}
	
	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.towards(getParamValue(env, 0), getParamValue(env,1));
	}
}
