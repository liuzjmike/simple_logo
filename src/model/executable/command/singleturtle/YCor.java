package model.executable.command.singleturtle;

import model.turtle.Turtle;

/**
 * Returns the turtle's Y coordinate from the center of the screen
 * 
 * @author zhuangbihan
 *
 */
public class YCor extends SingleCommand {

	public YCor() {
		super(0);
	}

	@Override
	protected double turtleExecute(Turtle turtle) {
		return turtle.getY();
	}

}
