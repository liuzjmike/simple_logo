package model.executable.command.multipleturtle;

import model.Environment;
import model.turtle.Turtle;

/**
 * Puts pen down such that when the turtle moves, it leaves a trail
 * 
 * @author zhuangbihan
 *
 */
public class PenDown extends MultipleCommand {

	public PenDown() {
		super(0);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		turtle.getPen().setDown(true);
		return 1;
	}

}
