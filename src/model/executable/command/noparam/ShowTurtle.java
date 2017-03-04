package model.executable.command.noparam;

import model.Environment;

/**
 * Makes turtle visible
*  Returns 1
 * @author zhuangbihan
 *
 */
public class ShowTurtle extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		env.getTurtlePool().apply(turtle -> turtle.setVisible(true));
		return 1;
	}

}
