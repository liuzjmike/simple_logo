package model.executable.command.singleturtle;

import model.executable.command.SingleCommand;
import model.turtle.Turtle;

/**
 * Returns 1 if turtle's pen is down, 0 if it is up
 * 
 * @author zhuangbihan
 *
 */
public class IsPenDown extends SingleCommand {

	public IsPenDown() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Turtle turtle) {
		return (turtle.getPenInfo().isDown()) ? 1 : 0;
	}

}
