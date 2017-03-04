package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns 1 if turtle is showing, 0 if it is hiding
 * 
 * @author zhuangbihan
 *
 */
public class IsShowing extends AbstractCommand {

	public IsShowing() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.isVisible()) ? 1 : 0;
	}

}
