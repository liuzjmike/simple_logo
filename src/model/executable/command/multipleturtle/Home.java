package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

/**
 * Moves turtle to the center of the screen (0, 0)
 * Returns the distance turtle moved
 * @author Bihan Zhuang
 *
 */
public class Home extends MultipleCommand {

	public Home() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.home();
	}

}
