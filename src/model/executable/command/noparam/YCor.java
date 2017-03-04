package model.executable.command.noparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns the turtle's Y coordinate from the center of the screen
 * @author zhuangbihan
 *
 */
public class YCor extends AbstractCommand {

	public YCor() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.getY());
	}

}
