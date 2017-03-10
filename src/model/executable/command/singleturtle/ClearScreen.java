package model.executable.command.singleturtle;

import model.executable.command.SingleCommand;
import model.turtle.Turtle;

/**
 * Erases turtle's trails and sends it to the home position Returns the distance
 * turtle moved
 * 
 * @author zhuangbihan
 *
 */
public class ClearScreen extends SingleCommand {

	public ClearScreen() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Turtle turtle) {
		return turtle.reset();
	}

}
