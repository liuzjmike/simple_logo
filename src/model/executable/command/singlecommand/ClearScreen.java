package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Erases turtle's trails and sends it to the home position Returns the distance
 * turtle moved
 * 
 * @author zhuangbihan
 *
 */
public class ClearScreen extends AbstractCommand {

	public ClearScreen() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.reset());
	}

}
