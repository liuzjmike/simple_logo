package model.executable.command.singleturtle;

import model.executable.command.SingleCommand;
import model.turtle.Turtle;

/**
 * Returns the turtle's X coordinate from the center of the screen
 * 
 * @author zhuangbihan
 *
 */
public class XCor extends SingleCommand {

	public XCor() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Turtle turtle) {
		return turtle.getX();
	}

}
