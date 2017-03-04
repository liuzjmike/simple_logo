package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if turtle's pen is down, 0 if it is up
 * 
 * @author zhuangbihan
 *
 */
public class IsPenDown extends AbstractCommand {

	public IsPenDown() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.penDown()) ? 1 : 0;
	}

}
