package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns the turtle's heading in degrees
 * 
 * @author zhuangbihan
 *
 */
public class Heading extends AbstractCommand {

	public Heading() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.getHeading());
	}

}
