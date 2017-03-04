package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Moves turtle to the center of the screen (0 0) Returns the distance turtle
 * moved
 * 
 * @author zhuangbihan
 *
 */
public class Home extends AbstractCommand {

	public Home() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.home());
	}

}
