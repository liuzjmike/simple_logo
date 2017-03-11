package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Erases turtle's trails and sends it to the home position Returns the distance
 * turtle moved
 * 
 * @author zhuangbihan
 *
 */
public class ClearScreen extends MultipleCommand {

	public ClearScreen() {
		super(0);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.reset();
	}

}
