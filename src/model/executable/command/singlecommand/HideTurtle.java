package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Makes turtle invisible Returns 0
 * 
 * @author zhuangbihan
 *
 */
public class HideTurtle extends AbstractCommand {

	public HideTurtle() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		env.getTurtlePool().apply(turtle -> turtle.setVisible(false));
		return 0;
	}

}
