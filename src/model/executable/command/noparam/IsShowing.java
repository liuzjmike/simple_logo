package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns 1 if turtle is showing, 0 if it is hiding
 * @author zhuangbihan
 *
 */
public class IsShowing extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal((env.getTurtlePool().isVisible()) ? 1 : 0);
	}

}
