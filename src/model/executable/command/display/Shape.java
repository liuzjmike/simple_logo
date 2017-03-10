package model.executable.command.display;

import model.executable.command.SingleCommand;
import model.turtle.Turtle;

/**
 * returns turtle's current shape index
 * @author zhuangbihan
 *
 */
public class Shape extends SingleCommand {

	public Shape() {
		super(0);
	}

	@Override
	protected double turtleExecute(Turtle turtle) {
		return 0;
	}

}
