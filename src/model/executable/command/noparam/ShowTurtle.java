package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Makes turtle visible
*  Returns 1
 * @author zhuangbihan
 *
 */
public class ShowTurtle extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		env.getTurtlePool().setVisible(true);
		return new Literal(1);
	}

}
