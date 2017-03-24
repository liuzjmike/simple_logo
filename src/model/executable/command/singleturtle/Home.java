package model.executable.command.singleturtle;

import model.turtle.Turtle;

/**
 * Moves turtle to the center of the screen (0 0) Returns the distance turtle
 * moved
 * 
 * @author zhuangbihan
 *
 */
public class Home extends SingleCommand {

	public Home() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Turtle turtle) {
		return turtle.home();
	}

}
