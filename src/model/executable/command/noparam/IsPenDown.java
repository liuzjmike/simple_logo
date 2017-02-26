package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Returns 1 if turtle's pen is down, 0 if it is up
 * @author zhuangbihan
 *
 */
public class IsPenDown extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		return new Literal((env.getTurtlePool().penDown()) ? 1 : 0);
	}

}
