package model.executable.command.singleturtle;

import model.turtle.Turtle;

/**
 * Returns the turtle's heading in degrees
 * 
 * @author zhuangbihan
 *
 */
public class Heading extends SingleCommand {

	public Heading() {
		super(0);
	}

	@Override
	protected double turtleExecute(Turtle turtle) {
		return turtle.getHeading();
	}

}
