package model.executable.command.noparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Makes turtle visible
*  Returns 1
 * @author zhuangbihan
 *
 */
public class ShowTurtle extends AbstractCommand {

	public ShowTurtle() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		env.getTurtlePool().apply(turtle -> turtle.setVisible(true));
		return 1;
	}

}
