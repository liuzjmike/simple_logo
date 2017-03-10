package model.executable.command.singleturtle;

import model.executable.command.SingleCommand;
import model.turtle.Turtle;

/**
 * Returns 1 if turtle is showing, 0 if it is hiding
 * 
 * @author zhuangbihan
 *
 */
public class IsShowing extends SingleCommand {

	public IsShowing() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Turtle turtle) {
		return (turtle.isVisible()) ? 1 : 0;
	}

}
