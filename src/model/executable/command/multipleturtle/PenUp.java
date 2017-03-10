package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Puts pen up such that when the turtle moves, it does not leave a trail
 * 
 * @author zhuangbihan
 *
 */
public class PenUp extends MultipleCommand {

	public PenUp() {
		super(0);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		turtle.getPen().setDown(false);
		return 0;
	}

}
