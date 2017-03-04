package model.executable.command.noparam;

import model.Environment;

/**
 * Makes turtle invisible
 * Returns 0
 * @author zhuangbihan
 *
 */
public class HideTurtle extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		env.getTurtlePool().apply(turtle -> turtle.setVisible(false));
		return 0;
	}

}
